package com.andregcaires.rabbitmqconsumer.entity;

import java.time.LocalDate;

import com.andregcaires.rabbitmqconsumer.json.CustomLocalDateDeserializer;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

public class Employee {

    public Employee(String employeeId, String name, LocalDate birthDate) {
        super();
        this.birthDate = birthDate;
        this.name = name;
        this.employeeId = employeeId;
    }

    public Employee() {
    }

    @Override
    public String toString() {
        return "ID: " + employeeId + ", Name: " + name + ", Birthdate: "+ birthDate;
    }

    @JsonProperty("employee_id")
    private String employeeId;

    private String name;

    @JsonProperty("birth_date")
    @JsonDeserialize(using = CustomLocalDateDeserializer.class)
    private LocalDate birthDate;

    public String getEmployeeId() {
        return employeeId;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }
}