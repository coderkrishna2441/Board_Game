package com.boardgame.backend.functions;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.io.ClassPathResource;

import java.io.InputStream;
import java.util.*;

public class EventService {

private final String EVENT_FILE="data/events.json";
private final String MEMBER_FILE="data/membertypes.json";


/* =========================
   GET ALL EVENTS
========================= */

public List<Map<String,Object>> getAllEvents(){

List<Map<String,Object>> events=new ArrayList<>();

try{

ClassPathResource resource=new ClassPathResource(EVENT_FILE);
InputStream inputStream=resource.getInputStream();

ObjectMapper mapper=new ObjectMapper();
JsonNode root=mapper.readTree(inputStream);

for(JsonNode node:root){

Map<String,Object> event=new HashMap<>();

event.put("event_name",node.get("event_name").asText());
event.put("host_names",node.get("host_names").asText());
event.put("event_date",node.get("event_date").asText());
event.put("event_time",node.get("event_time").asText());
event.put("event_venue",node.get("event_venue").asText());
event.put("event_description",node.get("event_description").asText());

if(node.get("poster")!=null)
event.put("poster",node.get("poster").asText());
else
event.put("poster",null);

List<String> schedule=new ArrayList<>();

for(JsonNode s:node.get("event_schedule")){
schedule.add(s.asText());
}

event.put("event_schedule",schedule);

events.add(event);

}

}catch(Exception e){
e.printStackTrace();
}

return events;

}


/* =========================
   GET SINGLE EVENT
========================= */

public Map<String,Object> getEventByName(String name){

for(Map<String,Object> event:getAllEvents()){

if(event.get("event_name").toString().equals(name)){
return event;
}

}

return new HashMap<>();

}


/* =========================
   MEMBER TYPES TABLE
========================= */

public List<Map<String,Object>> getMemberTypes(){

List<Map<String,Object>> types=new ArrayList<>();

try{

ClassPathResource resource=new ClassPathResource(MEMBER_FILE);
InputStream inputStream=resource.getInputStream();

ObjectMapper mapper=new ObjectMapper();
JsonNode root=mapper.readTree(inputStream);

for(JsonNode node:root){

Map<String,Object> type=new HashMap<>();

type.put("member_type",node.get("member_type").asText());
type.put("perks",node.get("perks").asText());
type.put("years",node.get("years").asInt());

types.add(type);

}

}catch(Exception e){
e.printStackTrace();
}

return types;

}

}