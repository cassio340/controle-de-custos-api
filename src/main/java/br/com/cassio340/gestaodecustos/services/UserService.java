package br.com.cassio340.gestaodecustos.services;

import br.com.cassio340.gestaodecustos.dto.UserRequest;
import br.com.cassio340.gestaodecustos.dto.UserResponse;
import br.com.cassio340.gestaodecustos.exceptions.custom.DataBaseException;
import br.com.cassio340.gestaodecustos.exceptions.custom.ResourceNotFoundException;
import br.com.cassio340.gestaodecustos.mapper.UserMapper;
import br.com.cassio340.gestaodecustos.entities.User;
import br.com.cassio340.gestaodecustos.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;

    private final UserMapper mapper;

    public List<UserResponse> findAll () {
        return repository.findAll().stream().map(mapper::toResponse).toList();
    }

    public UserResponse findById(Long id){
        User user = repository.findById(id).orElseThrow(()-> new ResourceNotFoundException(id));
        return mapper.toResponse(user);
    }

    public UserResponse insert (UserRequest userRequest){
        User user = repository.save(mapper.toEntity(userRequest));
        return  mapper.toResponse(user);
    }

    public UserResponse update (Long id, UserRequest userRequest){
        User user = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));

        mapper.updateUser(user,userRequest);
        repository.save(user);

        return mapper.toResponse(user);
    }

    public void delete (Long id){
        repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));

        try {
            repository.deleteById(id);
        }
        catch (DataIntegrityViolationException e){
            throw new DataBaseException("Cannot delete user because it has related expenses ");
        }

    }

}
