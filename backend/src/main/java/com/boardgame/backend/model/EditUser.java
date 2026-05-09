package com.boardgame.backend.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class EditUser {

    @JsonProperty("firstname")
    private String firstname;

    @JsonProperty("lastname")
    private String lastname;

    @JsonProperty("address")
    private String address;

    @JsonProperty("phonenumber")
    private String phonenumber;

    @JsonProperty("membertype")
    private String membertype;

    private String email;

    @JsonProperty("bio")
    private String bio;


    public String getFirstName(){ return firstname; }
    public void setFirstName(String firstname){ this.firstname = firstname; }

    public String getLastName(){ return lastname; }
    public void setLastName(String lastname){ this.lastname = lastname; }

    public String getAddress(){ return address; }
    public void setAddress(String address){ this.address = address; }

    public String getPhoneNumber(){ return phonenumber; }
    public void setPhoneNumber(String phonenumber){ this.phonenumber = phonenumber; }

    public String getMemberType(){ return membertype; }
    public void setMemberType(String membertype){ this.membertype = membertype; }

    public String getEmail(){ return email; }
    public void setEmail(String email){ this.email = email; }

    public String getBio(){ return bio; }
    public void setBio(String bio){ this.bio = bio; }

}