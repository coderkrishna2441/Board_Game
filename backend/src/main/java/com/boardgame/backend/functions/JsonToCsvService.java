package com.boardgame.backend.functions;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.opencsv.CSVWriter;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileWriter;

@Service
public class JsonToCsvService {

    private final ObjectMapper mapper =
            new ObjectMapper();

    // MEMBER TYPES
    public void convertMemberTypes() {

        try {

            JsonNode root = mapper.readTree(
                    new File(
                            "src/main/resources/data/JSON_VIEWS/member_types.json"
                    )
            );

            JsonNode array =
                    root.get("type_of_member");

            CSVWriter writer =
                    new CSVWriter(
                            new FileWriter(
                                    "src/main/resources/data/csv/member_types.csv"
                            )
                    );

            writer.writeNext(new String[]{
                    "member_type_id",
                    "member_type_name",
                    "member_type_perks",
                    "years_to_get_member_type"
            });

            for (JsonNode node : array) {

                writer.writeNext(new String[]{
                        node.get("member_type_id").asText(),
                        node.get("member_type_name").asText(),
                        node.get("member_type_perks").asText(),
                        node.get("years_to_get_member_type").asText()
                });
            }

            writer.close();

            System.out.println(
                    "member_types.csv created"
            );

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    // USERS
    public void convertUsers() {

        try {

            JsonNode root = mapper.readTree(
                    new File(
                            "src/main/resources/data/JSON_VIEWS/user_login_view.json"
                    )
            );

            JsonNode array =
                    root.get("user_login_view");

            CSVWriter writer =
                    new CSVWriter(
                            new FileWriter(
                                    "src/main/resources/data/csv/users.csv"
                            )
                    );

            writer.writeNext(new String[]{
                    "username",
                    "password"
            });

            int userId = 1;

            for (JsonNode node : array) {

                writer.writeNext(new String[]{
                        String.valueOf(userId++),
                        node.get("username").asText(),
                        node.get("password").asText()
                });
            }

            writer.close();

            System.out.println(
                    "users.csv created"
            );

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    // USER PROFILES
    public void convertUserProfiles() {

        try {

            JsonNode root = mapper.readTree(
                    new File(
                            "src/main/resources/data/JSON_VIEWS/user_edit_view.json"
                    )
            );

            JsonNode array =
                    root.get("user_edit_view");

            CSVWriter writer =
                    new CSVWriter(
                            new FileWriter(
                                    "src/main/resources/data/csv/user_profiles.csv"
                            )
                    );

            writer.writeNext(new String[]{
                    "profile_id",
                    "first_name",
                    "last_name",
                    "address",
                    "phone_number",
                    "email",
                    "bio",
                    "profile_picture",
                    "member_type_name"
            });

            int profileId = 1;

            for (JsonNode node : array) {

                writer.writeNext(new String[]{
                        String.valueOf(profileId++),
                        node.get("first_name").asText(),
                        node.get("last_name").asText(),
                        node.get("address").asText(),
                        node.get("phone_number").asText(),
                        node.get("email").asText(),
                        node.get("bio").asText(),
                        node.get("profile_picture").asText(),
                        node.get("member_type_name").asText()
                });
            }

            writer.close();

            System.out.println(
                    "user_profiles.csv created"
            );

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    // EVENTS
    public void convertEvents() {

        try {

            JsonNode root = mapper.readTree(
                    new File(
                            "src/main/resources/data/JSON_VIEWS/event_full_details.json"
                    )
            );

            JsonNode array =
                    root.get("event_full_details");

            CSVWriter writer =
                    new CSVWriter(
                            new FileWriter(
                                    "src/main/resources/data/csv/events.csv"
                            )
                    );

            writer.writeNext(new String[]{
                    "event_id",
                    "event_name",
                    "event_date",
                    "event_time",
                    "event_venue",
                    "event_description",
                    "poster"
            });

            int eventId = 1;

            for (JsonNode node : array) {

                writer.writeNext(new String[]{
                        String.valueOf(eventId++),
                        node.get("event_name").asText(),
                        node.get("event_date").asText(),
                        node.get("event_time").asText(),
                        node.get("event_venue").asText(),
                        node.get("event_description").asText(),
                        node.get("poster").asText()
                });
            }

            writer.close();

            System.out.println(
                    "events.csv created"
            );

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    // EVENT HOSTS
    public void convertEventHosts() {

        try {

            JsonNode root = mapper.readTree(
                    new File(
                            "src/main/resources/data/JSON_VIEWS/event_full_details.json"
                    )
            );

            JsonNode array =
                    root.get("event_full_details");

            CSVWriter writer =
                    new CSVWriter(
                            new FileWriter(
                                    "src/main/resources/data/csv/event_hosts.csv"
                            )
                    );

            writer.writeNext(new String[]{
                    "event_host_id",
                    "event_id",
                    "host_name"
            });

            int hostId = 1;
            int eventId = 1;

            for (JsonNode node : array) {

                JsonNode hosts =
                        node.get("host_names");

                for (JsonNode host : hosts) {

                    writer.writeNext(new String[]{
                            String.valueOf(hostId++),
                            String.valueOf(eventId),
                            host.asText()
                    });
                }

                eventId++;
            }

            writer.close();

            System.out.println(
                    "event_hosts.csv created"
            );

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    // EVENT SCHEDULES
    public void convertEventSchedules() {

        try {

            JsonNode root = mapper.readTree(
                    new File(
                            "src/main/resources/data/JSON_VIEWS/event_full_details.json"
                    )
            );  

            JsonNode array =
                    root.get("event_full_details");

            CSVWriter writer =
                    new CSVWriter(
                            new FileWriter(
                                    "src/main/resources/data/csv/event_schedules.csv"
                            )
                    );

            writer.writeNext(new String[]{
                    "schedule_id",
                    "event_id",
                    "activity",
                    "activity_time"
            });

            int scheduleId = 1;
            int eventId = 1;

            for (JsonNode node : array) {

                JsonNode schedules =
                        node.get("event_schedule");

                for (JsonNode schedule : schedules) {

                    writer.writeNext(new String[]{
                            String.valueOf(scheduleId++),
                            String.valueOf(eventId),
                            schedule.get("activity").asText(),
                            schedule.get("time").asText()
                    });
                }

                eventId++;
            }

            writer.close();

            System.out.println(
                    "event_schedules.csv created"
            );

        } catch (Exception e) {

            e.printStackTrace();
        }
    }
}