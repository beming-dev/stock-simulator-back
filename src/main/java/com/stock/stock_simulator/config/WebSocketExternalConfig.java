package com.stock.stock_simulator.config;

import com.stock.stock_simulator.infra.FrontendWebSocketHandler;
import com.stock.stock_simulator.infra.WebSocketHandler;
import com.stock.stock_simulator.interfaces.StockApiInterface;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.WebSocketConnectionManager;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class WebSocketExternalConfig implements WebSocketConfigurer {
    private final StockApiInterface stockApi;
    private final WebSocketHandler webSocketHandler;
    private final FrontendWebSocketHandler frontendWebSocketHandler;

    public WebSocketExternalConfig(StockApiInterface stockApi, WebSocketHandler webSocketHandler, FrontendWebSocketHandler frontendWebSocketHandler) {
        this.stockApi = stockApi;
        this.webSocketHandler = webSocketHandler;
        this.frontendWebSocketHandler = frontendWebSocketHandler;
    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        WebSocketClient webSocketClient = new StandardWebSocketClient();

        String webSocketKey = stockApi.getWebSocketKey();
        webSocketHandler.setWebSocketKey(webSocketKey);

        WebSocketConnectionManager connectionManager = new WebSocketConnectionManager(
                webSocketClient, webSocketHandler, "ws://ops.koreainvestment.com:21000"
        );

        connectionManager.setAutoStartup(true);
        connectionManager.start();
    }
}