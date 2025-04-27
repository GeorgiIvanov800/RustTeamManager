package org.rtm.web;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.rtm.model.dto.request.RegisterUserRequest;
import org.rtm.model.dto.response.RegisterUserResponse;
import org.rtm.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping(path = "/register", consumes = "application/json", produces = "application/json" )
    public ResponseEntity<RegisterUserResponse> registerUser(
            @RequestBody @Valid RegisterUserRequest registerUserRequest
    ){

        RegisterUserResponse userResponse = userService.registerUser(registerUserRequest);

        return ResponseEntity.created(
                ServletUriComponentsBuilder
                        .fromCurrentRequest()
                        .path("/{id}")
                        .buildAndExpand(userResponse.id())
                        .toUri()
        ).body(userResponse);
    }
}
