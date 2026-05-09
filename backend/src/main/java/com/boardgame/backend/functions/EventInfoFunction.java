package com.boardgame.backend.functions;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class EventInfoFunction {

    private final ObjectMapper mapper = new ObjectMapper();

    private final String EVENTS_JSON =
            "src/main/resources/data/JSON_VIEWS/event_full_details.json";


    public JsonNode getEventInfo(String eventname) {

        try {


            JsonNode eventsRoot = mapper.readTree(new File(EVENTS_JSON));
            JsonNode eventsArray = eventsRoot.get("event_full_details");



            for (JsonNode event : eventsArray) {

                if (event.get("event_name").asText().equalsIgnoreCase(eventname)) {

                    return event;

                }

            }

        }

        catch (Exception e) {

            e.printStackTrace();

        }

        return null;
    }

}