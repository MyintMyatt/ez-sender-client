package com.ezsender.client.metadata;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "ez-sender.rabbitmq")
public class EzSenderRabbitMqQueueMetadata {
    private String exchange = "ez.sender.rabbit.exchange";
    private String emailQueue = "ez.sender.rabbit.email.queue";
    private String pushQueue = "ez.sender.rabbit.push.queue";
    private String smsQueue = "ez.sender.rabbit.sms.queue";
    private String emailRoutingKey = "ez.sender.rabbit.email.*";
    private String smsRoutingKey = "ez.sender.rabbit.sms.*";
    private String pushRoutingKey = "ez.sender.rabbit.push.*";

    public String getExchange() {
        return exchange;
    }

    public void setExchange(String exchange) {
        this.exchange = exchange;
    }

    public String getEmailQueue() {
        return emailQueue;
    }

    public void setEmailQueue(String emailQueue) {
        this.emailQueue = emailQueue;
    }

    public String getPushQueue() {
        return pushQueue;
    }

    public void setPushQueue(String pushQueue) {
        this.pushQueue = pushQueue;
    }

    public String getSmsQueue() {
        return smsQueue;
    }

    public void setSmsQueue(String smsQueue) {
        this.smsQueue = smsQueue;
    }

    public String getEmailRoutingKey() {
        return emailRoutingKey;
    }

    public void setEmailRoutingKey(String emailRoutingKey) {
        this.emailRoutingKey = emailRoutingKey;
    }

    public String getSmsRoutingKey() {
        return smsRoutingKey;
    }

    public void setSmsRoutingKey(String smsRoutingKey) {
        this.smsRoutingKey = smsRoutingKey;
    }

    public String getPushRoutingKey() {
        return pushRoutingKey;
    }

    public void setPushRoutingKey(String pushRoutingKey) {
        this.pushRoutingKey = pushRoutingKey;
    }
}
