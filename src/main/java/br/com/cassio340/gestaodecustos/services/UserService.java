package br.com.cassio340.gestaodecustos.services;

import br.com.cassio340.gestaodecustos.dto.UserDto;
import br.com.cassio340.gestaodecustos.entities.User;
import br.com.cassio340.gestaodecustos.respositorys.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository repository;


    public List<UserDto> findAll () {
        return repository.findAll().stream().map(UserDto::new).toList();
    }

    public UserDto findById(Long id){

        UserDto userdto = new UserDto(repository.findById(id).get());
        return userdto;
    }
}
