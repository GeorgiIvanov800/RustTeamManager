package org.rtm.model.dto.response;

import org.rtm.model.enums.WarehouseName;

public record WarehouseResponse(
        Long id,
        WarehouseName name
) {
}
