package com.shares.instantiation;

public class IsExistInstantiation {
    private int shareid;
    private int id;
    private int invitationid;
    private int likes;
    private int favorite;
    private int browse;

    public IsExistInstantiation(int shareid, int id, int invitationid, int likes, int favorite, int browse) {
        this.shareid = shareid;
        this.id = id;
        this.invitationid = invitationid;
        this.likes = likes;
        this.favorite = favorite;
        this.browse = browse;
    }

    public IsExistInstantiation() {
    }

    public int getShareid() {
        return shareid;
    }

    public void setShareid(int shareid) {
        this.shareid = shareid;
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
        return "IsExistInstantiation{" +
                "shareid=" + shareid +
                ", id=" + id +
                ", invitationid=" + invitationid +
                ", likes=" + likes +
                ", favorite=" + favorite +
                ", browse=" + browse +
                '}';
    }
}
