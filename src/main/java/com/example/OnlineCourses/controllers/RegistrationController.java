package com.example.OnlineCourses.controllers;

import com.example.OnlineCourses.dtos.RegistrationRequest;
import com.example.OnlineCourses.services.RegistrationService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/v1/registration")
@AllArgsConstructor
public class RegistrationController {

    private final RegistrationService registrationService;

    // корректно істеп тұр
    @PostMapping
    public ResponseEntity register(@RequestBody RegistrationRequest request){
        return ResponseEntity.ok(registrationService.register(request));
    }

    // корректно істеп тұр
    @GetMapping(path = "/confirm")
    public ResponseEntity confirm(@RequestParam("token") String token) {
        return ResponseEntity.ok(registrationService.confirmToken(token));
    }
}
