package com.amar.productservice.commons;

import com.amar.productservice.dto.UserDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class AuthenticateCommon {

    private RestTemplate restTemplate;

    public AuthenticateCommon(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    public UserDto validateToken(String token){
        ResponseEntity<UserDto> response = restTemplate.postForObject("http://localhost:8181/users/validate", token,null, UserDto.class);
        if( response.getBody() == null){
            return null;
        }
        return response.getBody();
    }
}
