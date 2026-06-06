package br.com.cassio340.gestaodecustos.controllers;

import br.com.cassio340.gestaodecustos.dto.UserDto;
import br.com.cassio340.gestaodecustos.entities.User;
import br.com.cassio340.gestaodecustos.services.UserService;
import org.aspectj.apache.bcel.Repository;
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
    public ResponseEntity <List<UserDto>> findAll(){
        List <UserDto> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }
    @GetMapping("/{id}")
    public ResponseEntity <UserDto> findById (@PathVariable Long id){
        UserDto userDto = service.findById(id);
        return ResponseEntity.ok().body(userDto);
    }
}
