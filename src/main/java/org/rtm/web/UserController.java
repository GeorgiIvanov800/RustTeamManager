package org.rtm.web;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.rtm.model.dto.request.RegisterUserRequest;
import org.rtm.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<?> register(
            @RequestBody @Valid RegisterUserRequest registerUserRequest
    ){
        System.out.println();
        userService.registerUser(registerUserRequest);
        return ResponseEntity.accepted().build();
    }
}
