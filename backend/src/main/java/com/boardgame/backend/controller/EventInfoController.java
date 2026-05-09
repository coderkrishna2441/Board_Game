package com.boardgame.backend.controller;

import org.springframework.web.bind.annotation.*;
import com.fasterxml.jackson.databind.JsonNode;
import com.boardgame.backend.functions.EventInfoFunction;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/eventinfo")
public class EventInfoController {

    private EventInfoFunction eventInfoFunction = new EventInfoFunction();

    @GetMapping("/{eventname}")
    public JsonNode getEventInfo(@PathVariable String eventname) {

        return eventInfoFunction.getEventInfo(eventname);

    }
}