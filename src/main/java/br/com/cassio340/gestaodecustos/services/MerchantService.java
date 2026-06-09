package br.com.cassio340.gestaodecustos.services;

import br.com.cassio340.gestaodecustos.dto.MerchantRequest;
import br.com.cassio340.gestaodecustos.dto.MerchantResponse;
import br.com.cassio340.gestaodecustos.entities.Merchant;
import br.com.cassio340.gestaodecustos.exceptions.custom.DataBaseException;
import br.com.cassio340.gestaodecustos.exceptions.custom.ResourceNotFoundException;
import br.com.cassio340.gestaodecustos.mapper.MerchantMapper;
import br.com.cassio340.gestaodecustos.respositories.MerchantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
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

    public MerchantResponse findById (Long id){
        Merchant merchant = repository.findById(id).orElseThrow(()-> new ResourceNotFoundException(id));
        return mapper.toResponse(merchant);
    }
    public MerchantResponse insert (MerchantRequest request){
        Merchant merchant = mapper.toEntity(request);
        repository.save(merchant);
        return mapper.toResponse(merchant);
    }
    public MerchantResponse update (Long id, MerchantRequest request){
        Merchant merchant = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
        mapper.update(merchant,request);
        repository.save(merchant);
        return mapper.toResponse(merchant);
    }
    public void delete (Long id){
        repository.findById(id).orElseThrow(()-> new ResourceNotFoundException(id));
        try {
            repository.deleteById(id);
            repository.flush();
        }
        catch (DataIntegrityViolationException e) {
            throw new DataBaseException("Cannot delete user because it has related expenses");
        }
        catch (InvalidDataAccessApiUsageException e) {
            throw new DataBaseException("Cannot delete user because it has related expenses");
        }
    }
}
