package com.ezsender.client.grpc;

import com.ezsender.client.NotificationClient;
import io.grpc.stub.StreamObserver;
import dev.orion.grpc.notification.NotificationServiceGrpc;
import dev.orion.grpc.notification.OtpNotificationRequest;
import dev.orion.grpc.notification.NotificationCommonResponse;
import dev.orion.grpc.notification.NotificationProfileRegisterResponse;
import dev.orion.grpc.notification.NotificationProfileRegisterRequest;

import java.util.concurrent.CompletableFuture;

public class NotificationClientDefault implements NotificationClient {

    private final NotificationServiceGrpc.NotificationServiceStub asyncStub;

    public NotificationClientDefault(NotificationServiceGrpc.NotificationServiceStub asyncStub){
        this.asyncStub = asyncStub;
    }

    @Override
    public NotificationCommonResponse sendOtp(OtpNotificationRequest request) {
        var  result = new CompletableFuture<NotificationCommonResponse>();
        asyncStub.sendOtpMail(request, new StreamObserver<>() {
            @Override
            public void onNext(NotificationCommonResponse value) {
                result.complete(value);
            }

            @Override
            public void onError(Throwable t) {
                result.completeExceptionally(t);
            }

            @Override
            public void onCompleted() {

            }
        });
        return result.join();
    }

    @Override
    public NotificationProfileRegisterResponse registerNotificationProfile(NotificationProfileRegisterRequest request) {
        var result = new CompletableFuture<NotificationProfileRegisterResponse>();
        asyncStub.notificationProfileRegister(request, new StreamObserver<NotificationProfileRegisterResponse>() {
            @Override
            public void onNext(NotificationProfileRegisterResponse value) {
                result.complete(value);
            }

            @Override
            public void onError(Throwable t) {
                result.completeExceptionally(t);
            }

            @Override
            public void onCompleted() {
            }
        });

        return result.join();
    }
}
