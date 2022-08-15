package com.example.OnlineCourses.controllers;

import com.example.OnlineCourses.domains.UDetails;
import com.example.OnlineCourses.domains.User;
import com.example.OnlineCourses.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    // корректно істеп тұр
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers(){
        System.out.println("Get mapping");
        return ResponseEntity.ok(userService.getAllUsers());
    }

    // корректно істеп тұр
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id){
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @PutMapping
    public ResponseEntity updateUser(@RequestBody UDetails uDetails){
        userService.update(uDetails);
        return ResponseEntity.ok("user by id : " + uDetails.getId() + " was updated");
    }

    // корректно істеп тұр
    @DeleteMapping("/{id}")
    public ResponseEntity deleteUserById(@PathVariable Long id){
        userService.deleteUserById(id);
        return ResponseEntity.ok("user by id : " + id + " was deleted");
    }

}
