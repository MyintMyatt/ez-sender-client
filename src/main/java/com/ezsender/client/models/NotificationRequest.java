package com.ezsender.client.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.OffsetDateTime;
import java.util.Locale;
import java.util.Map;

public record NotificationRequest(
        @JsonProperty("id") String id,

        // User id or email
        @JsonProperty("recipient") String recipient,

        @JsonProperty("priority") int priority,

        @JsonProperty("client_time") OffsetDateTime clientTime,

        @JsonProperty("template") String template,

        @JsonProperty("locale") Locale locale,

        @JsonProperty("subject") String subject,

        @JsonProperty("data")
        @JsonInclude(JsonInclude.Include.NON_EMPTY)
        Map<String, Object> data
) {}

enum NotificationPriority{

}

