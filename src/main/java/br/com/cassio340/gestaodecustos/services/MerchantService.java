package br.com.cassio340.gestaodecustos.services;

import br.com.cassio340.gestaodecustos.dto.MerchantResponse;
import br.com.cassio340.gestaodecustos.mapper.MerchantMapper;
import br.com.cassio340.gestaodecustos.respositories.MerchantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MerchantService {

    private final MerchantRepository repository;


    private final MerchantMapper mapper;

    public List<MerchantResponse> findAll (){
        return repository.findAll().stream().map(e->mapper.toResponse(e)).toList();
    }
}
