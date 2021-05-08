package com.uppicvote.model;

import java.util.List;

public class ImagesDto {
    List<Image> images;

    public ImagesDto(List<Image> images) {
        this.images = images;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }
}
