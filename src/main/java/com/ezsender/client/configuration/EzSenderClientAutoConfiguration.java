package com.ezsender.client.configuration;

import com.ezsender.client.grpc.EzSenderGrpcClient;
import com.ezsender.client.grpc.EzSenderGrpcClientDefault;
import com.ezsender.client.metadata.EzSenderClientProperties;
import com.ezsender.client.metadata.EzSenderRabbitMqProperties;
import com.ezsender.client.metadata.EzSenderRabbitMqQueueMetadata;
import dev.orion.grpc.notification.NotificationServiceGrpc;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
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

@AutoConfiguration
@EnableConfigurationProperties({
        EzSenderClientProperties.class,
        EzSenderRabbitMqProperties.class,
        EzSenderRabbitMqQueueMetadata.class
})
@ConditionalOnClass(GrpcChannelFactory.class)
public class EzSenderClientAutoConfiguration {

    private final EzSenderRabbitMqQueueMetadata mqMetadata;

    public EzSenderClientAutoConfiguration(EzSenderRabbitMqQueueMetadata queueMetadata) {
        this.mqMetadata = queueMetadata;
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

    @Bean
    @ConditionalOnMissingBean
    public EzSenderGrpcClient notificationClient(NotificationServiceGrpc.NotificationServiceStub stub){
        return new EzSenderGrpcClientDefault(stub);
    }

    // rabbitmq config
    @Bean
    @ConditionalOnMissingBean
    public ConnectionFactory notificationConnectionFactory(EzSenderRabbitMqProperties properties){
        var factory = new CachingConnectionFactory();
        factory.setHost(properties.getHost());
        factory.setPort(properties.getPort());
        factory.setUsername(properties.getUsername());
        factory.setPassword(properties.getPassword());
        factory.setVirtualHost(properties.getVirtualHost());

        return factory;
    }


    @Bean
    public TopicExchange notificationExchange(){
        return new TopicExchange(mqMetadata.getExchange(), true, false);
    }

    @Bean
    public Queue smsQueue() {
        return new Queue(mqMetadata.getSmsQueue(), true);
    }

    @Bean
    public Queue pushNotiQueue(){
        return new Queue(mqMetadata.getPushQueue(), true);
    }

    @Bean
    public Queue emailQueue() {
        return new Queue(mqMetadata.getEmailQueue(), true);
    }

    @Bean
    public Binding smsBinding(){
        return BindingBuilder.bind(smsQueue()).to(notificationExchange()).with(mqMetadata.getSmsRoutingKey());
    }

    @Bean
    public Binding emailBinding() {
        return BindingBuilder.bind(emailQueue()).to(notificationExchange()).with(mqMetadata.getEmailRoutingKey());
    }

    @Bean
    public Binding pushNotiBinding(){
        return BindingBuilder.bind(pushNotiQueue()).to(notificationExchange()).with(mqMetadata.getPushRoutingKey());
    }

    @Bean
    public MessageConverter rabbitMessageConverter(){
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
