package com.boardgame.backend.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RegisterEvent {

    @JsonProperty("firstname")
    private String firstname;

    @JsonProperty("lastname")
    private String lastname;

    private String email;

    @JsonProperty("phonenumber")
    private String phonenumber;

    @JsonProperty("membertype")
    private String membertype;

    @JsonProperty("guests")
    private String guests;


    public String getFirstName(){ return firstname; }
    public void setFirstName(String firstname){ this.firstname = firstname; }

    public String getLastName(){ return lastname; }
    public void setLastName(String lastname){ this.lastname = lastname; }

    public String getEmail(){ return email; }
    public void setEmail(String email){ this.email = email; }

    public String getPhoneNumber(){ return phonenumber; }
    public void setPhoneNumber(String phonenumber){ this.phonenumber = phonenumber; }

    public String getMemberType(){ return membertype; }
    public void setMemberType(String membertype){ this.membertype = membertype; }

    public String getGuests(){ return guests; }
    public void setGuests(String guests){ this.guests = guests; }

}