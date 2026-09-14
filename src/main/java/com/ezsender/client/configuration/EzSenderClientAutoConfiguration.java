package com.ezsender.client.configuration;

import com.ezsender.client.grpc.EzSenderGrpcClient;
import com.ezsender.client.grpc.EzSenderGrpcClientDefault;
import com.ezsender.client.metadata.EzSenderClientProperties;
import com.ezsender.client.metadata.EzSenderRabbitMqProperties;
import com.ezsender.client.metadata.EzSenderRabbitMqMetadata;
import dev.orion.grpc.notification.NotificationServiceGrpc;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.JacksonJsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.grpc.client.GrpcChannelFactory;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

@AutoConfiguration
@EnableConfigurationProperties({
        EzSenderClientProperties.class,
        EzSenderRabbitMqProperties.class,
})
@ConditionalOnClass(GrpcChannelFactory.class)
public class EzSenderClientAutoConfiguration {

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

    @Bean
    @ConditionalOnMissingBean
    public EzSenderGrpcClient notificationClient(NotificationServiceGrpc.NotificationServiceStub stub){
        return new EzSenderGrpcClientDefault(stub);
    }

    // rabbitmq config
    @Bean
    @ConditionalOnMissingBean
    public ConnectionFactory notificationConnectionFactory(EzSenderRabbitMqProperties properties){
        var factory = new com.rabbitmq.client.ConnectionFactory();
        try{
            factory.useSslProtocol();
        } catch (NoSuchAlgorithmException | KeyManagementException e) {
            throw new RuntimeException(e);
        }

        factory.setHost(properties.getHost());
        factory.setPort(properties.getPort());
        factory.setUsername(properties.getUsername());
        factory.setPassword(properties.getPassword());
        factory.setVirtualHost(properties.getVirtualHost());
        factory.setAutomaticRecoveryEnabled(false);

        return new CachingConnectionFactory(factory);
    }


    @Bean
    @ConditionalOnMissingBean
    public MessageConverter messageConverter() {
        return new JacksonJsonMessageConverter();
    }

    @Bean
    @ConditionalOnMissingBean
    public RabbitTemplate notificationRabbitMqTemplate(ConnectionFactory factory, MessageConverter converter) {
        var template = new RabbitTemplate(factory);
        template.setMessageConverter(converter);
        return template;
    }
}
