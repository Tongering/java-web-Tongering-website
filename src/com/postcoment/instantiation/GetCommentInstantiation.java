package com.postcoment.instantiation;

import java.sql.Timestamp;

public class GetCommentInstantiation {
    private int id;
    private String photo;
    private String comment;
    private String user;

    public GetCommentInstantiation(int id, String photo, String comment, String user) {
        this.id = id;
        this.photo = photo;
        this.comment = comment;
        this.user = user;
    }

    public GetCommentInstantiation() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "GetCommentInstantiation{" +
                "id=" + id +
                ", photo='" + photo + '\'' +
                ", comment='" + comment + '\'' +
                ", user='" + user + '\'' +
                '}';
    }
}
