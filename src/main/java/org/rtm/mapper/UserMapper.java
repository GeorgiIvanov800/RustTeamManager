package org.rtm.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.rtm.model.dto.request.RegisterUserRequest;
import org.rtm.model.dto.response.RegisterUserResponse;
import org.rtm.model.entity.User;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toEntity(RegisterUserRequest request);

    @Mapping(source = "createdDate", target = "createdDate", dateFormat = "dd.MM.yyyy")
    RegisterUserResponse toResponse(User response);
}
