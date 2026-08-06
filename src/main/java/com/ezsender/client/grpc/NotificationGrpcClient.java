package com.ezsender.client.grpc;



import com.ez_sender.grpc.notification.MultiUserSubscribeRequest;
import com.ez_sender.grpc.notification.MultiUserUnSubscribeRequest;
import com.ez_sender.grpc.notification.SingleUserSubscribeRequest;
import com.ez_sender.grpc.notification.SingleUserUnSubscribeRequest;
import dev.orion.grpc.notification.NotificationCommonResponse;
import dev.orion.grpc.notification.NotificationProfileRegisterRequest;
import dev.orion.grpc.notification.NotificationProfileRegisterResponse;
import dev.orion.grpc.notification.OtpNotificationRequest;


public interface NotificationGrpcClient {

   NotificationCommonResponse sendOtp(OtpNotificationRequest request);
   NotificationProfileRegisterResponse registerNotificationProfile(NotificationProfileRegisterRequest request);

   NotificationCommonResponse subscribeSingleUser(SingleUserSubscribeRequest request);
   NotificationCommonResponse unSubscribeSingleUser(SingleUserUnSubscribeRequest request);

   NotificationCommonResponse subscribeMultiUser(MultiUserSubscribeRequest request);
   NotificationCommonResponse unSubscribeMultiUser(MultiUserUnSubscribeRequest request);
}
