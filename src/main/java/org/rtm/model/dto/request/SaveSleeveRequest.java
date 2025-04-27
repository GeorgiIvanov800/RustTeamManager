package org.rtm.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import org.rtm.model.entity.Warehouse;
import org.rtm.model.enums.SleeveCondition;
import org.rtm.model.enums.SleeveType;

import java.time.LocalDate;

public record SaveSleeveRequest(
        @NotNull(message = "") //Satznummer
        Integer sequenceNumber,

        @NotNull(message = "")
        Integer sleeveNumber,

        @NotBlank(message = "")
        String design,

        @NotBlank(message = "")
        String color,

        @NotBlank(message = "")
        String manufacturer,

        String notes,

        @NotNull(message = "") //Zahnrad
        Integer gear,

        @NotNull(message = "") // Umfang
        Integer circumference,

        @NotNull(message = "")
        Integer slot,

        @NotNull(message = "")
        @PastOrPresent(message = "")
        LocalDate manufactureDate,

        @NotNull(message = "")
        Integer width,

        Long kmStand,

        @NotNull(message = "")
        Warehouse warehouse,

        String status,

        @NotBlank(message = "")
        SleeveType type,

        @NotBlank(message = "")
        SleeveCondition condition
) {
}
