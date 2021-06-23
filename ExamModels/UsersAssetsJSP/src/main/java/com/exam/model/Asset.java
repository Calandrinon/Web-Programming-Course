package com.exam.model;

public class Asset {
    private int id, userId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    private String name, description;
    private int value;

    public Asset(int userId, String name, String description, int value) {
        this.userId = userId;
        this.name = name;
        this.description = description;
        this.value = value;
    }
}
