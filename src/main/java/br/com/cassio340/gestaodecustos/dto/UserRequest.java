package br.com.cassio340.gestaodecustos.dto;

import br.com.cassio340.gestaodecustos.entities.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserRequest {
    private String name;
    private String email;
    private String password;



}
