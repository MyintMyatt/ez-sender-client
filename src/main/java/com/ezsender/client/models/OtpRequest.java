package com.ezsender.client.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public record OtpRequest(
        @JsonProperty("recipient_name")
        String recipientName,
        @JsonProperty("application_name")
        String applicationName,
        @JsonProperty("expried_time")
        String expiredTime,
        @JsonProperty("otp")
        String otp,
        @JsonProperty("otp_purpose")
        String otpPurpose
) {
    public OtpRequest(String recipientName, String expiredTime, String otp, String otpPurpose){
        this(recipientName, "TrackMyVehicle", expiredTime, otp, otpPurpose);
    }
}
