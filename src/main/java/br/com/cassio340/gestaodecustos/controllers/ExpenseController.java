package br.com.cassio340.gestaodecustos.controllers;

import br.com.cassio340.gestaodecustos.dto.ExpenseRequest;
import br.com.cassio340.gestaodecustos.dto.ExpenseResponse;
import br.com.cassio340.gestaodecustos.services.ExpenseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("expenses")
@RequiredArgsConstructor
public class ExpenseController {

    private final ExpenseService service;



    @Operation(description = "Find all expenses")
    @ApiResponse(responseCode = "200",description = "Expenses found successfully")
    @GetMapping
    public ResponseEntity <List<ExpenseResponse>> findALL (){
        List <ExpenseResponse> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }


    @Operation(description = "Create a new expense")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",description = "Expense created successfully"),
            @ApiResponse(responseCode = "400",description = "Invalid request data"),
            @ApiResponse(responseCode = "404", description = "User or merchant not found")
    })
    @PostMapping
    public ResponseEntity <ExpenseResponse> insert (@Valid @RequestBody ExpenseRequest expenseRequest){

        ExpenseResponse response  = service.insert(expenseRequest);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").
                buildAndExpand(response.getId()).toUri();

        return ResponseEntity.created(uri).body(response);
    }


    @Operation(description = "Updates the data of an existing expense")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Expense updated successfully"),
            @ApiResponse(responseCode = "404", description = "Expense, user or merchant not found"),
            @ApiResponse(responseCode = "400",description = "Invalid request data")
    })
    @PutMapping ("/{id}")
    public ResponseEntity<ExpenseResponse> update (@PathVariable Long id, @Valid @RequestBody ExpenseRequest expenseRequest){
        ExpenseResponse response = service.update(id, expenseRequest);
        return ResponseEntity.ok().body(response);
    }


    @Operation(description = "Deletes an existing expense by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Expense deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Expense not found"),
    })
    @DeleteMapping ("/{id}")
    public ResponseEntity<Void> delete (@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }


}
