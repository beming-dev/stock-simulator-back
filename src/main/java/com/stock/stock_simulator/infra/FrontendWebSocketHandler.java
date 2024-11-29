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

import java.util.concurrent.CopyOnWriteArrayList;

@Component
public class FrontendWebSocketHandler extends TextWebSocketHandler {
    private final CopyOnWriteArrayList<WebSocketSession> sessions = new CopyOnWriteArrayList<>();
    private final ApplicationEventPublisher eventPublisher;

    public FrontendWebSocketHandler(ApplicationEventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }


    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        sessions.add(session);
        System.out.println("Frontend WebSocket Connected: " + session.getId());
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        sessions.remove(session);
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
                sessions.add(session);
                System.out.println("User Subscribed: " + session.getId());
            }
            eventPublisher.publishEvent(new WebSocketEvent(this, trId, trKey, trType));

        } catch (Exception e) {
            System.err.println("Failed to parse JSON payload: " + e.getMessage());
            e.printStackTrace();
        }

    }

    public void broadcastToTargetGroup(String message) {
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