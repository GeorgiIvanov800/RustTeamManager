package org.rtm.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record RegisterUserRequest(
        @NotBlank(message = "First name is required.")
        String firstName,

        @NotBlank(message = "Last name is required.")
        String lastName,

        @NotBlank(message = "Can't register user without personal number!")
        Integer personalNumber,

        @NotBlank(message = "You must provide a password.")
        @Length(min = 8, message = "Password must be at least 8 characters long.")
        String password

) {
}
