package com.showpost.instantiation;

public class SayingInstantiation {
    private String saying;

    public SayingInstantiation(String saying) {
        this.saying = saying;
    }

    public SayingInstantiation() {
    }

    public String getSaying() {
        return saying;
    }

    public void setSaying(String saying) {
        this.saying = saying;
    }

    @Override
    public String toString() {
        return "SayingInstantiation{" +
                "saying='" + saying + '\'' +
                '}';
    }
}
