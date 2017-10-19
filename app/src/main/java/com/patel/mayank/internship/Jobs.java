package com.patel.mayank.internship;

/**
 * Created by Mayank on 10/17/2017.
 */

public class Jobs {
    private String title,location,career_level,desc,employer_id,responsbility,skill;

    public Jobs(String title, String location, String career_level, String desc, String employer_id, String responsbility, String skill) {
        this.title = title;
        this.location = location;
        this.career_level = career_level;
        this.desc = desc;
        this.employer_id = employer_id;
        this.responsbility = responsbility;
        this.skill = skill;
    }

    public Jobs(String title, String location) {
        this.title = title;
        this.location = location;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCareer_level() {
        return career_level;
    }

    public void setCareer_level(String career_level) {
        this.career_level = career_level;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getEmployer_id() {
        return employer_id;
    }

    public void setEmployer_id(String employer_id) {
        this.employer_id = employer_id;
    }

    public String getResponsbility() {
        return responsbility;
    }

    public void setResponsbility(String responsbility) {
        this.responsbility = responsbility;
    }

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }
}
