package org.rtm.mapper;

import org.mapstruct.Mapper;
import org.rtm.model.dto.request.RegisterUserRequest;
import org.rtm.model.dto.response.RegisterUserResponse;
import org.rtm.model.entity.User;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toEntity(RegisterUserRequest request);

    RegisterUserResponse toResponse(User response);
}
