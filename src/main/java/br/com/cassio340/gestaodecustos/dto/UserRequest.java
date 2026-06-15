package br.com.cassio340.gestaodecustos.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserRequest {

    @NotBlank(message = "Name is required")
    private String name;


    @NotBlank ( message = "Email is required")
    @Email (message = "The email address must be valid.")
    private String email;


    @NotBlank(message = "Password is required")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d).+$",
    message = "Password must contain at least one letter and one number")
    @Size(min = 6, max = 100, message = "Password must be between 8 and 100 characters")
    private String password;



}
