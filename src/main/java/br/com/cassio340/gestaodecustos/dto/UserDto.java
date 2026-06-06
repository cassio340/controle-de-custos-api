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
public class UserDto {
    private Long id;
    private String name;

    public UserDto (User user){
        id = user.getId();
        name = user.getName();
    }
}
