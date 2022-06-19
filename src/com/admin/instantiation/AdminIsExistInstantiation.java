package com.admin.instantiation;

public class AdminIsExistInstantiation {
    private int id;
    private String user;
    private String user_photo;

    public AdminIsExistInstantiation() {
    }

    public AdminIsExistInstantiation(int id, String user, String user_photo) {
        this.id = id;
        this.user = user;
        this.user_photo = user_photo;
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

    @Override
    public String toString() {
        return "AdminIsExistInstantiation{" +
                "id=" + id +
                ", user='" + user + '\'' +
                ", user_photo='" + user_photo + '\'' +
                '}';
    }
}
