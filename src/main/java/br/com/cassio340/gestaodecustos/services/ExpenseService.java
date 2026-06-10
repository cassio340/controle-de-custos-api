package br.com.cassio340.gestaodecustos.services;

import br.com.cassio340.gestaodecustos.dto.ExpenseRequest;
import br.com.cassio340.gestaodecustos.dto.ExpenseResponse;

import br.com.cassio340.gestaodecustos.entities.Merchant;
import br.com.cassio340.gestaodecustos.exceptions.custom.BadRequestException;
import br.com.cassio340.gestaodecustos.exceptions.custom.ResourceNotFoundException;
import br.com.cassio340.gestaodecustos.mapper.ExpenseMapper;
import br.com.cassio340.gestaodecustos.entities.Expense;
import br.com.cassio340.gestaodecustos.entities.User;
import br.com.cassio340.gestaodecustos.repositories.ExpenseRepository;
import br.com.cassio340.gestaodecustos.repositories.MerchantRepository;
import br.com.cassio340.gestaodecustos.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExpenseService {

    private final ExpenseRepository repository;

    private final UserRepository userRepository;

    private final MerchantRepository merchantRepository;

    private final ExpenseMapper mapper;

    public List<ExpenseResponse> findAll (){
        return repository.findAll().stream().map(e -> mapper.toResponse(e)).toList();
    }


    public ExpenseResponse insert (ExpenseRequest expenseRequest){
        // Checks if the required ID fields were provided in the request.
        // If the field is missing or null, a BadRequestException is thrown before querying the database.
        if (expenseRequest.getUserId() == null){
            throw new BadRequestException("userId is required");
        }
        if (expenseRequest.getMerchantId() == null){
            throw new BadRequestException("merchantId is required");
        }

        User user = userRepository.findById(expenseRequest.getUserId()).orElseThrow(
                ()-> new ResourceNotFoundException(expenseRequest.getUserId()));

        Merchant merchant = merchantRepository.findById(expenseRequest.getMerchantId()).orElseThrow(
                () -> new ResourceNotFoundException (expenseRequest.getMerchantId()));

        Expense expense = mapper.toEntity(expenseRequest,user,merchant);


        repository.save(expense);


        return mapper.toResponse(expense);
    }

    public ExpenseResponse update (Long id, ExpenseRequest expenseRequest){

        User user = userRepository.findById(expenseRequest.getUserId()).orElseThrow(
                ()-> new ResourceNotFoundException(expenseRequest.getUserId()));

        Merchant merchant = merchantRepository.findById(expenseRequest.getMerchantId()).orElseThrow(
                () -> new ResourceNotFoundException (expenseRequest.getMerchantId()));

        Expense expense = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));

        mapper.updateExpense(expense,expenseRequest,user,merchant);
        repository.save(expense);
        return mapper.toResponse(expense);
    }

    public void delete (Long id){
        repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
        repository.deleteById(id);
    }
}
