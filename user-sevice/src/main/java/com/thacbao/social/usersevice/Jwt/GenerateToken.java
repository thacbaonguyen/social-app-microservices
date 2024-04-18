package com.thacbao.social.usersevice.Jwt;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jwt.JWTClaimsSet;
import com.thacbao.social.usersevice.dto.request.LoginRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;
import java.util.Date;
@Component
public class GenerateToken {
    @Value("${jwt.secretKey}")
    private String secretKey;
    public String generateToken(LoginRequest request, String role){
        JWSHeader header = new JWSHeader(JWSAlgorithm.HS256);
        Date expirationTime = Date.from(ZonedDateTime.now().plusDays(20).toInstant());
        JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
                .subject(request.getPhoneNumber())
                .issuer("instagram-clone.com")
                .issueTime(new Date())
                .expirationTime(expirationTime)
                .claim("scope", role)
                .build();
        Payload payload = new Payload(jwtClaimsSet.toJSONObject());
        JWSObject jwsObject = new JWSObject(header, payload);
        try {
            jwsObject.sign(new MACSigner(secretKey.getBytes()));
            return jwsObject.serialize();
        } catch (JOSEException e) {
            throw new RuntimeException(e);
        }
    }
}
