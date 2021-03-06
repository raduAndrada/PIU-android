package com.utcluj.laborator_1_android;

import java.io.Serializable;

/**
 * Created by Student on 11/21/2017.
 */
public class Trip implements Serializable{

    private String title;

    private String description;

    private String price;

    private String imgName;

    private boolean isFavourite;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getImgName() {
        return imgName;
    }

    public void setImgName(String imgName) {
        this.imgName = imgName;
    }

    public boolean isFavourite() {
        return isFavourite;
    }


    public void setIsFavourite(boolean isFavourite) {
        this.isFavourite = isFavourite;
    }

    public Trip(String title, String description, String price, String imgName) {
        this.title = title;
        this.description = description;
        this.price = price;
        this.imgName = imgName;
    }
}
