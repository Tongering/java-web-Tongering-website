package com.register.instantiation;

public class GetId {
    private int id;

    public GetId(int id) {
        this.id = id;
    }

    public GetId() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "GetId{" +
                "id=" + id +
                '}';
    }
}
