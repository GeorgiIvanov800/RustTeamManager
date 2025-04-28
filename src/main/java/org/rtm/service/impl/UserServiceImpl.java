package org.rtm.service.impl;

import lombok.RequiredArgsConstructor;
import org.rtm.exception.DuplicatePersonalNumberException;
import org.rtm.mapper.UserMapper;
import org.rtm.model.dto.request.RegisterUserRequest;
import org.rtm.model.dto.response.RegisterUserResponse;
import org.rtm.model.entity.User;
import org.rtm.repository.UserRepository;
import org.rtm.service.UserService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public RegisterUserResponse registerUser(RegisterUserRequest userRegister) {

       if (userRepository.existsByPersonalNumber(Integer.valueOf(userRegister.personalNumber()))) {
           throw new DuplicatePersonalNumberException(userRegister.personalNumber());
       }

        User user = userRepository.save(userMapper.toEntity(userRegister));

        return userMapper.toResponse(user);
    }

}
