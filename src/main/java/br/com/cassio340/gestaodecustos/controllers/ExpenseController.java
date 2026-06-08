package br.com.cassio340.gestaodecustos.controllers;

import br.com.cassio340.gestaodecustos.dto.ExpenseRequest;
import br.com.cassio340.gestaodecustos.dto.ExpenseResponse;
import br.com.cassio340.gestaodecustos.services.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("expenses")
public class ExpenseController {
    @Autowired
    private ExpenseService service;

    @GetMapping
    public ResponseEntity <List<ExpenseResponse>> findALL (){
        List <ExpenseResponse> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @PostMapping
    public ResponseEntity <ExpenseResponse> insert (@RequestBody ExpenseRequest expenseRequest){
        ExpenseResponse response  = service.insert(expenseRequest);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").
                buildAndExpand(response.getId()).toUri();
        return ResponseEntity.created(uri).body(response);
    }

}
