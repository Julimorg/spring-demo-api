package org.example.springdemoapi.Controller;

import lombok.RequiredArgsConstructor;
import org.example.springdemoapi.Dto.ApiResponse;
import org.example.springdemoapi.Dto.Request.AuthenticationRequest;
import org.example.springdemoapi.Dto.Response.AuthenticationResponse;
import org.example.springdemoapi.Service.AuthenticationService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/log-in")
    ApiResponse<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request){
        boolean result = authenticationService.authenticate(request);

        return ApiResponse.<AuthenticationResponse>builder()
                .data(AuthenticationResponse.builder()
                        .authenticated(result)
                        .build())
                .build();
    }
}
