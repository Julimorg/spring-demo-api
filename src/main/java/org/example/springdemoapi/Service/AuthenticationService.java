package org.example.springdemoapi.Service;

import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSObject;
import com.nimbusds.jwt.JWTClaimsSet;
import lombok.RequiredArgsConstructor;
import org.example.springdemoapi.Dto.Request.AuthenticationRequest;
import org.example.springdemoapi.Dto.Response.AuthenticationResponse;
import org.example.springdemoapi.Enum.ErrorCode.ErrorCode;
import org.example.springdemoapi.Exception.AppException;
import org.example.springdemoapi.Repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        var user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));

        PasswordEncoder passwordEncode = new BCryptPasswordEncoder(10);

         boolean authenticate = passwordEncode.matches(request.getPassword(), user.getPassword());

         if(!authenticate)
             throw  new AppException(ErrorCode.UNAUTHENTICAED);

         return null;

    }

    private String generateToken(String username){

        JWSHeader header = new JWSHeader(JWSAlgorithm.HS512);

        JWTClaimsSet jwtClaimSet = new JWTClaimsSet.Builder()
                .subject(username) // --> dai dien cho user dang nhap
                .build();

        JWSObject jwsObject = new JWSObject();
    }
}
