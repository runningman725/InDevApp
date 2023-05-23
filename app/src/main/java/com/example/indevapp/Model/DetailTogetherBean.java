package com.example.indevapp.Model;

public class DetailTogetherBean {

    private String area;
    private String image;
    private String endDate;
    private String togetherCnt;
    private String leaderId;
    private String title;
    private String uid;
    private String togetherList;
    private String intro;
    private String time;
    private String woroutLevel;
    private String startDate;
    private String sportsList;

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getTogetherCnt() {
        return togetherCnt;
    }

    public void setTogetherCnt(String togetherCnt) {
        this.togetherCnt = togetherCnt;
    }

    public String getLeaderId() {
        return leaderId;
    }

    public void setLeaderId(String leaderId) {
        this.leaderId = leaderId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getTogetherList() {
        return togetherList;
    }

    public void setTogetherList(String togetherList) {
        this.togetherList = togetherList;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getWoroutLevel() {
        return woroutLevel;
    }

    public void setWoroutLevel(String woroutLevel) {
        this.woroutLevel = woroutLevel;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getSportsList() {
        return sportsList;
    }

    public void setSportsList(String sportsList) {
        this.sportsList = sportsList;
    }

    @Override
    public String toString() {
        return "DetailTogetherBean{" +
                "area='" + area + '\'' +
                ", image='" + image + '\'' +
                ", endDate='" + endDate + '\'' +
                ", togetherCnt='" + togetherCnt + '\'' +
                ", leaderId='" + leaderId + '\'' +
                ", title='" + title + '\'' +
                ", uid='" + uid + '\'' +
                ", togetherList='" + togetherList + '\'' +
                ", intro='" + intro + '\'' +
                ", time='" + time + '\'' +
                ", woroutLevel='" + woroutLevel + '\'' +
                ", startDate='" + startDate + '\'' +
                ", sportsList='" + sportsList + '\'' +
                '}';
    }
}
