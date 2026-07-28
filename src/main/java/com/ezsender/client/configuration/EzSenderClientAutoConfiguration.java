package com.ezsender.client.configuration;

import com.ezsender.client.NotificationClient;
import com.ezsender.client.grpc.NotificationClientDefault;
import dev.orion.grpc.notification.NotificationServiceGrpc;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.grpc.client.GrpcChannelFactory;

@AutoConfiguration
@EnableConfigurationProperties(EzSenderClientProperties.class)
@ConditionalOnClass(GrpcChannelFactory.class)
@ConditionalOnBean(GrpcChannelFactory.class)
public class EzSenderClientAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public NotificationClient notificationClient(NotificationServiceGrpc.NotificationServiceStub stub){
        return new NotificationClientDefault(stub);
    }

    @Bean
    @ConditionalOnMissingBean
    public NotificationServiceGrpc.NotificationServiceStub notificationServiceStub(GrpcChannelFactory factory, EzSenderClientProperties properties) {
        try {
            return NotificationServiceGrpc.newStub(
                    factory.createChannel(properties.getChannel()));
        } catch (Exception ex) {
            throw new IllegalStateException(
                    "No Spring gRPC channel named '" + properties.getChannel()
                            + "' has been configured. Configure it under "
                            + "'spring.grpc.client.channels." + properties.getChannel() + "'.",
                    ex);
        }
    }

}
