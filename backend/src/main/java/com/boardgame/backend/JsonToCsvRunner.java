package com.boardgame.backend;

import com.boardgame.backend.functions.JsonToCsvService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class JsonToCsvRunner
        implements CommandLineRunner {

    private final JsonToCsvService service;

    public JsonToCsvRunner(
            JsonToCsvService service
    ) {
        this.service = service;
    }

    @Override
    public void run(String... args) {

        service.convertMemberTypes();

        service.convertUsers();

        service.convertUserProfiles();

        service.convertEvents();

        service.convertEventHosts();

        service.convertEventSchedules();

        System.out.println(
                "All CSV files created successfully!"
        );
    }
}