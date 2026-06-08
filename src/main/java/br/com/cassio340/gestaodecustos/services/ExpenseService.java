package br.com.cassio340.gestaodecustos.services;

import br.com.cassio340.gestaodecustos.dto.ExpenseRequest;
import br.com.cassio340.gestaodecustos.dto.ExpenseResponse;

import br.com.cassio340.gestaodecustos.dto.mapper.ExpenseMapper;
import br.com.cassio340.gestaodecustos.entities.Expense;
import br.com.cassio340.gestaodecustos.entities.User;
import br.com.cassio340.gestaodecustos.respositorys.ExpenseRepository;
import br.com.cassio340.gestaodecustos.respositorys.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpenseService {
    @Autowired
    private ExpenseRepository repository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ExpenseMapper mapper;
    public List<ExpenseResponse> findAll (){

        return repository.findAll().stream().map(mapper::toResponse).toList();
    }


    public ExpenseResponse insert (ExpenseRequest expenseRequest){
        User user = userRepository.findById(expenseRequest.getUserId()).get();

        Expense expense = mapper.toEntity(expenseRequest,user);
        repository.save(expense);
        return mapper.toResponse(expense);
    }
    public ExpenseResponse update (Long id, ExpenseRequest expenseRequest){
        Expense expense = repository.findById(id).get();
        mapper.updateExpense(expense,expenseRequest);
        repository.save(expense);
        return mapper.toResponse(expense);
    }
}
