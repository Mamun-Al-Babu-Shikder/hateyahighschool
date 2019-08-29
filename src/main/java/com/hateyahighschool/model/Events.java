package com.hateyahighschool.model;

import javax.persistence.*;

/**
 * Created by A.A.MAMUN on 8/13/2019.
 */
@Entity
public class Events {

    @Id
    @SequenceGenerator(name="EventsSequence", sequenceName="EventsSequence",allocationSize=1)
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="EventsSequence")
    private Integer id;
    private String type;
    private String imgName;
    private String title;
    private String date;
    @Column(length = 5)
    private String dd;
    @Column(length = 5)
    private String mm;
    @Column(length = 10)
    private String timeFrom;
    @Column(length = 10)
    private String timeTo;
    private String location;
    @Column(length = 1000)
    private String body;


    public Events(){}


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getImgName() {
        return imgName;
    }

    public void setImgName(String imgName) {
        this.imgName = imgName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDd() {
        return dd;
    }

    public void setDd(String dd) {
        this.dd = dd;
    }

    public String getMm() {
        return mm;
    }

    public void setMm(String mm) {
        this.mm = mm;
    }


    public String getTimeFrom() {
        return timeFrom;
    }

    public void setTimeFrom(String timeFrom) {
        this.timeFrom = timeFrom;
    }

    public String getTimeTo() {
        return timeTo;
    }

    public void setTimeTo(String timeTo) {
        this.timeTo = timeTo;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return "Events{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", imgName='" + imgName + '\'' +
                ", title='" + title + '\'' +
                ", date='" + date + '\'' +
                ", dd='" + dd + '\'' +
                ", mm='" + mm + '\'' +
                ", timeFrom='" + timeFrom + '\'' +
                ", timeTo='" + timeTo + '\'' +
                ", location='" + location + '\'' +
                ", body='" + body + '\'' +
                '}';
    }
}
