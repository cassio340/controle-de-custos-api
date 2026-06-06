package br.com.cassio340.gestaodecustos.dto;

import br.com.cassio340.gestaodecustos.entities.Expense;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ExpenseDTO {
    private Long id;
    private String name;
    private BigDecimal amount;

    public ExpenseDTO(Expense expense){
        id = expense.getId();
        name = expense.getName();
        amount = expense.getAmount();
    }
}
