package it.linksmt.rental.service;

import it.linksmt.rental.dto.CreateUserRequest;
import it.linksmt.rental.dto.LoginUserRequest;
import it.linksmt.rental.entity.UserEntity;
import it.linksmt.rental.enums.ErrorCode;


import it.linksmt.rental.exception.ServiceException;
import it.linksmt.rental.repository.UserRepository;
import it.linksmt.rental.security.SecurityBean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthenticationService(UserRepository userRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
    }

    public UserEntity signUp(CreateUserRequest createUserRequest) {
        if (userRepository.existsByUsername(createUserRequest.getUsername())) {
            throw new ServiceException(
                    ErrorCode.USER_ALREADY_EXISTS,
                    "User already exists with username: " + createUserRequest.getUsername()
            );
        }

        if (userRepository.existsByEmail(createUserRequest.getEmail())) {
            throw new ServiceException(
                    ErrorCode.USER_ALREADY_EXISTS,
                    "User already exists with email: " + createUserRequest.getEmail()
            );
        }

        try {
            UserEntity user = new UserEntity();
            user.setUsername(createUserRequest.getUsername());
            user.setName(createUserRequest.getName());
            user.setSurname(createUserRequest.getSurname());
            user.setEmail(createUserRequest.getEmail());
            String salt = "salt";
            String password = createUserRequest.getPassword() + salt;
            user.setPassword(passwordEncoder.encode(password));
            user.setAge(createUserRequest.getAge());
            user.setUserType(createUserRequest.getUserType());

            return userRepository.save(user);
        } catch (Exception e) {
            throw new ServiceException(
                    ErrorCode.INTERNAL_SERVER_ERROR,
                    "Error occurred while creating user"
            );
        }
    }
    public UserEntity authenticate(LoginUserRequest loginUserRequest) {
        try {
            String salt = "salt";
            String password = loginUserRequest.getPassword() + salt;

            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginUserRequest.getUsername(),
                            password
                    )
            );

            return userRepository.findByUsername(loginUserRequest.getUsername())
                    .orElseThrow(() -> new ServiceException(
                            ErrorCode.USER_NOT_FOUND,
                            "User not found with username: " + loginUserRequest.getUsername()
                    ));

        } catch (BadCredentialsException e) {
            throw new ServiceException(
                    ErrorCode.INVALID_CREDENTIALS,
                    "Invalid username or password"
            );
        } catch (Exception e) {
            throw new ServiceException(
                    ErrorCode.INTERNAL_SERVER_ERROR,
                    "Authentication failed"
            );
        }
    }

    public boolean isAdmin(SecurityBean currentUser) {
        return currentUser.getAuthorities().stream()
                .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_ADMIN"));
    }
    public Long getCurrentUserId() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new ServiceException(ErrorCode.UNAUTHORIZED_ACCESS,
                        "User not loogged in"))
                .getId();
    }

}
