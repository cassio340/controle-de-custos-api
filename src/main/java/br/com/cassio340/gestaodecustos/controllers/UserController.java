package br.com.cassio340.gestaodecustos.controllers;

import br.com.cassio340.gestaodecustos.dto.UserDTO;
import br.com.cassio340.gestaodecustos.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService service;

    @GetMapping
    public ResponseEntity <List<UserDTO>> findAll(){
        List <UserDTO> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }
    @GetMapping("/{id}")
    public ResponseEntity <UserDTO> findById (@PathVariable Long id){
        UserDTO userDto = service.findById(id);
        return ResponseEntity.ok().body(userDto);
    }
}
