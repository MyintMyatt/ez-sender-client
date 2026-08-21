package com.ezsender.client.metadata;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "ez-sender.client")
public class EzSenderClientProperties {

    ///
    /// gRPC channel name
    ///
    private String channel = "noti-service";

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }
}
