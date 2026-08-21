package com.ezsender.client.grpc;

import com.ez_sender.grpc.notification.MultiUserSubscribeRequest;
import com.ez_sender.grpc.notification.MultiUserUnSubscribeRequest;
import com.ez_sender.grpc.notification.SingleUserSubscribeRequest;
import com.ez_sender.grpc.notification.SingleUserUnSubscribeRequest;
import dev.orion.grpc.notification.*;
import io.grpc.stub.StreamObserver;

import java.util.concurrent.CompletableFuture;

public class EzSenderGrpcClientDefault implements EzSenderGrpcClient {

    private final NotificationServiceGrpc.NotificationServiceStub asyncStub;

    public EzSenderGrpcClientDefault(NotificationServiceGrpc.NotificationServiceStub asyncStub){
        this.asyncStub = asyncStub;
    }

    @Override
    public NotificationCommonResponse sendOtp(OtpMailRequest request) {
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
        asyncStub.notificationProfileRegister(request, new StreamObserver<>() {
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

    @Override
    public NotificationCommonResponse subscribeSingleUser(SingleUserSubscribeRequest request) {
        var  result = new CompletableFuture<NotificationCommonResponse>();
        asyncStub.subscribeSingleUser(request, new StreamObserver<>() {
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
    public NotificationCommonResponse unSubscribeSingleUser(SingleUserUnSubscribeRequest request) {
        var  result = new CompletableFuture<NotificationCommonResponse>();
        asyncStub.unSubscribeSingleUser(request, new StreamObserver<>() {
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
    public NotificationCommonResponse subscribeMultiUser(MultiUserSubscribeRequest request) {
        var  result = new CompletableFuture<NotificationCommonResponse>();
        asyncStub.subscribeMultiUser(request, new StreamObserver<>() {
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
    public NotificationCommonResponse unSubscribeMultiUser(MultiUserUnSubscribeRequest request) {
        var  result = new CompletableFuture<NotificationCommonResponse>();
        asyncStub.unSubscribeMultiUser(request, new StreamObserver<>() {
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
}
