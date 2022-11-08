package com.jspmodel.models;

import java.sql.Date;

public class Booking {
    private int id, user_id, service_id, employee_id;
    private Date set_hour;
    private String note;
    private Date created_at;

    public Booking() {
        super();
    }

    public Booking(int id, int user_id, int service_id, int employee_id, Date set_hour, String note, Date created_at) {
        super();
        this.id = id;
        this.user_id = user_id;
        this.service_id = service_id;
        this.employee_id = employee_id;
        this.set_hour = set_hour;
        this.note = note;
        this.created_at = created_at;
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

    public Date getSet_hour() {
        return set_hour;
    }

    public String getNote() {
        return note;
    }

    public Date getCreated_at() {
        return created_at;
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

    public void setSet_hour(Date set_hour) {
        this.set_hour = set_hour;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }
}
