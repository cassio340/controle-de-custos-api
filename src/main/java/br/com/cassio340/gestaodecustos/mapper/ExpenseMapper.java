package br.com.cassio340.gestaodecustos.mapper;

import br.com.cassio340.gestaodecustos.dto.ExpenseRequest;
import br.com.cassio340.gestaodecustos.dto.ExpenseResponse;
import br.com.cassio340.gestaodecustos.dto.UserRequest;
import br.com.cassio340.gestaodecustos.entities.Expense;
import br.com.cassio340.gestaodecustos.entities.Merchant;
import br.com.cassio340.gestaodecustos.entities.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ExpenseMapper {
    private final MerchantMapper merchantMapper;
    public  ExpenseResponse toResponse (Expense expense){
        return  new ExpenseResponse(expense.getId(), expense.getName(), expense.getAmount(),expense.getCategory(),merchantMapper.toResponse(expense.getMerchant()));
    }

    public Expense toEntity(ExpenseRequest expenseRequest, User user){
        return new Expense( expenseRequest.getName(),
                expenseRequest.getAmount(), expenseRequest.getCategory(), user);
    }

    public void updateExpense (Expense expense,ExpenseRequest expenseRequest){
        expense.setName(expenseRequest.getName());
        expense.setAmount(expenseRequest.getAmount());
        expense.setCategory(expenseRequest.getCategory());


    }

}
