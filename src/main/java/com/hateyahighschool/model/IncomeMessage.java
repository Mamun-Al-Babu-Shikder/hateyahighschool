package com.hateyahighschool.model;

import javax.persistence.*;

/**
 * Created by A.A.MAMUN on 8/17/2019.
 */
@Entity
public class IncomeMessage {

    public static final int SEEN = 101, UNSEEN = 102;

    @Id
    @SequenceGenerator(name="IncomeMessageSequence", sequenceName="IncomeMessageSequence",allocationSize=1)
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="IncomeMessageSequence")
    private Integer id;
    private String name;
    private String email;
    private String phone;
    private String date;
    private String message;
    private int status;

    public IncomeMessage() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}


