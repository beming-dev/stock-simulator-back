package com.stock.stock_simulator.infra;

import com.google.gson.Gson;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.HashMap;
import java.util.Map;


public class WebSocketHandler extends TextWebSocketHandler {
    private String webSocketKey;
    private WebSocketSession session;

    public WebSocketHandler(String webSocketKey) {
        this.webSocketKey = webSocketKey;
    }

    @Override // 웹 소켓 연결시
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        System.out.println("WebSocket Connected: " + session.getId());
        this.session = session;

        this.sendMessage();
    }

    private void sendMessage() throws Exception {
        if (session == null || !session.isOpen()) {
            System.out.println("WebSocket session is not open or is null.");
            return;
        }

        // Gson 객체 생성
        Gson gson = new Gson();

        // Header 작성
        Map<String, String> header = new HashMap<>();
        header.put("approval_key", webSocketKey); // WebSocket Key
        header.put("custtype", "P"); // 고객 타입: 개인
        header.put("tr_type", "1"); // 거래 타입: 등록
        header.put("content-type", "utf-8"); // Content-Type

        // Input 작성 (Body 내부의 중첩 필드)
        Map<String, String> input = new HashMap<>();
        input.put("tr_id", "H0IFCNT0"); // 거래 ID
        input.put("tr_key", "101S12"); // 거래 Key (종목코드)

        // Body 작성
        Map<String, Object> body = new HashMap<>();
        body.put("input", input);

        // 최종 요청 작성
        Map<String, Object> request = new HashMap<>();
        request.put("header", header);
        request.put("body", body);

        // JSON 직렬화
        String jsonRequest = gson.toJson(request);
        System.out.println("JSON Request: " + jsonRequest);

        // WebSocket 메시지 전송
        session.sendMessage(new TextMessage(jsonRequest));
    }

    @Override // 데이터 통신시
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String payload = message.getPayload();
        System.out.println("Received Message: " + payload);

        if (payload.contains("PINGPONG")) {
            System.out.println("Received PING. Sending PONG...");
            session.sendMessage(new TextMessage("PONG"));
            return;
        }

        String[] stockInfo = payload.split("\\^");
        if(stockInfo.length > 1){
            System.out.println("해당 주식 현재 가격" + stockInfo[1]);
        }
    }

    @Override // 웹소켓 통신 에러시
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        System.out.println("WebSocket Error: " + exception.getMessage());
        exception.printStackTrace();
        super.handleTransportError(session, exception);
    }

    @Override // 웹 소켓 연결 종료시
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        System.out.println("finished");
        super.afterConnectionClosed(session, status);
    }
}
