package org.rtm.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import org.rtm.model.enums.SleeveCondition;
import org.rtm.model.enums.SleeveType;

import java.time.LocalDate;

public record SaveSleeveRequest(
        @NotNull(message = "{sleeve.sequenceNumber.required}") //Satznummer
        Integer sequenceNumber,

        @NotNull(message = "{sleeve.sleeveNumber.required}")
        Integer sleeveNumber,

        @NotBlank(message = "{sleeve.design.required}")
        String design,

        @NotBlank(message = "{sleeve.color.required}")
        String color,

        @NotBlank(message = "{sleeve.manufacturer.required}")
        String manufacturer,

        String notes,

        @NotNull(message = "{sleeve.gear.required}") //Zahnrad
        Integer gear,

        @NotNull(message = "{sleeve.circumference.required}") // Umfang
        Integer circumference,

        @NotNull(message = "{sleeve.slot.required}")
        Integer slot,

        @NotNull(message = "{sleeve.manufactureDate.required}")
        @PastOrPresent(message = "{sleeve.manufactureDate.pastOrPresent}")
        LocalDate manufactureDate,

        @NotNull(message = "{sleeve.width.required}")
        Integer width,

        Long kmStand,

        @NotNull(message = "{sleeve.warehouse.required}")
        String warehouse,

        String status,

        @NotBlank(message = "{sleeve.type.required}")
        SleeveType type,

        @NotBlank(message = "{sleeve.condition.required}")
        SleeveCondition condition
) {
}
