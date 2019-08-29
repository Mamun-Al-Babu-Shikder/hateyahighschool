package com.hateyahighschool.model;

import javax.persistence.*;

/**
 * Created by A.A.MAMUN on 8/26/2019.
 */
@Entity
public class Book {

    @Id
    @SequenceGenerator(name="BookSequence", sequenceName="BookSequence",allocationSize=1)
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="BookSequence")
    private Integer id;
    private String imgUrl;
    private String pdfLink;
    private String name;
    private String author;
    private String uploadDate;
    private Integer view;
    @Column(length = 3)
    private String forCls;

    public Book(){}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getPdfLink() {
        return pdfLink;
    }

    public void setPdfLink(String pdfLink) {
        this.pdfLink = pdfLink;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(String uploadDate) {
        this.uploadDate = uploadDate;
    }

    public Integer getView() {
        return view;
    }

    public void setView(Integer view) {
        this.view = view;
    }

    public String getForCls() {
        return forCls;
    }

    public void setForCls(String forCls) {
        this.forCls = forCls;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", imgUrl='" + imgUrl + '\'' +
                ", pdfLink='" + pdfLink + '\'' +
                ", name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", uploadDate='" + uploadDate + '\'' +
                ", view=" + view +
                ", forCls=" + forCls +
                '}';
    }
}
