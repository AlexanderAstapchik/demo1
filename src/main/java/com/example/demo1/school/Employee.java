package com.example.demo1.school;

public class Employee {
    private int id;
    private String firstName;
    private String lastName;
    private int workExperience;
    private String specialization;

    public Employee(int id, String firstName, String lastName, int workExperience, String specialization) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.workExperience = workExperience;
        this.specialization = specialization;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getWorkExperience() {
        return workExperience;
    }

    public void setWorkExperience(int workExperience) {
        this.workExperience = workExperience;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", workExperience=" + workExperience +
                ", specialization='" + specialization + '\'' +
                '}';
    }
}
