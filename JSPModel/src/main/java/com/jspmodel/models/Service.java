package com.jspmodel.models;

public class Service {
    private int id, employee_id;
    private String name;
    private int cost;

    public Service() {
        super();
    }

    public Service(int id, int employee_id, String name, int cost) {
        super();
        this.id = id;
        this.employee_id = employee_id;
        this.name = name;
        this.cost = cost;
    }

    public int getId() {
        return id;
    }

    public int getEmployee_id() {
        return employee_id;
    }

    public String getName() {
        return name;
    }

    public int getCost() {
        return cost;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setEmployee_id(int employee_id) {
        this.employee_id = employee_id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }
}
