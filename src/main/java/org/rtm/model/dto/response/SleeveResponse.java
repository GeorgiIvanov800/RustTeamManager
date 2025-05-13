package org.rtm.model.dto.response;

import org.rtm.model.entity.Warehouse;
import org.rtm.model.enums.SleeveCondition;
import org.rtm.model.enums.SleeveType;

import java.time.LocalDate;

public record SleeveResponse(
        Long id,
        Integer sequenceNumber,
        Integer sleeveNumber,
        String design,
        String color,
        String manufacturer,
        String notes,
        Integer gear,
        Integer circumference,
        Integer slot,
        LocalDate manufactureDate,
        Integer width,
        Long kmStand,
        WarehouseResponse warehouse,
        String status,
        SleeveType type,
        SleeveCondition condition
) {
}
