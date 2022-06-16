package com.register.instantiation;

public class Radompho {
    private String photobase;

    public Radompho(String photobase) {
        this.photobase = photobase;
    }

    public Radompho() {
    }

    public String getPhotobase() {
        return photobase;
    }

    public void setPhotobase(String photobase) {
        this.photobase = photobase;
    }

    @Override
    public String toString() {
        return "radompho{" +
                "photobase='" + photobase + '\'' +
                '}';
    }
}
