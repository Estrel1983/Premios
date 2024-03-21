package org.example.model;

import java.util.Date;

public class EmployeesPerDay {
    private Integer id;
    private Date cDate;
    private String name;
    private Long premio;

    public EmployeesPerDay(Integer id, Date cDate, String name) {
        this.id = id;
        this.cDate = cDate;
        this.name = name;
    }

    public EmployeesPerDay(Date cDate, String name) {
        this.cDate = cDate;
        this.name = name;
    }

    public EmployeesPerDay(Date cDate, String name, Long premio) {
        this.cDate = cDate;
        this.name = name;
        this.premio = premio;
    }

    public EmployeesPerDay(Integer id, Date cDate, String name, Long premio) {
        this.id = id;
        this.cDate = cDate;
        this.name = name;
        this.premio = premio;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getcDate() {
        return cDate;
    }

    public void setcDate(Date cDate) {
        this.cDate = cDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getPremio() {
        return premio;
    }

    public void setPremio(Long premio) {
        this.premio = premio;
    }
}
