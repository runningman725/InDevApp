package com.example.indevapp.bean;

import java.io.Serializable;

public class GoodsEntity implements Serializable {
    public String imgPath;//图片地址
    public String title;//货物名称
    public String contents;//货物价格
    public String date;//货物价格
    public String comment;//货物价格

    public GoodsEntity() {
    }

    public GoodsEntity(String imgPath, String title, String contents, String date, String comment) {
        this.imgPath = imgPath;
        this.title = title;
        this.contents = contents;
        this.date = date;
        this.comment = comment;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "GoodsEntity{" +
                "imgPath='" + imgPath + '\'' +
                ", title='" + title + '\'' +
                ", contents='" + contents + '\'' +
                ", date='" + date + '\'' +
                ", comment='" + comment + '\'' +
                '}';
    }
}