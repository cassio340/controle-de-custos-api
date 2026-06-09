package br.com.cassio340.gestaodecustos.services;

import br.com.cassio340.gestaodecustos.dto.ExpenseRequest;
import br.com.cassio340.gestaodecustos.dto.ExpenseResponse;

import br.com.cassio340.gestaodecustos.exceptions.custom.ResourceNotFoundException;
import br.com.cassio340.gestaodecustos.mapper.ExpenseMapper;
import br.com.cassio340.gestaodecustos.entities.Expense;
import br.com.cassio340.gestaodecustos.entities.User;
import br.com.cassio340.gestaodecustos.respositories.ExpenseRepository;
import br.com.cassio340.gestaodecustos.respositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExpenseService {

    private final ExpenseRepository repository;


    private final UserRepository userRepository;


    private final ExpenseMapper mapper;
    public List<ExpenseResponse> findAll (){

        return repository.findAll().stream().map(e -> mapper.toResponse(e)).toList();
    }


    public ExpenseResponse insert (ExpenseRequest expenseRequest){
        User user = userRepository.findById(expenseRequest.getUserId()).get();

        Expense expense = mapper.toEntity(expenseRequest,user);
        repository.save(expense);
        return mapper.toResponse(expense);
    }
    public ExpenseResponse update (Long id, ExpenseRequest expenseRequest){
        Expense expense = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));

        mapper.updateExpense(expense,expenseRequest);
        repository.save(expense);
        return mapper.toResponse(expense);
    }
    public void delete (Long id){
        repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
        repository.deleteById(id);
    }
}
