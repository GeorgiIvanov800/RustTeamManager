package org.rtm.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.*;
import org.rtm.model.dto.request.SaveSleeveRequest;
import org.rtm.model.dto.response.SleeveResponse;
import org.rtm.model.entity.Sleeve;

@Mapper(componentModel = "spring")
public interface SleeveMapper {

    @Mapping(target = "warehouse", ignore = true)
    Sleeve toEntity(SaveSleeveRequest request);

    SleeveResponse toResponse(Sleeve sleeve);

}
