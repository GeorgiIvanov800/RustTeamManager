package org.rtm.model.dto.request;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.Length;

public record RegisterUserRequest(
        @NotBlank(message = "{user.firstName.required}")
        @Size(max = 30)
        String firstName,

        @NotBlank(message = "{user.lastName.required}")
        @Size(max = 30)
        String lastName,

        @Pattern(regexp = "\\d{8}", message = "{user.personalNumber.required}") //TODO ask exactly how many digit have the Personal number
        String personalNumber,

        @NotBlank(message = "{user.password.required}")
        @Length(min = 8, message = "{user.password.length}")
        String password

) {
}
