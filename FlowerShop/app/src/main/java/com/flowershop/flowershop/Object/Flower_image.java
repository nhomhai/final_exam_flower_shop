package com.flowershop.flowershop.Object;

/**
 * Created by kenjita.tran on 3/10/18.
 */

public class Flower_image {
    private String image;
    private String key;

    public void Flower_image(String image, String key)
    {
        this.image=image;
        this.key=key;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    public String getImage() {
        return image;
    }
}