package org.rtm.model.dto.request;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.Length;

public record RegisterUserRequest(
        @NotBlank(message = "First name is required.")
        @Size(max = 30)
        String firstName,

        @NotBlank(message = "Last name is required.")
        @Size(max = 30)
        String lastName,

        @Digits(integer = 8, fraction = 0, message = "Can't register user without personal number!")
        Integer personalNumber,

        @NotBlank(message = "You must provide a password.")
        @Length(min = 8, message = "Password must be at least 8 characters long.")
        String password

) {
}
