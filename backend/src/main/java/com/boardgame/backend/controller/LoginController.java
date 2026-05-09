package com.boardgame.backend.controller;

import org.springframework.web.bind.annotation.*;
import java.util.Map;
import com.boardgame.backend.functions.LoginFunction;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class LoginController {

    LoginFunction login = new LoginFunction();

    @PostMapping("/login")
    public Map<String, String> login(@RequestBody Map<String, String> data) {

        String username = data.get("username");
        String password = data.get("password");

        String result = login.loginUser(username, password);

        return Map.of("message", result);
    }
}