package com.boardgame.backend.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class User {

    @JsonProperty("firstname")
    private String firstname;

    @JsonProperty("lastname")
    private String lastname;

    private String email;

    @JsonProperty("phonenumber")
    private String phonenumber;

    @JsonProperty("dob")
    private String dob;

    @JsonProperty("username")
    private String username;

    private String password;

    public String getFirstName() { return firstname; }
    public void setFirstName(String firstname) { this.firstname = firstname; }

    public String getLastName() { return lastname; }
    public void setLastName(String lastname) { this.lastname = lastname; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPhoneNumber() { return phonenumber; }
    public void setPhoneNumber(String phonenumber) { this.phonenumber = phonenumber; }

    public String getDateOfBirth() { return dob; }
    public void setDateOfBirth(String dob) { this.dob = dob; }

    public String getUserName() { return username; }
    public void setUserName(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}