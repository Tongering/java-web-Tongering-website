package com.myself.left.instantiation;

import java.sql.Date;

public class Usermyself {
    private int id;
    private String university;
    private String subject;
    private Date birth;
    private String better;
    private String likes;

    public Usermyself() {
    }

    public Usermyself(int id, String university, String subject, Date birth, String better, String likes) {
        this.id = id;
        this.university = university;
        this.subject = subject;
        this.birth = birth;
        this.better = better;
        this.likes = likes;
    }

    @Override
    public String toString() {
        return "Usermyself{" +
                "id=" + id +
                ", university='" + university + '\'' +
                ", subject='" + subject + '\'' +
                ", birth=" + birth +
                ", better='" + better + '\'' +
                ", likes='" + likes + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
}
