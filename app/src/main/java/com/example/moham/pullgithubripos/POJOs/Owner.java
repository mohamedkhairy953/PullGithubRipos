package com.example.moham.pullgithubripos.POJOs;

import com.google.gson.annotations.SerializedName;

/**
 * Created by moham on 8/19/2017.
 */

public class Owner {
    private int id;
    private String login;
    @SerializedName("html_url")
    private String url;
    private String avatar_url;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAvatar_url() {
        return avatar_url;
    }

    public void setAvatar_url(String avatar_url) {
        this.avatar_url = avatar_url;
    }
}
