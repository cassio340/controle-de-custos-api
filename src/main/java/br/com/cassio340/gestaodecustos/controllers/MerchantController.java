package br.com.cassio340.gestaodecustos.controllers;

import br.com.cassio340.gestaodecustos.dto.MerchantRequest;
import br.com.cassio340.gestaodecustos.dto.MerchantResponse;
import br.com.cassio340.gestaodecustos.services.MerchantService;
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
@RequestMapping("/merchants")
@RequiredArgsConstructor
public class MerchantController {

    private final MerchantService service;



    @Operation(description = "Find all merchants")
    @ApiResponse(responseCode = "200",description = "Merchants found successfully")
    @GetMapping
    public ResponseEntity<List<MerchantResponse>> findAll(){
        List<MerchantResponse> responses = service.findAll();
        return ResponseEntity.ok().body(responses);
    }


    @Operation(description = "Find merchant by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Merchant found successfully"),
            @ApiResponse(responseCode = "404",description = "Merchant not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<MerchantResponse> findById (@PathVariable Long id){
        MerchantResponse response = service.findById(id);
        return ResponseEntity.ok().body(response);
    }


    @Operation(description = "Create a new merchant")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",description = "Merchant created successfully"),
            @ApiResponse(responseCode = "400",description = "Invalid request data")
    })
    @PostMapping
    public ResponseEntity<MerchantResponse> insert(@Valid @RequestBody MerchantRequest request){
        MerchantResponse response = service.insert(request);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().
                path("/{id}").buildAndExpand(response.getId()).toUri();

        return ResponseEntity.created(uri).body(response);
    }


    @Operation(description = "Updates the data of an existing merchant")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Merchant updated successfully"),
            @ApiResponse(responseCode = "404",description = "Merchant not found"),
            @ApiResponse(responseCode = "400", description = "Invalid request data")
    })
    @PutMapping ("/{id}")
    public ResponseEntity<MerchantResponse> update (@PathVariable Long id, @Valid @RequestBody MerchantRequest request){
        MerchantResponse response = service.update(id,request);
        return ResponseEntity.ok().body(response);
    }


    @Operation(description = "Deletes an existing merchant by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Merchant deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Merchant not found"),
            @ApiResponse(responseCode = "400", description = "Merchant cannot be deleted because it has related expenses")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete (@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
