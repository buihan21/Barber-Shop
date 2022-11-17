package com.jspmodel.models;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDateTime;

public class Booking {
    private int id, user_id, service_id, employee_id;
    private Time set_hour;
    private Date set_date;
    private String note;

    public Booking() {
        super();
    }

    public Booking(int user_id, int service_id, int employee_id, Time set_hour,Date set_date, String note) {
        super();
        this.user_id = user_id;
        this.service_id = service_id;
        this.employee_id = employee_id;
        this.set_hour = set_hour;
        this.set_date = set_date;
        this.note = note;
    }

    public Booking(int user_id, int service_id, int employee_id, Time set_hour, Date set_date) {
        this.user_id = user_id;
        this.service_id = service_id;
        this.employee_id = employee_id;
        this.set_hour = set_hour;
        this.set_date = set_date;
    }

    public Booking(int service_id, int employee_id, Time set_hour, Date set_date) {
        this.service_id = service_id;
        this.employee_id = employee_id;
        this.set_hour = set_hour;
        this.set_date = set_date;
    }

    @Override
    public String toString(){
        return this.user_id+" "+this.service_id+" "+this.employee_id+" "+this.set_hour+" "+this.set_date;
    }


    public int getId() {
        return id;
    }

    public int getUser_id() {
        return user_id;
    }

    public int getService_id() {
        return service_id;
    }

    public int getEmployee_id() {
        return employee_id;
    }

    public Time getSet_hour() {
        return set_hour;
    }

    public Date getSet_date() {
        return set_date;
    }

    public void setSet_date(Date set_date) {
        this.set_date = set_date;
    }

    public String getNote() {
        return note;
    }


    public void setId(int id) {
        this.id = id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public void setService_id(int service_id) {
        this.service_id = service_id;
    }

    public void setEmployee_id(int employee_id) {
        this.employee_id = employee_id;
    }

    public void setSet_hour(Time set_hour) {
        this.set_hour = set_hour;
    }

    public void setNote(String note) {
        this.note = note;
    }
}

