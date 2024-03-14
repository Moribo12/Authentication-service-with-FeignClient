package com.geeks4learning.Authenticationservice.Service;

import com.geeks4learning.Authenticationservice.Dto.Request.AuthenticationRequest;
import com.geeks4learning.Authenticationservice.Dto.Request.RefreshTokenRequest;
import com.geeks4learning.Authenticationservice.Dto.Response.AuthenticationResponse;

public interface AuthenticationService {

    public AuthenticationResponse authenticate(AuthenticationRequest request);

//   public void validateToken(String token);
//
//   public AuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest);

}