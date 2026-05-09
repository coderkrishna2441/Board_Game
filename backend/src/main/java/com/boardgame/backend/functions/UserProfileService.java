package com.boardgame.backend.functions;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

@Service
public class UserProfileService {

    private final String SIGNUP_FILE = "src/main/resources/data/JSON_VIEWS/user_signup_view.json";
    private final String EDIT_FILE = "src/main/resources/data/JSON_VIEWS/user_edit_view.json";

    /* =========================================================
                        GET USER PROFILE
       ========================================================= */

    public JsonNode getUserProfile(String username) {

        ObjectMapper mapper = new ObjectMapper();

        try {

            JsonNode signupRoot = mapper.readTree(new File(SIGNUP_FILE));
            JsonNode signupArray = signupRoot.get("user_signup_view");

            String phoneNumber = null;  

            for (JsonNode user : signupArray) {
                if (user.get("username").asText().equals(username)) {
                    phoneNumber = user.get("phone_number").asText(); 
                    break;
                }
            }

            if (phoneNumber == null) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
            }

            JsonNode editRoot = mapper.readTree(new File(EDIT_FILE));
            JsonNode editArray = editRoot.get("user_edit_view");

            for (JsonNode user : editArray) {
                if (user.get("phone_number").asText().equals(phoneNumber)) {
                    return user;
                }
            }

            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Profile not found");

        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Server Error");
        }
    }

    /* =========================================================
                        UPDATE USER PROFILE
       ========================================================= */

    public JsonNode updateUserProfile(String username, ObjectNode updatedData) {

        ObjectMapper mapper = new ObjectMapper();

        try {

            File signupFile = new File(SIGNUP_FILE);
            File editFile = new File(EDIT_FILE);

            JsonNode signupRoot = mapper.readTree(signupFile);
            JsonNode signupArray = signupRoot.get("user_signup_view");

            String oldPhoneNumber = null;
            ObjectNode signupNode = null;

            /* ---------- FIND USER ---------- */

            for (JsonNode user : signupArray) {
                if (user.get("username").asText().equals(username)) {
                    signupNode = (ObjectNode) user;
                    oldPhoneNumber = signupNode.get("phone_number").asText();
                    break;
                }
            }

            if (signupNode == null) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
            }

            /* =========================================================
                            VALIDATION SECTION
               ========================================================= */

            String email = updatedData.path("email").asText().trim();
            String phone = updatedData.path("phonenumber").asText().trim();
            String firstname = updatedData.path("firstname").asText().trim();
            String lastname = updatedData.path("lastname").asText().trim();

            // Email validation
            if (!email.isEmpty() && !email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid email format");
            }

            // Phone validation
            if (!phone.isEmpty() && !phone.matches("^[0-9]{10}$")) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Phone number must be 10 digits");
            }

            // Name validation
            if (!firstname.isEmpty() && !firstname.matches("^[A-Za-z]+$")) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid first name");
            }

            if (!lastname.isEmpty() && !lastname.matches("^[A-Za-z]+$")) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid last name");
            }

            /* ---------- UNIQUENESS CHECK ---------- */

            for (JsonNode user : signupArray) {

                if (!user.get("username").asText().equals(username)) {

                    if (!email.isEmpty() &&
                        user.path("email").asText().equalsIgnoreCase(email)) {
                        throw new ResponseStatusException(HttpStatus.CONFLICT, "Email already in use");
                    }

                    if (!phone.isEmpty() &&
                        user.path("phone_number").asText().equals(phone)) {
                        throw new ResponseStatusException(HttpStatus.CONFLICT, "Phone number already in use");
                    }
                }
            }

            /* =========================================================
                        UPDATE SIGNUP JSON
               ========================================================= */

            Iterator<String> fields = updatedData.fieldNames();

            while (fields.hasNext()) {

                String field = fields.next();
                JsonNode value = updatedData.get(field);

                if (value.asText().trim().isEmpty()) continue;

                String jsonField = mapField(field);

                if (signupNode.has(jsonField)) {
                    signupNode.set(jsonField, value);
                }
            }

            mapper.writerWithDefaultPrettyPrinter().writeValue(signupFile, signupRoot);

            /* =========================================================
                        UPDATE EDIT JSON
               ========================================================= */

            JsonNode editRoot = mapper.readTree(editFile);
            JsonNode editArray = editRoot.get("user_edit_view");

            for (JsonNode user : editArray) {

                if (user.get("phone_number").asText().equals(oldPhoneNumber)) {

                    ObjectNode editNode = (ObjectNode) user;

                    Iterator<String> fields2 = updatedData.fieldNames();

                    while (fields2.hasNext()) {

                        String field = fields2.next();
                        JsonNode value = updatedData.get(field);

                        if (value.asText().trim().isEmpty()) continue;

                        String jsonField = mapField(field);

                        if (editNode.has(jsonField)) {
                            editNode.set(jsonField, value);
                        }
                    }

                    mapper.writerWithDefaultPrettyPrinter().writeValue(editFile, editRoot);

                    return editNode;
                }
            }

            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Profile not found");

        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Server Error");
        }
    }

    /* =========================================================
                        FIELD MAPPING HELPER
       ========================================================= */

    private String mapField(String field) {

        switch (field) {
            case "firstname": return "first_name";
            case "lastname": return "last_name";
            case "phonenumber": return "phone_number";
            case "membertype": return "member_type_name";
            default: return field;
        }
    }
}