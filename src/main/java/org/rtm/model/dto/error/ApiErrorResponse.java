package org.rtm.model.dto.error;

import java.time.Instant;
import java.util.Map;

public record ApiErrorResponse(String code,
                               String message,
                               Instant timestamp,
                               Map<String,Object> details) {
}
