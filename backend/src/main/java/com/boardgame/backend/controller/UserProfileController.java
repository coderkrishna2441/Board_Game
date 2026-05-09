package com.boardgame.backend.controller;

import org.springframework.web.bind.annotation.*;
import com.boardgame.backend.functions.UserProfileDisplay;
import com.fasterxml.jackson.databind.JsonNode;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class UserProfileController {

    UserProfileDisplay profile = new UserProfileDisplay();

    @GetMapping("/userprofile/{username}")
    public JsonNode getProfile(@PathVariable String username) {

        return profile.getUserProfile(username);

    }
}