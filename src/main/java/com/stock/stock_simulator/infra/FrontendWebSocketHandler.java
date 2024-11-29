package com.stock.stock_simulator.infra;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.concurrent.CopyOnWriteArrayList;

@Component
public class FrontendWebSocketHandler extends TextWebSocketHandler {
    private final CopyOnWriteArrayList<WebSocketSession> sessions = new CopyOnWriteArrayList<>();


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
        System.out.println("Hey Received Message: " + payload);

        // 특정 메시지 ("subscribe")를 수신한 사용자 세션만 추가
        if (payload.equals("subscribe")) {
            sessions.add(session);
            System.out.println("User Subscribed: " + session.getId());
        }
    }

//    public void broadcast(String message) {
//        sessions.forEach(session -> {
//            try {
//                session.sendMessage(new TextMessage(message));
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        });
//    }

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