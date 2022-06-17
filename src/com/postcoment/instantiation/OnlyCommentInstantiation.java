package com.postcoment.instantiation;

import java.sql.Timestamp;

public class OnlyCommentInstantiation {
    private int id;
    private int invitationid;
    private int commentid;
    private String content;
    private Timestamp commenttime;

    public OnlyCommentInstantiation(int id, int invitationid, int commentid, String content, Timestamp commenttime) {
        this.id = id;
        this.invitationid = invitationid;
        this.commentid = commentid;
        this.content = content;
        this.commenttime = commenttime;
    }

    public OnlyCommentInstantiation() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getInvitationid() {
        return invitationid;
    }

    public void setInvitationid(int invitationid) {
        this.invitationid = invitationid;
    }

    public int getCommentid() {
        return commentid;
    }

    public void setCommentid(int commentid) {
        this.commentid = commentid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getCommenttime() {
        return commenttime;
    }

    public void setCommenttime(Timestamp commenttime) {
        this.commenttime = commenttime;
    }

    @Override
    public String toString() {
        return "OnlyCommentInstantiation{" +
                "id=" + id +
                ", invitationid=" + invitationid +
                ", commentid=" + commentid +
                ", content='" + content + '\'' +
                ", commenttime=" + commenttime +
                '}';
    }
}
