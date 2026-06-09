package br.com.cassio340.gestaodecustos.controllers;

import br.com.cassio340.gestaodecustos.dto.MerchantRequest;
import br.com.cassio340.gestaodecustos.dto.MerchantResponse;
import br.com.cassio340.gestaodecustos.services.MerchantService;
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

    @GetMapping
    public ResponseEntity<List<MerchantResponse>> findAll(){
        List<MerchantResponse> responses = service.findAll();
        return ResponseEntity.ok().body(responses);
    }
    @PostMapping
    public ResponseEntity<MerchantResponse> insert(@RequestBody MerchantRequest request){
        MerchantResponse response = service.insert(request);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().
                path("/{id}").buildAndExpand(response.getId()).toUri();

        return ResponseEntity.created(uri).body(response);
    }
}
