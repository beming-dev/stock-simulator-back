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

            Stock stockData = stockRepository.findBySymbol(symbol);
            if(stockData == null) throw new Exception("Invalid symbol: " + symbol);

            switch(rq_type.toLowerCase()) {
                case "current":
                    if(Objects.equals(stockData.getCountry(), "NAS")){
                        trId = "HDFSCNT0";
                        trKey="DNAS" + symbol;
                    }else{
                        trId = "H0STCNT0";
                        trKey=symbol;
                    }
                    break;
                default:
                    throw new Exception("Invalid rq_type: " + rq_type);
            }

            // 추가 로직
            // 예: 특정 조건에 따라 작업 수행
            if ("subscribe".equals(type)) {
                String subscriptionKey = trId + "|" + trKey;

                // 중복 요청 확인
                subscriptions.computeIfAbsent(subscriptionKey, k -> new CopyOnWriteArraySet<>()).add(session);
                System.out.println("User Subscribed: " + session.getId() + " to " + subscriptionKey);

                System.out.println("User Subscribed: " + session.getId());
            }
            eventPublisher.publishEvent(new WebSocketEvent(this, trId, trKey, trType));

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