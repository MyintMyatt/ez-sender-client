package com.ezsender.client.rest;

import com.ezsender.client.rest.dtos.request.MultiUserSubscribeRequest;
import com.ezsender.client.rest.dtos.request.MultiUserUnSubscribeRequest;
import com.ezsender.client.rest.dtos.request.SingleUserSubscribeRequest;
import com.ezsender.client.rest.dtos.request.SingleUserUnSubscribeRequest;
import com.ezsender.client.rest.dtos.response.CommonResponse;

public interface NotificationRestClient {
   CommonResponse subscribeSingleUser(SingleUserSubscribeRequest request);
   CommonResponse unSubscribeSingleUser(SingleUserUnSubscribeRequest request);

   CommonResponse subscribeMultiUser(MultiUserSubscribeRequest request);
   CommonResponse unSubscribeMultiUser(MultiUserUnSubscribeRequest request);
}
