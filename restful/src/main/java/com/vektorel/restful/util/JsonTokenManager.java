package com.vektorel.restful.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;
import java.util.OptionalInt;

@Service
public class JsonTokenManager {


    @Value("${benim-configim.secret-key}")
    String secretKey;
    public Optional<String> createToken(Long id){

        String token="";
        Long expiresTime= 1000*60*60l; // 60 dakika

        try {
            token= JWT.create()
                    .withClaim("id",id)
                    .withClaim("email","f.turkmen@gamil")
                    .withClaim("kendime not","ben buraya not ekledim")
                    .withIssuer("vektorel")
                    .withIssuedAt(new Date())
                    .withExpiresAt(new Date(System.currentTimeMillis()+expiresTime))
                    .sign(Algorithm.HMAC512(secretKey));

            return Optional.of(token);

        }catch (Exception e){
            return Optional.of(token);
        }



    }

    public Optional<Long> getIdByToken(String token) {
        try {
            Algorithm algorithm= Algorithm.HMAC512(secretKey);
            JWTVerifier jwtVerifier=JWT.require(algorithm).withIssuer("vektorel").build();
            DecodedJWT decodedJWT= jwtVerifier.verify(token);

            if (decodedJWT==null){
                return Optional.empty();
            }

            Optional<Long> id= Optional.of(decodedJWT.getClaim("id").asLong());

            return id;
        }catch (Exception e){
            throw new RuntimeException();
        }

    }
}
