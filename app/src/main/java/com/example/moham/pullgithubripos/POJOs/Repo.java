package com.example.moham.pullgithubripos.POJOs;

import android.graphics.Color;

import com.google.gson.annotations.SerializedName;

/**
 * Created by moham on 8/19/2017.
 */

public class Repo {
    private int id;
    private String name;
    @SerializedName("html_url")
    private String url;
    private Owner owner;
    @SerializedName("description")
    private String desc;

    public boolean isFork() {
        return fork;
    }

    public void setFork(boolean fork) {
        this.fork = fork;
    }

    private boolean fork;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getCardBackGround(Repo m){
        if(m.isFork()){
            return Color.parseColor("#90EE90");
        }
        return Color.parseColor("#ffffff");
    }
}
