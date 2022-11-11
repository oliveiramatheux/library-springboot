package com.microservices.services;

import com.microservices.entities.AuthLogin;
import com.microservices.entities.AuthRequest;
import com.microservices.entities.AuthResponse;
import com.microservices.entities.value_objects.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.web.client.RestTemplate;

@Service
public class AuthService {

    private final RestTemplate restTemplate;
    private final JwtUtil jwt;

    @Autowired
    public AuthService(RestTemplate restTemplate, final JwtUtil jwt) {
        this.restTemplate = restTemplate;
        this.jwt = jwt;
    }

    public AuthResponse register(AuthRequest authRequest) {
        //do validation if user already exists
        authRequest.setPassword(BCrypt.hashpw(authRequest.getPassword(), BCrypt.gensalt(12)));

        UserVO userVO = restTemplate.postForObject("http://bookUser/users", authRequest, UserVO.class);
        Assert.notNull(userVO, "Failed to register user. Please try again later");

        String accessToken = jwt.generate(userVO, "ACCESS");
        String refreshToken = jwt.generate(userVO, "REFRESH");

        return new AuthResponse(accessToken, refreshToken, "Success");
    }

    public AuthResponse login(AuthLogin authLogin) {
        //do validation if user already exists
        UserVO userVO = restTemplate.getForObject("http://bookUser/users/email/"+authLogin.getEmail(), UserVO.class);

        if(userVO != null && BCrypt.checkpw(authLogin.getPassword(), userVO.getPassword())) {
            String accessToken = jwt.generate(userVO, "ACCESS");
            String refreshToken = jwt.generate(userVO, "REFRESH");

            return new AuthResponse(accessToken, refreshToken, "Sucess");
        }
        
        return new AuthResponse(null, null, "Failed to authenticate user");
    }
}
