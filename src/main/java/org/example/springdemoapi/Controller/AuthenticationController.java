package org.example.springdemoapi.Controller;

import com.nimbusds.jose.JOSEException;
import lombok.RequiredArgsConstructor;
import org.example.springdemoapi.Dto.ApiResponse;
import org.example.springdemoapi.Dto.Request.AuthenticationRequest;
import org.example.springdemoapi.Dto.Request.IntrospectRequest;
import org.example.springdemoapi.Dto.Request.LogOutRequest;
import org.example.springdemoapi.Dto.Request.RefreshRequest;
import org.example.springdemoapi.Dto.Response.AuthenticationResponse;
import org.example.springdemoapi.Dto.Response.IntrospectResponse;
import org.example.springdemoapi.Service.AuthenticationService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/log-in")
    ApiResponse<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request){
        var result = authenticationService.authenticate(request);

        return ApiResponse.<AuthenticationResponse>builder()
                .data(result)
                .build();
    }

    @PostMapping("/introspect-token")
    ApiResponse<IntrospectResponse> checkVerifyToken(@RequestBody IntrospectRequest request) throws ParseException, JOSEException {
        var result = authenticationService.introspect(request);

        return ApiResponse.<IntrospectResponse>builder()
                .data(result)
                .build();
    }

    @PostMapping("/refresh-token")
    ApiResponse<AuthenticationResponse> checkVerifyToken(@RequestBody RefreshRequest request) throws ParseException, JOSEException {
        var result = authenticationService.refreshToken(request);

        return ApiResponse.<AuthenticationResponse>builder()
                .data(result)
                .build();
    }

    @PostMapping("/log-out")
    ApiResponse<Void> logout(@RequestBody LogOutRequest request) throws ParseException, JOSEException{
        authenticationService.logOut(request);
        return ApiResponse.<Void>builder()
                .build();
    }
}
