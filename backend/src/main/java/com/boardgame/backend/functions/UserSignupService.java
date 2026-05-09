package com.boardgame.backend.functions;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

@Service
public class UserSignupService {

    public ResponseEntity<JsonNode> addUserSignup(ObjectNode signupData) {

        ObjectMapper mapper = new ObjectMapper();

        try {

            File signupFile = new File("src/main/resources/data/JSON_VIEWS/user_signup_view.json");
            File editFile = new File("src/main/resources/data/JSON_VIEWS/user_edit_view.json");
            File loginFile = new File("src/main/resources/data/JSON_VIEWS/user_login_view.json");

            /* ---------- VALIDATION SECTION ---------- */

            String email = signupData.path("email").asText().trim();
            String phone = signupData.path("phonenumber").asText().trim();
            String username = signupData.path("username").asText().trim();
            String password = signupData.path("password").asText().trim();

            if (email.isEmpty() || phone.isEmpty() || username.isEmpty() || password.isEmpty()) {
                return errorResponse(mapper, 400, "Required fields cannot be empty");
            }

            if (!email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
                return errorResponse(mapper, 400, "Invalid email format");
            }

            if (!phone.matches("^[0-9]{10}$")) {
                return errorResponse(mapper, 400, "Phone number must be 10 digits");
            }

            if (!username.matches("^[a-zA-Z0-9_]+$")) {
                return errorResponse(mapper, 400, "Username can contain only letters, numbers, and underscore");
            }

            if (password.length() < 6) {
                return errorResponse(mapper, 400, "Password must be at least 6 characters");
            }

            /* ---------- SIGNUP JSON ---------- */

            JsonNode signupRoot = mapper.readTree(signupFile);
            ArrayNode signupArray = (ArrayNode) signupRoot.path("user_signup_view");

            /* ---------- UNIQUENESS CHECK ---------- */

            for (JsonNode user : signupArray) {

                if (user.path("email").asText().equalsIgnoreCase(email)) {
                    return errorResponse(mapper, 409, "Email already registered");
                }

                if (user.path("username").asText().equalsIgnoreCase(username)) {
                    return errorResponse(mapper, 409, "Username already taken");
                }

                if (user.path("phone_number").asText().equals(phone)) {
                    return errorResponse(mapper, 409, "Phone number already registered");
                }
            }

            /* ---------- CREATE USER ---------- */

            ObjectNode signupUser = mapper.createObjectNode();

            signupUser.put("first_name", signupData.path("firstname").asText());
            signupUser.put("last_name", signupData.path("lastname").asText());
            signupUser.put("email", email);
            signupUser.put("phone_number", phone);
            signupUser.put("dob", signupData.path("dob").asText());
            signupUser.put("username", username);
            signupUser.put("password", password);

            signupArray.add(signupUser);
            mapper.writerWithDefaultPrettyPrinter().writeValue(signupFile, signupRoot);

            /* ---------- EDIT USER JSON ---------- */

            JsonNode editRoot = mapper.readTree(editFile);
            ArrayNode editArray = (ArrayNode) editRoot.path("user_edit_view");

            ObjectNode editUser = mapper.createObjectNode();

            editUser.put("first_name", signupData.path("firstname").asText());
            editUser.put("last_name", signupData.path("lastname").asText());
            editUser.put("address", signupData.path("address").asText());
            editUser.put("phone_number", phone);
            editUser.put("email", email);
            editUser.put("bio", signupData.path("bio").asText());
            editUser.putNull("profile_picture");
            editUser.put("member_type_name", signupData.path("membertype").asText());

            editArray.add(editUser);
            mapper.writerWithDefaultPrettyPrinter().writeValue(editFile, editRoot);

            /* ---------- LOGIN JSON ---------- */

            JsonNode loginRoot = mapper.readTree(loginFile);
            ArrayNode loginArray = (ArrayNode) loginRoot.path("user_login_view");

            ObjectNode loginUser = mapper.createObjectNode();

            loginUser.put("username", username);
            loginUser.put("password", password);

            loginArray.add(loginUser);
            mapper.writerWithDefaultPrettyPrinter().writeValue(loginFile, loginRoot);

            /* ---------- SUCCESS RESPONSE ---------- */

            return successResponse(mapper, signupUser);

        } catch (IOException e) {
            return errorResponse(mapper, 500, "Internal server error");
        }
    }

    /* ---------- HELPER METHODS ---------- */

    private ResponseEntity<JsonNode> errorResponse(ObjectMapper mapper, int status, String message) {
        ObjectNode error = mapper.createObjectNode();
        error.put("success", false);
        error.put("status", status);
        error.put("error", message);
        return ResponseEntity.status(status).body(error);
    }

    private ResponseEntity<JsonNode> successResponse(ObjectMapper mapper, JsonNode data) {
        ObjectNode success = mapper.createObjectNode();
        success.put("success", true);
        success.set("data", data);
        return ResponseEntity.ok(success);
    }
}   