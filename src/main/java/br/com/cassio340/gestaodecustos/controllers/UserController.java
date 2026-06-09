package br.com.cassio340.gestaodecustos.controllers;

import br.com.cassio340.gestaodecustos.dto.UserRequest;
import br.com.cassio340.gestaodecustos.dto.UserResponse;
import br.com.cassio340.gestaodecustos.entities.User;
import br.com.cassio340.gestaodecustos.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService service;

    @GetMapping
    public ResponseEntity <List<UserResponse>> findAll(){
        List <UserResponse> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }
    @GetMapping("/{id}")
    public ResponseEntity <UserResponse> findById (@PathVariable Long id){
        UserResponse userResponse = service.findById(id);
        return ResponseEntity.ok().body(userResponse);
    }


    @PostMapping
    public ResponseEntity<UserResponse> insert (@RequestBody UserRequest userRequest){
        UserResponse response = service.insert(userRequest);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().
                path("/{id}").buildAndExpand(response.getId()).toUri();
        return ResponseEntity.created(uri).body(response);

    }
    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> update (@PathVariable Long id, @RequestBody UserRequest userRequest){
        UserResponse userResponse = service.update(id, userRequest);

        return ResponseEntity.ok().body(userResponse);


    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete (@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
