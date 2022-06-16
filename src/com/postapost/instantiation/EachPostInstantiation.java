package com.postapost.instantiation;


import java.sql.Timestamp;

public class EachPostInstantiation {
    private String user_photo;
    private String user;
    private String title;
    private int invitationid;
    private Timestamp posttime;
    private int id;
    private String typeinvitation;

    public EachPostInstantiation(String user_photo, String user, String title, int invitationid, Timestamp posttime, int id, String typeinvitation) {
        this.user_photo = user_photo;
        this.user = user;
        this.title = title;
        this.invitationid = invitationid;
        this.posttime = posttime;
        this.id = id;
        this.typeinvitation = typeinvitation;
    }

    public EachPostInstantiation() {
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

    public String getTypeinvitation() {
        return typeinvitation;
    }

    public void setTypeinvitation(String typeinvitation) {
        this.typeinvitation = typeinvitation;
    }

    @Override
    public String toString() {
        return "EachPostInstantiation{" +
                "user_photo='" + user_photo + '\'' +
                ", user='" + user + '\'' +
                ", title='" + title + '\'' +
                ", invitationid=" + invitationid +
                ", posttime=" + posttime +
                ", id=" + id +
                ", typeinvitation='" + typeinvitation + '\'' +
                '}';
    }
}

