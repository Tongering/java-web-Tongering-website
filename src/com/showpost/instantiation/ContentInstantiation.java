package com.showpost.instantiation;

public class ContentInstantiation {
    private String content;

    public ContentInstantiation(String content) {
        this.content = content;
    }

    public ContentInstantiation() {
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "contentinstant{" +
                "content='" + content + '\'' +
                '}';
    }
}
