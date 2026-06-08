package br.com.cassio340.gestaodecustos.services;

import br.com.cassio340.gestaodecustos.dto.UserRequest;
import br.com.cassio340.gestaodecustos.dto.UserResponse;
import br.com.cassio340.gestaodecustos.dto.mapper.UserMapper;
import br.com.cassio340.gestaodecustos.entities.User;
import br.com.cassio340.gestaodecustos.respositorys.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository repository;

    @Autowired
    private UserMapper mapper;

    public List<UserResponse> findAll () {
        return repository.findAll().stream().map(mapper::toResponse).toList();
    }

    public UserResponse findById(Long id){
        return mapper.toResponse(repository.findById(id).get());
    }

    public UserResponse insert (UserRequest userRequest){
        User user = repository.save(mapper.UserRequest(userRequest));

        return  mapper.toResponse(user);
    }
    public UserResponse update (Long id, UserRequest userRequest){
        User user = repository.findById(id).get();
        mapper.updateUser(user,userRequest);

        repository.save(user);
        return mapper.toResponse(user);

    }
    public void delete (Long id){
        repository.deleteById(id);

    }

}
