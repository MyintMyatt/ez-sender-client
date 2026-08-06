package com.ezsender.client.rest.dtos.request;

import jakarta.validation.constraints.NotBlank;

public record SingleUserUnSubscribeRequest(
    @NotBlank(message = "topic is required") String topic,
    @NotBlank(message = "username is required") String username
) {
}
