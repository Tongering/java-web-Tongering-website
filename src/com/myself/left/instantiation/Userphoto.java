package com.myself.left.instantiation;

import java.util.Arrays;

public class Userphoto {
    private int id;
    private String user_photo;

    public Userphoto(int id, String user_photo) {
        this.id = id;
        this.user_photo = user_photo;
    }

    public Userphoto() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUser_photo() {
        return user_photo;
    }

    public void setUser_photo(String user_photo) {
        this.user_photo = user_photo;
    }

    @Override
    public String toString() {
        return "Userphoto{" +
                "id=" + id +
                ", user_photo='" + user_photo + '\'' +
                '}';
    }
}
