package com.stock.stock_simulator.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class TestController {
    @MessageMapping("/stock") // 클라이언트에서 메시지를 보낼 경로
    @SendTo("/topic/stock")    // 클라이언트가 구독할 경로
    public YourResponse handleSubscription(YourRequest request) {
        System.out.println("Received subscription request: " + request.getTopic());

        // 서버에서 처리 후 응답 생성
        return new YourResponse("Message to topic: " + request.getTopic());
    }
}

class YourRequest {
    private String topic;

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }
}

class YourResponse {
    private String message;

    public YourResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}