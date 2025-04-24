package org.rtm.service;

import org.rtm.model.dto.request.RegisterUserRequest;

public interface UserService {

    void registerUser(RegisterUserRequest userRegister);
}
