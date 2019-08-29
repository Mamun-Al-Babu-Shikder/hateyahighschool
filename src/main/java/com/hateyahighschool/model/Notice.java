package com.hateyahighschool.model;

import javax.persistence.*;

/**
 * Created by A.A.MAMUN on 8/13/2019.
 */
@Entity
public class Notice {


    @Id
    @SequenceGenerator(name="NoticeSequence", sequenceName="NoticeSequence",allocationSize=1)
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="NoticeSequence")
    private Integer id;
    private String title;
    private String date;
    private String body;

    public Notice(){}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return "Notice{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", date='" + date + '\'' +
                ", body='" + body + '\'' +
                '}';
    }
}
