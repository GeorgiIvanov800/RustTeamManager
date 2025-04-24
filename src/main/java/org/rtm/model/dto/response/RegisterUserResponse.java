package org.rtm.model.dto.response;

public record RegisterUserResponse(
        Long id,
        String firstName,
        String lastName,
        Integer personalNumber,
        String createdDate
) {
}
