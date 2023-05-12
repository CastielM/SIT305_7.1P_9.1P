package com.example.lostandfound;

public class DataModel {

    String postType;
    String description;

    public String getPostType() {
        return postType;
    }

    public void setPostType(String postType) {
        this.postType = postType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public DataModel(String postType, String description) {
        this.postType = postType;
        this.description = description;
    }



}
