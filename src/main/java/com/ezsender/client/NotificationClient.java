package com.ezsender.client;

import dev.orion.grpc.notification.NotificationCommonResponse;
import dev.orion.grpc.notification.NotificationProfileRegisterRequest;
import dev.orion.grpc.notification.NotificationProfileRegisterResponse;
import dev.orion.grpc.notification.OtpNotificationRequest;


public interface NotificationClient {

   NotificationCommonResponse sendOtp(OtpNotificationRequest request);
   NotificationProfileRegisterResponse registerNotificationProfile(NotificationProfileRegisterRequest request);

}
