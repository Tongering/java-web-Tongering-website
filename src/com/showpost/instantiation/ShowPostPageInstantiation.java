package com.showpost.instantiation;

import java.sql.Timestamp;

public class ShowPostPageInstantiation {
    private String user_photo;
    private String user;
    private String title;
    private int invitationid;
    private Timestamp posttime;
    private int id;
    private int likes;
    private int favorite;
    private int browse;

    public ShowPostPageInstantiation(String user_photo, String user, String title, int invitationid, Timestamp posttime, int id, int likes, int favorite, int browse) {
        this.user_photo = user_photo;
        this.user = user;
        this.title = title;
        this.invitationid = invitationid;
        this.posttime = posttime;
        this.id = id;
        this.likes = likes;
        this.favorite = favorite;
        this.browse = browse;
    }

    public ShowPostPageInstantiation() {
    }

    public String getUser_photo() {
        return user_photo;
    }

    public void setUser_photo(String user_photo) {
        this.user_photo = user_photo;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getInvitationid() {
        return invitationid;
    }

    public void setInvitationid(int invitationid) {
        this.invitationid = invitationid;
    }

    public Timestamp getPosttime() {
        return posttime;
    }

    public void setPosttime(Timestamp posttime) {
        this.posttime = posttime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getFavorite() {
        return favorite;
    }

    public void setFavorite(int favorite) {
        this.favorite = favorite;
    }

    public int getBrowse() {
        return browse;
    }

    public void setBrowse(int browse) {
        this.browse = browse;
    }

    @Override
    public String toString() {
        return "showpostpage{" +
                "user_photo='" + user_photo + '\'' +
                ", user='" + user + '\'' +
                ", title='" + title + '\'' +
                ", invitationid=" + invitationid +
                ", posttime=" + posttime +
                ", id=" + id +
                ", likes=" + likes +
                ", favorite=" + favorite +
                ", browse=" + browse +
                '}';
    }
}
