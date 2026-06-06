package br.com.cassio340.gestaodecustos.controllers;

import br.com.cassio340.gestaodecustos.dto.ExpenseDTO;
import br.com.cassio340.gestaodecustos.services.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("expenses")
public class ExpenseController {
    @Autowired
    private ExpenseService service;

    @GetMapping
    public ResponseEntity <List<ExpenseDTO>> findALL (){
        List <ExpenseDTO> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }
}
