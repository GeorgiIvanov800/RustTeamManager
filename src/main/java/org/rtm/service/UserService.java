package org.rtm.service;

import org.rtm.model.dto.request.RegisterUserRequest;
import org.rtm.model.dto.response.RegisterUserResponse;

public interface UserService {

    RegisterUserResponse registerUser(RegisterUserRequest userRegister);
}
