package org.example.springdemoapi.Service;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import lombok.RequiredArgsConstructor;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;
import org.example.springdemoapi.Dto.Request.AuthenticationRequest;
import org.example.springdemoapi.Dto.Request.IntrospectRequest;
import org.example.springdemoapi.Dto.Request.LogOutRequest;
import org.example.springdemoapi.Dto.Response.AuthenticationResponse;
import org.example.springdemoapi.Dto.Response.IntrospectResponse;
import org.example.springdemoapi.Entity.InvalidatedToken;
import org.example.springdemoapi.Entity.User;
import org.example.springdemoapi.Enum.ErrorCode.ErrorCode;
import org.example.springdemoapi.Exception.AppException;
import org.example.springdemoapi.Repository.InvalidatedTokenRepository;
import org.example.springdemoapi.Repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import java.text.ParseException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.StringJoiner;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;

    private final InvalidatedTokenRepository InvalidatedTokenRepository;

    @NonFinal
    protected  static final String SIGNER_KEY = "7aW5J8WZ0ck0TEg+OnxjCfK8dBqTkOjcKEEVM1UWufP3XZNfXTIcF/CBL+DyYJ52";
    private final InvalidatedTokenRepository invalidatedTokenRepository;


    //? Check verify Token
    public IntrospectResponse introspect(IntrospectRequest request) throws ParseException, JOSEException {
        var token = request.getToken();

        verifyToken(token);

        boolean isValid = true;
        try {
            verifyToken(token);
            isValid = false;
        }
        catch(AppException e){
            return IntrospectResponse.builder()
                    .valid(isValid)
                    .build();
        }

        return IntrospectResponse.builder()
                .valid(isValid)
                .build();

    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        var user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));

        PasswordEncoder passwordEncode = new BCryptPasswordEncoder(10);

         boolean authenticate = passwordEncode.matches(request.getPassword(), user.getPassword());

         if(!authenticate)
             throw  new AppException(ErrorCode.UNAUTHENTICAED);

         var token = generateToken(user);

         return AuthenticationResponse.builder()
                 .token(token)
                 .authenticated(true)
                 .build();

    }


    private String generateToken(User user){

        JWSHeader header = new JWSHeader(JWSAlgorithm.HS512);

        JWTClaimsSet jwtClaimSet = new JWTClaimsSet.Builder()
                .subject(user.getUsername()) // --> dai dien cho user dang nhap
                .issuer("devteria.com") // --> dinh danh token
                .issueTime(new Date())
                .expirationTime(new Date(
                        Instant.now().plus(1, ChronoUnit.HOURS ).toEpochMilli()
                ))
                .jwtID(UUID.randomUUID().toString())
                .claim("scope", buildScope(user))
                .build();

        Payload payload = new Payload(jwtClaimSet.toJSONObject());

        JWSObject jwsObject = new JWSObject(header, payload);

        //? Sign Token voi thuat toan MACSigner
        try {
            jwsObject.sign(new MACSigner(SIGNER_KEY));
            return jwsObject.serialize();
        } catch (JOSEException e) {
            log.error("Cannot create token", e);
            throw new RuntimeException(e);
        }

    }
    private String buildScope(User user){
        StringJoiner stringJoiner = new StringJoiner(" ");
        if(!CollectionUtils.isEmpty(user.getRoles()))
            user.getRoles().forEach(role -> {
                stringJoiner.add("ROLE_" + role.getName());
                if(!CollectionUtils.isEmpty(role.getPermissions()))
                {
                    role.getPermissions()
                            .forEach(permission -> stringJoiner.add(permission.getName()));
                }
            });

        return stringJoiner.toString();
    }

    private SignedJWT verifyToken(String token) throws JOSEException, ParseException {

        JWSVerifier verifier = new MACVerifier(SIGNER_KEY.getBytes());

        SignedJWT signedJWT = SignedJWT.parse(token);

        Date expiryTime = signedJWT.getJWTClaimsSet().getExpirationTime();

        var verified = signedJWT.verify(verifier);

        if(!verified && expiryTime.after(new Date()))
            throw new AppException(ErrorCode.UNAUTHENTICAED);


        if(invalidatedTokenRepository.existsById(signedJWT.getJWTClaimsSet().getJWTID()))
            throw new AppException(ErrorCode.UNAUTHENTICAED);


        return signedJWT;
    }

    public void logOut(LogOutRequest request) throws ParseException, JOSEException {
        var signToken = verifyToken(request.getToken());

        String jit = signToken.getJWTClaimsSet().getJWTID();

        Date expiryTime = signToken.getJWTClaimsSet().getExpirationTime();

        InvalidatedToken invalidatedToken = InvalidatedToken.builder()
                .id(jit)
                .expiryDate(expiryTime)
                .build();

        invalidatedTokenRepository.save(invalidatedToken);
    }
}
