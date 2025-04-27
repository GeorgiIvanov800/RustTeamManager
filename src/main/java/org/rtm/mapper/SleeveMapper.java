package org.rtm.mapper;

import org.mapstruct.Mapper;
import org.rtm.model.dto.request.SaveSleeveRequest;
import org.rtm.model.dto.response.SaveSleeveResponse;
import org.rtm.model.entity.Sleeve;

@Mapper(componentModel = "spring")
public interface SleeveMapper {

    Sleeve toEntity(SaveSleeveRequest request);

    SaveSleeveResponse toResponse(Sleeve sleeve);
}
