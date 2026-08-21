package com.ezsender.client.rest;

import com.ezsender.client.rest.dtos.request.MultiUserSubscribeRequest;
import com.ezsender.client.rest.dtos.request.MultiUserUnSubscribeRequest;
import com.ezsender.client.rest.dtos.request.SingleUserSubscribeRequest;
import com.ezsender.client.rest.dtos.request.SingleUserUnSubscribeRequest;
import com.ezsender.client.rest.dtos.response.CommonResponse;

public class EzSenderRestClientDefault implements EzSenderRestClient {
    @Override
    public CommonResponse subscribeSingleUser(SingleUserSubscribeRequest request) {
        // TODO:
        return null;
    }

    @Override
    public CommonResponse unSubscribeSingleUser(SingleUserUnSubscribeRequest request) {
        // TODO:
        return null;
    }

    @Override
    public CommonResponse subscribeMultiUser(MultiUserSubscribeRequest request) {
        // TODO:
        return null;
    }

    @Override
    public CommonResponse unSubscribeMultiUser(MultiUserUnSubscribeRequest request) {
        // TODO:
        return null;
    }
}
