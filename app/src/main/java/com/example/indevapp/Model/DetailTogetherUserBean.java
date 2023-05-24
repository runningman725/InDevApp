package com.example.indevapp.Model;

import java.io.Serializable;

public class DetailTogetherUserBean implements Serializable {
    private String uid;
    private double smmChange;
    private String sex;
    private double weightChange;
    private double age;
    private double pbfChange;
    private double height;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public double getAge() {
        return age;
    }

    public void setAge(double age) {
        this.age = age;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getSmmChange() {
        return smmChange;
    }

    public void setSmmChange(double smmChange) {
        this.smmChange = smmChange;
    }

    public double getWeightChange() {
        return weightChange;
    }

    public void setWeightChange(double weightChange) {
        this.weightChange = weightChange;
    }

    public double getPbfChange() {
        return pbfChange;
    }

    public void setPbfChange(double pbfChange) {
        this.pbfChange = pbfChange;
    }

    @Override
    public String toString() {
        return "DetailTogetherUserBean{" +
                "uid='" + uid + '\'' +
                ", smmChange=" + smmChange +
                ", sex='" + sex + '\'' +
                ", weightChange=" + weightChange +
                ", age=" + age +
                ", pbfChange=" + pbfChange +
                ", height=" + height +
                '}';
    }
}
