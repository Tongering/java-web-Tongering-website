package com.admin.instantiation;

public class AdminUserInstantiation {
    private int id;

    public AdminUserInstantiation(int id) {
        this.id = id;
    }

    public AdminUserInstantiation() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "AdminUserInstantiation{" +
                "id=" + id +
                '}';
    }
}
