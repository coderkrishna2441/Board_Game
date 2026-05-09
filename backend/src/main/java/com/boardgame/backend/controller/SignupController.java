package com.boardgame.backend.controller;

import com.boardgame.backend.functions.UserSignupService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/signup")
@CrossOrigin(origins = "http://localhost:4200")
public class SignupController {

    private final UserSignupService userSignupService;

    public SignupController(UserSignupService userSignupService) {
        this.userSignupService = userSignupService;
    }

    @PostMapping
    public ResponseEntity<JsonNode> signup(@RequestBody ObjectNode data) {
        return userSignupService.addUserSignup(data);
    }
}