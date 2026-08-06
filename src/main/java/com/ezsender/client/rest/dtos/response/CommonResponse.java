package com.ezsender.client.rest.dtos.response;

public record CommonResponse(
        boolean success,
        String message
) {
}
