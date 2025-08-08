package com.stock.stock_simulator.config;

import com.stock.stock_simulator.infra.WebSocketHandler;
import com.stock.stock_simulator.interfaces.StockApiInterface;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;

@Configuration
public class WebSocketExternalConfig {

    private final StockApiInterface stockApi;
    private final WebSocketHandler webSocketHandler;

    public WebSocketExternalConfig(
            StockApiInterface stockApi,
            WebSocketHandler webSocketHandler) {
        this.stockApi = stockApi;
        this.webSocketHandler = webSocketHandler;
    }

    @Bean
    public ReconnectingWebSocketConnectionManager webSocketConnectionManager() {
        // 1) 서버로 보낼 approval_key 세팅
        webSocketHandler.setWebSocketKey(stockApi.getWebSocketKey());

        // 2) URL 은 실제 사용하는 엔드포인트로 바꿔주세요
        String wsUrl = "ws://ops.koreainvestment.com:21000";

        // 3) ReconnectingWebSocketConnectionManager 생성
        ReconnectingWebSocketConnectionManager mgr =
                new ReconnectingWebSocketConnectionManager(
                        new StandardWebSocketClient(),
                        webSocketHandler,
                        wsUrl
                );
        mgr.setAutoStartup(true);

        webSocketHandler.setConnectionManager(mgr);
        return mgr;
    }
}