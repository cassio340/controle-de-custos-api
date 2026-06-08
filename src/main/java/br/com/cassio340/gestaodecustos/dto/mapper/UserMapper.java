package br.com.cassio340.gestaodecustos.dto.mapper;

import br.com.cassio340.gestaodecustos.dto.ExpenseResponse;
import br.com.cassio340.gestaodecustos.dto.UserRequest;
import br.com.cassio340.gestaodecustos.dto.UserResponse;
import br.com.cassio340.gestaodecustos.entities.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Component
@RequiredArgsConstructor
public class UserMapper {

    private final ExpenseMapper expenseMapper;

    public UserResponse toResponse (User user){
        List<ExpenseResponse>  list = user.getExpenses().
                stream().map(e-> expenseMapper.toDto(e)).toList();
        return new UserResponse(user.getId(), user.getName(),list);
    }
    public User UserRequest (UserRequest userRequest){
        return new User (userRequest.getName(),userRequest.getEmail(), userRequest.getPassword());

    }
    public void updateUser (User user, UserRequest userRequest){
        user.setName(userRequest.getName());
        user.setEmail(userRequest.getEmail());
        user.setPassword(userRequest.getPassword());

    }
}
