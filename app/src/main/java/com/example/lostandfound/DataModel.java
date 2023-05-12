package com.example.lostandfound;

public class DataModel {

    String postType;
    String description;

    public int getDatabase_id() {
        return database_id;
    }

    public void setDatabase_id(int database_id) {
        this.database_id = database_id;
    }

    int database_id;

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

    public DataModel(String postType, String description, int database_id) {
        this.postType = postType;
        this.description = description;
        this.database_id = database_id;
    }

}
