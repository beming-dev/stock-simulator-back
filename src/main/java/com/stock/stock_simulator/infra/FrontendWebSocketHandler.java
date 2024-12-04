package com.stock.stock_simulator.infra;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stock.stock_simulator.entity.Stock;
import com.stock.stock_simulator.event.WebSocketEvent;
import com.stock.stock_simulator.interfaces.StockRepository;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;

@Component
public class FrontendWebSocketHandler extends TextWebSocketHandler {
    private final Map<String, Set<WebSocketSession>> subscriptions = new ConcurrentHashMap<>();
    private final ApplicationEventPublisher eventPublisher;
    private final StockRepository stockRepository;

    public FrontendWebSocketHandler(ApplicationEventPublisher eventPublisher, StockRepository stockRepository) {
        this.eventPublisher = eventPublisher;
        this.stockRepository = stockRepository;
    }

    public void removeSession(WebSocketSession session, String trId, String trKey) {
        String subscriptionKey = trId + "|" + trKey;
        Set<WebSocketSession> sessions = subscriptions.get(subscriptionKey);

        if (sessions != null) {
            // session 삭제
            if (sessions.remove(session)) {
                System.out.println("Removed session: " + session.getId() + " from subscription: " + subscriptionKey);

                System.out.println(sessions.size());
                // sessions가 비어 있으면 subscriptions에서 subscriptionKey 삭제 (옵션)
                if (sessions.isEmpty()) {
                    subscriptions.remove(subscriptionKey);
                    eventPublisher.publishEvent(new WebSocketEvent(this, trId, trKey, "2"));
                }
            } else {
                System.out.println("Session not found in subscription: " + subscriptionKey);
            }
        } else {
            System.out.println("No sessions found for subscription key: " + subscriptionKey);
        }
    }


    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        System.out.println("Frontend WebSocket Connected: " + session.getId());
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        subscriptions.values().forEach(sessions -> sessions.remove(session));
        System.out.println("Frontend WebSocket Disconnected: " + session.getId());
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String payload = message.getPayload();
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            // JSON 데이터를 JsonNode로 파싱
            JsonNode rootNode = objectMapper.readTree(payload);

            // 각 키의 값 추출
            String type = rootNode.get("type").asText();
            String trType = rootNode.get("tr_type").asText();
            String symbol = rootNode.get("symbol").asText(); //symbol
            String rq_type = rootNode.get("rq_type").asText();
            String trId;
            String trKey;

            Stock stockData = stockRepository.findBySymbol(symbol)
                    .orElseThrow(() -> new Exception("Invalid symbol: " + symbol));

            switch(rq_type.toLowerCase()) {
                case "current":
                    if(Objects.equals(stockData.getCountry(), "KSP")
                            || Objects.equals(stockData.getCountry(), "KSD")){
                        trId = "H0STCNT0";
                        trKey=symbol;
                    }else{
                        trId = "HDFSCNT0";
                        trKey="D" + stockData.getCountry() + symbol;
                    }
                    break;
                default:
                    throw new Exception("Invalid rq_type: " + rq_type);
            }

            System.out.println(type + symbol);
            // 추가 로직
            if ("subscribe".equals(type)) {
                String subscriptionKey = trId + "|" + trKey;

                // 중복 요청 확인
                subscriptions.computeIfAbsent(subscriptionKey, k -> new CopyOnWriteArraySet<>()).add(session);
                System.out.println("User Subscribed: " + session.getId() + " to " + subscriptionKey);

                eventPublisher.publishEvent(new WebSocketEvent(this, trId, trKey, trType));
            }else if ("unsubscribe".equals(type)) {
                removeSession(session, trId, trKey);
            }

        } catch (Exception e) {
            System.err.println("Failed to parse JSON payload: " + e.getMessage());
            e.printStackTrace();
        }

    }

    public void broadcastToSubscribers(String trId, String trKey, String message) {
        String subscriptionKey = trId + "|" + trKey;
        Set<WebSocketSession> sessions = subscriptions.get(subscriptionKey);

        if (sessions != null) {
            sessions.forEach(session -> {
                try {
                    if (session.isOpen()) {
                        session.sendMessage(new TextMessage(message));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }
    }
}