package com.example.lostandfound;

public class DataModel {

    String postType;
    String description;

    int id;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


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

    public DataModel(String postType, String description, int id) {
        this.postType = postType;
        this.description = description;
        this.id = id;
    }



}
