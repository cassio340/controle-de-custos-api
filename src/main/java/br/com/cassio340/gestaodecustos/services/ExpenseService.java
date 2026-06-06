package br.com.cassio340.gestaodecustos.services;

import br.com.cassio340.gestaodecustos.dto.ExpenseDTO;

import br.com.cassio340.gestaodecustos.respositorys.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpenseService {
    @Autowired
    private ExpenseRepository repository;

    public List<ExpenseDTO> findAll (){
        return repository.findAll().stream().map(ExpenseDTO::new).toList();
    }

}
