package com.anim.animpedia;

public class Animal {

    private String title;
    private int imgRes;

    public Animal(String title, int imgRes) {
        this.title = title;
        this.imgRes = imgRes;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getImgRes() {
        return imgRes;
    }

    public void setImgRes(int imgRes) {
        this.imgRes = imgRes;
    }
}
