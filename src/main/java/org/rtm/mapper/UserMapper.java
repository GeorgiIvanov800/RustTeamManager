package org.rtm.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.rtm.model.dto.request.RegisterUserRequest;
import org.rtm.model.entity.User;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    User toEntity(RegisterUserRequest dto);
}
