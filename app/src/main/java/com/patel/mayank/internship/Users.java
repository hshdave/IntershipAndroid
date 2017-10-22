package com.patel.mayank.internship;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Mayank on 10/18/2017.
 */

public class Users {

    private String email,name,bod,utype,bio,skill;

    public Users(String name, String bod, String bio, String skill) {
        this.name = name;
        this.bod = bod;
        this.bio = bio;
        this.skill = skill;
    }

    public Users(String email, String name, String bod, String utype, String bio, String skill) {
        this.email = email;
        this.name = name;
        this.bod = bod;
        this.utype = utype;
        this.bio = bio;
        this.skill = skill;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBod() {
        return bod;
    }

    public void setBod(String bod) {
        this.bod = bod;
    }

    public String getUtype() {
        return utype;
    }

    public void setUtype(String utype) {
        this.utype = utype;
    }

    public Map<String, Object> toMap(){
        HashMap<String, Object> result = new HashMap<>();

        result.put("name",name);
        result.put("bod",bod);
        result.put("bio",bio);
        result.put("skill",skill);

        return result;

    }
}
