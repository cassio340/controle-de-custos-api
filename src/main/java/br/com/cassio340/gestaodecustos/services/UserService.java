package br.com.cassio340.gestaodecustos.services;

import br.com.cassio340.gestaodecustos.dto.UserDTO;
import br.com.cassio340.gestaodecustos.respositorys.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository repository;


    public List<UserDTO> findAll () {
        return repository.findAll().stream().map(UserDTO::new).toList();
    }

    public UserDTO findById(Long id){

        UserDTO userdto = new UserDTO(repository.findById(id).get());
        return userdto;
    }
}
