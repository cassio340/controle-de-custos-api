package br.com.cassio340.gestaodecustos.dto.mapper;

import br.com.cassio340.gestaodecustos.dto.ExpenseRequest;
import br.com.cassio340.gestaodecustos.dto.ExpenseResponse;
import br.com.cassio340.gestaodecustos.entities.Expense;
import br.com.cassio340.gestaodecustos.entities.User;
import org.springframework.stereotype.Component;

@Component
public class ExpenseMapper {
    public  ExpenseResponse toResponse (Expense expense){
        return  new ExpenseResponse(expense.getId(), expense.getName(), expense.getAmount());
    }

    public Expense toEntity(ExpenseRequest expenseRequest, User user){
        return new Expense( expenseRequest.getName(),
                expenseRequest.getAmount(), user);
    }
}
