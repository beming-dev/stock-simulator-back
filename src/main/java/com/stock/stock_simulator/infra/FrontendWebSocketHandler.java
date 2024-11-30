package com.stock.stock_simulator.infra;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stock.stock_simulator.event.WebSocketEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

@Component
public class FrontendWebSocketHandler extends TextWebSocketHandler {
    private final Map<String, Set<WebSocketSession>> subscriptions = new ConcurrentHashMap<>();
    private final ApplicationEventPublisher eventPublisher;

    public FrontendWebSocketHandler(ApplicationEventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
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
            String trKey = rootNode.get("tr_key").asText();
            String trId = rootNode.get("tr_id").asText();

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