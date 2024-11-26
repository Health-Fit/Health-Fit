package com.heemin.ws.model.dto.video;

public class ExampleVideo {
    private long id;
    private String title;
    private String url;
    private long category1;
    private long category2;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public long getCategory1() {
        return category1;
    }

    public void setCategory1(long category1) {
        this.category1 = category1;
    }

    public long getCategory2() {
        return category2;
    }

    public void setCategory2(long category2) {
        this.category2 = category2;
    }
}
