package com.felfel.crud.model;

import org.springframework.stereotype.Component;

@Component
public class Student {
    private int id;
    private String name;
    private double gpa;

    /**
     * get field
     *
     * @return id
     */
    public int getId() {
        return this.id;
    }

    /**
     * set field
     *
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * get field
     *
     * @return name
     */
    public String getName() {
        return this.name;
    }

    /**
     * set field
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * get field
     *
     * @return gpa
     */
    public double getGpa() {
        return this.gpa;
    }

    /**
     * set field
     *
     * @param gpa
     */
    public void setGpa(double gpa) {
        this.gpa = gpa;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", gpa=" + gpa +
                '}';
    }
}
