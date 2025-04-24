package org.rtm.model.dto.request;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.Length;

public record RegisterUserRequest(
        @NotBlank(message = "Vorname ist erforderlich.")
        @Size(max = 30)
        String firstName,

        @NotBlank(message = "Nachname ist erforderlich.")
        @Size(max = 30)
        String lastName,

        @Digits(integer = 8, fraction = 8, message = "Benutzer kann ohne persönliche Nummer nicht registriert werden!")
        Integer personalNumber,

        @NotBlank(message = "Sie müssen ein Passwort angeben.")
        @Length(min = 8, message = "Das Passwort muss mindestens 8 Zeichen lang sein.")
        String password

) {
}
