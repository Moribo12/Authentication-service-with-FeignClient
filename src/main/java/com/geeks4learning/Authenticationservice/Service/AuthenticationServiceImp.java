package com.geeks4learning.Authenticationservice.Service;

import com.geeks4learning.Authenticationservice.Config.JwtService;
import com.geeks4learning.Authenticationservice.Dto.Request.AuthenticationRequest;
import com.geeks4learning.Authenticationservice.Dto.Request.RefreshTokenRequest;
import com.geeks4learning.Authenticationservice.Dto.Response.AuthenticationResponse;
import com.geeks4learning.Authenticationservice.FeignClient.UserManagementFeignClient;
import com.geeks4learning.Authenticationservice.Model.UserModel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.HashMap;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthenticationServiceImp implements AuthenticationService {

    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final UserManagementFeignClient userFeignClient;


    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest request) {

        if (userFeignClient == null) {
            throw new IllegalStateException("userFeignClient is not initialized");
        }

        this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));

        var user = this.userFeignClient.getUserByEmail(request.getEmail());

        var jwtToken = this.jwtService.generateToken(user);
        var refreshToken = this.jwtService.generateRefreshToken(new HashMap<>(), user);

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .refreshToken(refreshToken)
                .build();
    }


    //    @Override
//    public void validateToken(String token) {
//        this.jwtService.validateToken(token);
//    }
//    @Override
//    public AuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest) {
//
//        String userEmail = this.jwtService.extractUsername(refreshTokenRequest.getToken());
//        UserModel user = this.userFeignClient.getUserByEmail(userEmail);
//
//        if (this.jwtService.isTokenValid(refreshTokenRequest.getToken(), user)) {
//            var jwt = this.jwtService.generateToken(user);
//
//            return AuthenticationResponse.builder()
//                    .token(jwt)
//                    .refreshToken(refreshTokenRequest.getToken())
//                    .build();
//        }
//        return null;
//    }
}
