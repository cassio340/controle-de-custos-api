package br.com.cassio340.gestaodecustos.controllers;

import br.com.cassio340.gestaodecustos.dto.UserRequest;
import br.com.cassio340.gestaodecustos.dto.UserResponse;
import br.com.cassio340.gestaodecustos.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
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



    @Operation(description = "Find all users")
    @ApiResponse(responseCode = "200",description = "Users found successfully")
    @GetMapping
    public ResponseEntity <List<UserResponse>> findAll(){
        List <UserResponse> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }


    @Operation(description = "Find user by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "User returned successfully"),
            @ApiResponse(responseCode = "404",description = "User not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity <UserResponse> findById (@PathVariable Long id){
        UserResponse userResponse = service.findById(id);
        return ResponseEntity.ok().body(userResponse);
    }


    @Operation(description = "Create a new user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",description = "User created successfully"),
            @ApiResponse(responseCode = "400",description = "Invalid request data")
    })
    @PostMapping
    public ResponseEntity<UserResponse> insert (@Valid @RequestBody UserRequest userRequest){
        UserResponse response = service.insert(userRequest);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().
                path("/{id}").buildAndExpand(response.getId()).toUri();

        return ResponseEntity.created(uri).body(response);
    }


    @Operation(description = "Updates the data of an existing user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "User updated successfully"),
            @ApiResponse(responseCode = "404",description = "User not found"),
            @ApiResponse(responseCode = "400",description = "Invalid request data")
    })
    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> update (@PathVariable Long id, @Valid @RequestBody UserRequest userRequest) {
        UserResponse userResponse = service.update(id, userRequest);
        return ResponseEntity.ok().body(userResponse);
    }


    @Operation(description = "Deletes an existing user by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "User deleted successfully"),
            @ApiResponse(responseCode = "404", description = "User not found"),
            @ApiResponse(responseCode = "400", description = "User cannot be deleted because it has related expenses")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete (@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }


}
