package com.hateyahighschool.model;

import javax.persistence.*;

/**
 * Created by A.A.MAMUN on 8/20/2019.
 */
@Entity
public class Teacher {

   // public static final int CURRENT = 201, RETIRED = 202;
    @Id
    @SequenceGenerator(name="TeacherSequence", sequenceName="TeacherSequence",allocationSize=1)
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="TeacherSequence")
    private Integer id;
    private String type;
    private String name;
    private String img;
    private String designation;
    private String address;
    private String email;
    private String phone;
    private String subject;
    private String degree;
    private String dob;
    private String joinDate;
    @Column(length = 1000)
    private String description;
    private String facebookLink;
    private String twitterLink;

    public Teacher(){}

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(String joinDate) {
        this.joinDate = joinDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFacebookLink() {
        return facebookLink;
    }

    public void setFacebookLink(String facebookLink) {
        this.facebookLink = facebookLink;
    }

    public String getTwitterLink() {
        return twitterLink;
    }

    public void setTwitterLink(String twitterLink) {
        this.twitterLink = twitterLink;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "id=" + id +
                ", type=" + type +
                ", name='" + name + '\'' +
                ", img='" + img + '\'' +
                ", designation='" + designation + '\'' +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", subject='" + subject + '\'' +
                ", degree='" + degree + '\'' +
                ", dob='" + dob + '\'' +
                ", joinDate='" + joinDate + '\'' +
                ", description='" + description + '\'' +
                ", facebookLink='" + facebookLink + '\'' +
                ", twitterLink='" + twitterLink + '\'' +
                '}';
    }
}
