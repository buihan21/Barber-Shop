package com.jspmodel.models;

public class Employee {
    private int id;
    private String name, task;
    private int base_salary, working_days;

    public Employee() {
        super();
    }

    public Employee(int id, String name, String task, int base_salary, int working_days) {
        super();
        this.id = id;
        this.name = name;
        this.task = task;
        this.base_salary = base_salary;
        this.working_days = working_days;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getTask() {
        return task;
    }

    public int getBase_salary() {
        return base_salary;
    }

    public int getWorking_days() {
        return working_days;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public void setBase_salary(int base_salary) {
        this.base_salary = base_salary;
    }

    public void setWorking_days(int working_days) {
        this.working_days = working_days;
    }
}
