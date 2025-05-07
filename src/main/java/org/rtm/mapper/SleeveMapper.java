package org.rtm.mapper;

import org.mapstruct.*;
import org.rtm.model.dto.request.SaveSleeveRequest;
import org.rtm.model.dto.response.SleeveResponse;
import org.rtm.model.entity.Sleeve;

@Mapper(componentModel = "spring")
public interface SleeveMapper {

    Sleeve toEntity(SaveSleeveRequest request);

    SleeveResponse toResponse(Sleeve sleeve);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateSleeve(Sleeve sleeve);
}
