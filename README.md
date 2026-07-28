## EzSender Client

**ez-sender-client** is a Spring Boot library that enables applications to communicate with the EzSender Service (Notification Service) using gRPC and REST

### Features
- Auto-configured Spring Boot integration
- gRPC client for communicating with the [EzSender Service](https://github.com/MyintMyatt/ez-sender)
- REST client support
- Configurable gRPC channel name

### Configuration
By default, the library uses the gRPC channel named **noti-service**.
Default Configuration
```properties
spring.grpc.client.channels.noti-service.address=static://localhost:9090
```

#### Custom Channel Name (Optional)

If you want to use a different Spring gRPC channel, configure the following property:

```properties
ez-sender.client.channel=my-channel
spring.grpc.client.channels.my-channel.address=static://localhost:9090
```