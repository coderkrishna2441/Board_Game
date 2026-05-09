package com.boardgame.backend.controller;

import org.springframework.web.bind.annotation.*;

import com.boardgame.backend.functions.UserProfileService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/edituserinfo")
public class EditUserController {

    UserProfileService profileService = new UserProfileService();

    @GetMapping("/{username}")
    public JsonNode getProfile(@PathVariable String username) {

        return profileService.getUserProfile(username);

    }

    @PutMapping("/{username}")
    public JsonNode updateProfile(
            @PathVariable String username,
            @RequestBody ObjectNode updatedData) {

        return profileService.updateUserProfile(username, updatedData);

    }
}