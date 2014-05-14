package com.hromadske.tv.ck.entities;

/**
 * Created by Cheb on 15.05.2014.
 */
public class Video {
    String id;
    String title;
    String sqThumb, hqThumb;
    String url;

    public Video(String id, String title, String sqThumb, String hqThumb, String url) {
        this.id = id;
        this.title = title;
        this.sqThumb = sqThumb;
        this.hqThumb = hqThumb;
        this.url = url;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSqThumb() {
        return sqThumb;
    }

    public void setSqThumb(String sqThumb) {
        this.sqThumb = sqThumb;
    }

    public String getHqThumb() {
        return hqThumb;
    }

    public void setHqThumb(String hqThumb) {
        this.hqThumb = hqThumb;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
