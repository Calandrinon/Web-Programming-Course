package com.uppicvote.model;


public class Image {
    private static final String persistencePath = "/home/calandrinon/Documents/an2sem2/Web-Programming/Lab-9/UpPicVote/src/main/webapp/Files";
    private Integer id;
    private String filename;
    private Integer numberOfVotes;
    private Integer userId;
    private String description;

    public Image(String filename, String description) {
        this.id = -1;
        this.filename = filename;
        this.numberOfVotes = 0;
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public Integer getNumberOfVotes() {
        return numberOfVotes;
    }

    public void setNumberOfVotes(Integer numberOfVotes) {
        this.numberOfVotes = numberOfVotes;
    }

    public void incrementNumberOfVotes() {
        this.numberOfVotes++;
    }

    public void decrementNumberOfVotes() {
        this.numberOfVotes--;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
