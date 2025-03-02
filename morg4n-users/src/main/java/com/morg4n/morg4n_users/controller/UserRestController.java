package com.morg4n.morg4n_users.controller;

import com.morg4n.morg4n_users.model.User;
import com.morg4n.morg4n_users.service.IUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * @author Julian David Camacho Erazo (Morg4n) {@literal <jdacamachoe@gmail.com>}
 */
@RestController
@RequestMapping("/api/users/")
@RequiredArgsConstructor
@Tag(name = "Users", description = "User management APIs")
public class UserRestController {
    private final IUserService service;

    @Operation(summary = "List all users", description = "Returns a list of all users")
    @GetMapping("")
    public ResponseEntity<List<User>> index() {
        List<User> users = service.findAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @Operation(summary = "Get user by ID", description = "Returns a user based on its ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "User found"),
            @ApiResponse(responseCode = "500", description = "User not found")
    })
    @GetMapping("{id}")
    public ResponseEntity<User> findUserById(@PathVariable String id) throws Exception {
        User user = service.findById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @Operation(summary = "Save a new user", description = "Creates a new user")
    @ApiResponse(responseCode = "200", description = "User successfully created")
    @PostMapping("")
    public ResponseEntity<User> saveUser(@RequestBody User user) {
        User newUser = service.save(user);
        return new ResponseEntity<>(newUser, HttpStatus.OK);
    }

    @Operation(summary = "Update an existing user", description = "Updates a user's information")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "User successfully updated"),
            @ApiResponse(responseCode = "500", description = "User not found")
    })
    @PutMapping("{id}")
    public  ResponseEntity<User> updateUser(@PathVariable String id, @RequestBody User user) throws  Exception {
        User updatedUser = service.update(id, user);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    @Operation(summary = "Delete user by ID", description = "Deletes a user based on their ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "User successfully deleted"),
            @ApiResponse(responseCode = "500", description = "User not found")
    })
    @DeleteMapping("{id}")
    public ResponseEntity<Boolean> deleteUser(@PathVariable String id) throws Exception {
        boolean flag = service.delete(id);
        return  ResponseEntity.ok(flag);
    }
}
