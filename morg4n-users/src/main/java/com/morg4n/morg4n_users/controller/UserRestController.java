package com.morg4n.morg4n_users.controller;

import com.morg4n.morg4n_users.model.User;
import com.morg4n.morg4n_users.service.IUserService;
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
public class UserRestController {
    private final IUserService service;

    @GetMapping("")
    public ResponseEntity<List<User>> index(){
        List<User> users = service.findAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<User> findUserById(@PathVariable String id) throws Exception {
        User user = service.findById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<User> saveUser(@RequestBody User user){
        User newUser = service.save(user);
        return new ResponseEntity<>(newUser, HttpStatus.OK);
    }

    @PutMapping("{id}")
    public  ResponseEntity<User> updateUser(@PathVariable String id, @RequestBody User user) throws  Exception {
        User updatedUser = service.update(id, user);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Boolean> deleteUser(@PathVariable String id) throws Exception {
        boolean flag = service.delete(id);
        return  ResponseEntity.ok(flag);
    }
}
