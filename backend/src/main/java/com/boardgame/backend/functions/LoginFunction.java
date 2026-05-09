package com.boardgame.backend.functions;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import java.io.File;

public class LoginFunction {

    private final String FILE_PATH = "src/main/resources/data/JSON_VIEWS/user_login_view.json";

    public String loginUser(String username, String password) {

    try {

        /* ---------- VALIDATION ---------- */

        if (username == null || username.trim().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Username cannot be empty");
        }

        if (password == null || password.trim().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Password cannot be empty");
        }

        username = username.trim();
        password = password.trim();

        if (!username.matches("^[a-zA-Z0-9_]+$")) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Invalid username format");
        }

        if (password.length() < 6) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Password must be at least 6 characters");
        }

        /* ---------- LOGIN CHECK ---------- */

        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode = mapper.readTree(new File(FILE_PATH));
        JsonNode users = rootNode.get("user_login_view");

        for (JsonNode user : users) {

            String jsonUsername = user.get("username").asText();
            String jsonPassword = user.get("password").asText();

            if (jsonUsername.equals(username) && jsonPassword.equals(password)) {
                return "Login Success";
            }
        }

        return "Invalid Username or Password";

    } catch (Exception e) {
        throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Server Error");
    }
}
}