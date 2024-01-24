package com.kinsk.pink.controller;

import com.kinsk.pink.model.User;
import com.kinsk.pink.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.webjars.NotFoundException;


import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    @Operation(summary = "Get All", description = "Retrieve all from the database")
    public ResponseEntity<List<User>> findAllUsers(){
        System.out.println("R READ");
        //instance
        return new ResponseEntity<List<User>>(userService.findAll(), HttpStatus.OK);

    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a user By Id", description = "Retrieve a user from the database by ID")
    public ResponseEntity<List<User>> findById(@PathVariable Long id)
            throws NotFoundException {
        System.out.println("R READ BY ID");
        return new ResponseEntity(userService.findUserById(id),HttpStatus.OK);
    }

    @PostMapping
    @Operation(summary = "Create a user", description = "Add a new user to the database")
    public ResponseEntity<User> postUser(@RequestBody User user){
        User user1 = userService.save(user);
        System.out.println("C CREATE " + user1.getStartUser());
        return new ResponseEntity<>(user1,HttpStatus.OK);
    }

    @PutMapping
    @Operation(summary = "Update a user", description = "Update a user in the database")
    public ResponseEntity<User> updateUser(@RequestBody User user)
        throws NotFoundException{
        User userUpdate = userService.update(user);
        System.out.println("U UPDATE | startUser " + userUpdate.getStartUser());
        System.out.println("U UPDATE | lastUpdate " + userUpdate.getLastUpdate());
        return new ResponseEntity<>(userUpdate,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a user", description = "Remove a user from the database by ID")
    public ResponseEntity<User> deleteById(@PathVariable Long id)
        throws ChangeSetPersister.NotFoundException{
        if(id != null){
            System.out.println("D DELETE");
            userService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else throw new ChangeSetPersister.NotFoundException();
    }
}
