package com.lzh.jmeter.commons.core.domain;

import java.io.Serializable;

/**
 * mq消息
 */
public class BaseMqMessage implements Serializable {

    private String routingKey;
    private String exchange;
    private String contents;
    private String title;

    public String getRoutingKey() {
        return routingKey;
    }

    public void setRoutingKey(String routingKey) {
        this.routingKey = routingKey;
    }

    public String getExchange() {
        return exchange;
    }

    public void setExchange(String exchange) {
        this.exchange = exchange;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
