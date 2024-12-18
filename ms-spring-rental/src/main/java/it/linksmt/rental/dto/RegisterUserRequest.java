package it.linksmt.rental.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import it.linksmt.rental.enums.UserType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterUserRequest {
    private String username;
    private String name;
    private String surname;
    private String email;
    private String password;
    private int age;

}
