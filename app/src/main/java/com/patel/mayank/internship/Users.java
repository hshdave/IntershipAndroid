package com.patel.mayank.internship;

/**
 * Created by Mayank on 10/18/2017.
 */

public class Users {

    private String email,name,bod,utype;

    public Users(String email, String name, String bod, String utype) {
        this.email = email;
        this.name = name;
        this.bod = bod;
        this.utype = utype;
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
}
