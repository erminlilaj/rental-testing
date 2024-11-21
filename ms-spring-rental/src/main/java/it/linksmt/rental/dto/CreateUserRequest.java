package it.linksmt.rental.dto;

import it.linksmt.rental.enums.UserType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserRequest {
    private String username;
    private String name;
    private String surname;
    private String email;
    private String password;
    private int age;
    private UserType userType;
}
