package com.smartMatch.likes;

import org.springframework.core.style.ToStringCreator;

public class Likes {

    private String netId;

    private String likes;

    public Likes(String net_id, String likes) {
        this.netId = net_id;
        this.likes = likes;
    }

    public Likes() {
        this.netId = "";
        this.likes = "";
    }

    @Override
    public String toString() {
        return new ToStringCreator(this).append("net_id: ", this.getNet_id()).append("likes: ", this.getLikes())
                .toString();
    }

    public String getLikes() {
        return this.likes;
    }

    public void addFirstLike(String net_id) {
        this.likes = net_id;
    }

    public void addLike(String net_id) {
        this.likes += "," + net_id;
    }

    public String getNet_id() {
        return this.netId;
    }

    public void setNet_id(String net_id) {
        this.netId = net_id;
    }
}
