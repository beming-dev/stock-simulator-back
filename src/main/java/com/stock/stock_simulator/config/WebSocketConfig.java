package com.stock.stock_simulator.config;

import com.stock.stock_simulator.infra.FrontendWebSocketHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    private final FrontendWebSocketHandler frontendWebSocketHandler;

    public WebSocketConfig(FrontendWebSocketHandler frontendWebSocketHandler) {
        this.frontendWebSocketHandler = frontendWebSocketHandler;
    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(frontendWebSocketHandler, "/ws") // WebSocket 엔드포인트
                .setAllowedOriginPatterns("http://localhost:5173", "https://beming-stock.kro.kr");
    }
}