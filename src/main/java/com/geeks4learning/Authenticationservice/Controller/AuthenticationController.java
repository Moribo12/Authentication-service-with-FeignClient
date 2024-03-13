package com.geeks4learning.Authenticationservice.Controller;

import com.geeks4learning.Authenticationservice.Dto.Request.AuthenticationRequest;
import com.geeks4learning.Authenticationservice.Dto.Response.AuthenticationResponse;
import com.geeks4learning.Authenticationservice.Response.ResponseObject;
import com.geeks4learning.Authenticationservice.Service.AuthenticationServiceImp;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationServiceImp authenticationServiceImp;
    private final PasswordEncoder passwordEncoder;


    @PostMapping("/authenticate")
    public ResponseObject<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request){
    try {
         return new ResponseObject<>(200, "Authenticated Successfully", this.authenticationServiceImp.authenticate(request));
      }catch (Exception e){
        e.printStackTrace();
    }
    return new ResponseObject<>(401,"Authentication failed!",null);
    }

    @GetMapping("/{password}")
    public String returnEncodedPassword(@PathVariable String password){
        return passwordEncoder.encode(password);
    }

//    @GetMapping("/validate/{token}")
//    public ResponseEntity<String> validateToken(@PathVariable String token) {
//        try {
//            this.authenticationServiceImp.validateToken(token);
//            return new ResponseEntity<>("Token is valid",HttpStatus.OK);
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//        return new ResponseEntity<>("Token is Invalid",HttpStatus.BAD_REQUEST);
//     }
//
//    @PostMapping("/refresh")
//    public ResponseObject<AuthenticationResponse> refresh(@RequestBody RefreshTokenRequest refreshTokenRequest){
//        try {
//            return new ResponseObject<>(200, "Refreshed Successfully", this.authenticationServiceImp.refreshToken(refreshTokenRequest));
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//        return new ResponseObject<>(401,"Failed to refresh",null);
//    }



}
