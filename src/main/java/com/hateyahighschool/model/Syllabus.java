package com.hateyahighschool.model;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by A.A.MAMUN on 8/28/2019.
 */
@Entity
public class Syllabus {

    @Id
    private int forCls;
    private String link;

    public Syllabus() {
    }

    public int getForCls() {
        return forCls;
    }

    public void setForCls(int forCls) {
        this.forCls = forCls;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @Override
    public String toString() {
        return "Syllabus{" +
                "forCls=" + forCls +
                ", link='" + link + '\'' +
                '}';
    }
}
