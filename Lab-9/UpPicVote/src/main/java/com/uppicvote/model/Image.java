package com.uppicvote.model;


public class Image {
    private Integer id;
    private String path;
    private Integer numberOfVotes;

    public Image(Integer id, String path) {
        this.id = id;
        this.path = path;
        this.numberOfVotes = 0;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
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

}
