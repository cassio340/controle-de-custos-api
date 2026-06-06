package br.com.cassio340.gestaodecustos.dto.mapper;

import br.com.cassio340.gestaodecustos.dto.ExpenseResponse;
import br.com.cassio340.gestaodecustos.entities.Expense;
import org.springframework.stereotype.Component;

@Component
public class ExpenseMapper {
    public  ExpenseResponse toDto (Expense expense){
        return  new ExpenseResponse(expense.getId(), expense.getName(), expense.getAmount());
    }

}
