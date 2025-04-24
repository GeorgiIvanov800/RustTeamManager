package org.rtm.model.dto;

public record RegisterUserRequest(
        String firstName,
        String lastName,
        Integer personalNumber,
        String password

) {
}
