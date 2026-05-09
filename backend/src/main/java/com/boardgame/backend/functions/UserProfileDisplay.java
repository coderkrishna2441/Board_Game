package com.boardgame.backend.functions;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;

public class UserProfileDisplay {

    private final String FILE_PATH1 = "src/main/resources/data/JSON_VIEWS/user_signup_view.json";
    private final String FILE_PATH2 = "src/main/resources/data/JSON_VIEWS/user_edit_view.json";

    public JsonNode getUserProfile(String username) {

        ObjectMapper mapper = new ObjectMapper();

        try {
            JsonNode signupRoot = mapper.readTree(new File(FILE_PATH1));
            JsonNode signupArray = signupRoot.get("user_signup_view");

            String phoneNumber = null;
            for (JsonNode user : signupArray) {
                if (user.get("username").asText().equals(username)) {
                    phoneNumber = user.get("phone_number").asText();
                    break;
                }
            }
            if (phoneNumber == null) {
                return null;
            }

            JsonNode editRoot = mapper.readTree(new File(FILE_PATH2));
            JsonNode editArray = editRoot.get("user_edit_view");

            for (JsonNode user : editArray) {
                if (user.get("phone_number").asText().equals(phoneNumber)) {
                    return user;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}