package com.stock.stock_simulator.event;

import org.springframework.context.ApplicationEvent;

public class WebSocketEvent extends ApplicationEvent {
    private final String tr_id;
    private final String tr_key;
    private final String tr_type;

    public WebSocketEvent(Object source, String trId, String trKey, String trType) {
        super(source);
        tr_id = trId;
        tr_key = trKey;
        tr_type = trType;
    }

    public String getTr_id() {
        return tr_id;
    }

    public String getTr_key() {
        return tr_key;
    }

    public String getTr_type() {
        return tr_type;
    }
}