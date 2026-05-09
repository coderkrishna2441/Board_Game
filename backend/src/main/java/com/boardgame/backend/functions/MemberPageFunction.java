package com.boardgame.backend.functions;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.stereotype.Service;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

@Service
public class MemberPageFunction {

    private final ObjectMapper mapper = new ObjectMapper();

    private final String EVENTS_JSON =
            "src/main/resources/data/JSON_VIEWS/event_full_details.json";

    private final String MEMBER_TYPES_JSON =
            "src/main/resources/data/JSON_VIEWS/type_of_member.json";

    private final String USERS_JSON =
            "src/main/resources/data/JSON_VIEWS/user_signup_view.json";


    public Map<String, Object> getMemberPageData(String username) {

        Map<String, Object> response = new HashMap<>();

        try {

            /* READ EVENTS JSON */

            JsonNode eventsRoot = mapper.readTree(new File(EVENTS_JSON));
            JsonNode eventsArray = eventsRoot.get("event_full_details");


            /* READ MEMBER TYPES JSON */

            JsonNode memberRoot = mapper.readTree(new File(MEMBER_TYPES_JSON));
            JsonNode memberTypesArray = memberRoot.get("type_of_member");


            /* READ USERS JSON */

            JsonNode userRoot = mapper.readTree(new File(USERS_JSON));
            JsonNode usersArray = userRoot.get("user_signup_view");


            /* FIND CURRENT USER */

            JsonNode currentUser = null;

            for (JsonNode user : usersArray) {

                if (user.get("username").asText().equals(username)) {

                    currentUser = user;
                    break;

                }

            }


            /* CREATE RESPONSE JSON */

            response.put("user", currentUser);
            response.put("events", eventsArray);
            response.put("member_types", memberTypesArray);

        }

        catch (Exception e) {

            e.printStackTrace();

        }

        return response;
    }

}