package br.com.cassio340.gestaodecustos.controllers;

import br.com.cassio340.gestaodecustos.dto.MerchantResponse;
import br.com.cassio340.gestaodecustos.services.MerchantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
