package it.linksmt.rental.controller;

import it.linksmt.rental.dto.CreateUserRequest;
import it.linksmt.rental.dto.LoginUserRequest;
import it.linksmt.rental.entity.UserEntity;

import it.linksmt.rental.exception.ServiceException;
import it.linksmt.rental.service.AuthenticationService;
import it.linksmt.rental.service.JwtService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/auth")
@RestController
public class AuthenticationController {

    private final JwtService jwtService;

    private final AuthenticationService authenticationService;
    public AuthenticationController(JwtService jwtService, AuthenticationService authenticationService) {
        this.jwtService = jwtService;
        this.authenticationService = authenticationService;
    }
    @PostMapping("/signup")
    public ResponseEntity<?> register(@RequestBody CreateUserRequest createUserRequest) {
        try {
            UserEntity registeredUser = authenticationService.signUp(createUserRequest);
            return ResponseEntity.ok().body(registeredUser);
        } catch (ServiceException e) {
                throw e;
        }
    }
    @PostMapping("/login")
    public ResponseEntity<?> authenticate(@RequestBody LoginUserRequest loginUserRequest){
       try {
           UserEntity user = authenticationService.authenticate(loginUserRequest);
           String token = jwtService.generateToken(user);
//        LoginResponse loginResponse=new LoginResponse();
//        loginResponse.setToken(token);
//        loginResponse.setExpiresIn(jwtService.getExpirationTime());
           return ResponseEntity.ok(token);
       }
       catch (ServiceException e) {
           throw e;
       }
    }

}
