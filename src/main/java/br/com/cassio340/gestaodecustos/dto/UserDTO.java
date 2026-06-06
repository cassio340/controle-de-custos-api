package br.com.cassio340.gestaodecustos.dto;

import br.com.cassio340.gestaodecustos.entities.Expense;
import br.com.cassio340.gestaodecustos.entities.User;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserDTO {
    private Long id;
    private String name;

    private List<ExpenseDTO> expenses = new ArrayList<>();
    public UserDTO(User user){
        id = user.getId();
        name = user.getName();
        for (Expense expense : user.getExpenses()){
            expenses.add(new ExpenseDTO(expense));
        }
    }
}
