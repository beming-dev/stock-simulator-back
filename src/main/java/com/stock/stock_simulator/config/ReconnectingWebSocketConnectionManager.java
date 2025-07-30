package com.stock.stock_simulator.config;

import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.WebSocketHandlerDecorator;
import org.springframework.web.socket.handler.WebSocketHandlerDecoratorFactory;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.WebSocketConnectionManager;
import org.springframework.web.socket.CloseStatus;

public class ReconnectingWebSocketConnectionManager
        extends WebSocketConnectionManager {

    public ReconnectingWebSocketConnectionManager(
            WebSocketClient client,
            WebSocketHandler handler,
            String uriTemplate) {
        super(client, handler, uriTemplate);
    }

    @Override
    protected WebSocketHandler decorateWebSocketHandler(WebSocketHandler handler) {
        // 핸들러에 데코레이터를 씌워, 연결 종료 시 재시작하도록
        return new WebSocketHandlerDecorator(handler) {
            @Override
            public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
                super.afterConnectionClosed(session, status);
                // 연결이 닫히면 즉시 재연결
                ReconnectingWebSocketConnectionManager.this.stop();
                ReconnectingWebSocketConnectionManager.this.start();
            }
        };
    }
}
