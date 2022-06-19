package com.admin.instantiation;

import java.sql.Date;

public class AdminDisposeUserInstantiation {
    private int id;
    private String user;
    private String user_photo;
    private String university;
    private String subject;
    private Date birth;
    private String better;
    private String likes;

    public AdminDisposeUserInstantiation(int id, String user, String user_photo, String university, String subject, Date birth, String better, String likes) {
        this.id = id;
        this.user = user;
        this.user_photo = user_photo;
        this.university = university;
        this.subject = subject;
        this.birth = birth;
        this.better = better;
        this.likes = likes;
    }

    public AdminDisposeUserInstantiation() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getUser_photo() {
        return user_photo;
    }

    public void setUser_photo(String user_photo) {
        this.user_photo = user_photo;
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public String getBetter() {
        return better;
    }

    public void setBetter(String better) {
        this.better = better;
    }

    public String getLikes() {
        return likes;
    }

    public void setLikes(String likes) {
        this.likes = likes;
    }

    @Override
    public String toString() {
        return "AdminDelUserInstantiation{" +
                "id=" + id +
                ", user='" + user + '\'' +
                ", user_photo='" + user_photo + '\'' +
                ", university='" + university + '\'' +
                ", subject='" + subject + '\'' +
                ", birth=" + birth +
                ", better='" + better + '\'' +
                ", likes='" + likes + '\'' +
                '}';
    }
}
