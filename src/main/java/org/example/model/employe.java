package org.example.model;

import java.util.Date;

public class employe {
    private Integer id;
    private String name;
    private String positionName;
    private Date startDate;
    private Date endDate;

    public employe(Integer id, String name, String positionName, Date startDate) {
        this.id = id;
        this.name = name;
        this.positionName = positionName;
        this.startDate = startDate;
    }

    public employe(String name, String positionName, Date startDate) {
        this.name = name;
        this.positionName = positionName;
        this.startDate = startDate;
    }

    public employe(Integer id, String name, String positionName, Date startDate, Date endDate) {
        this.id = id;
        this.name = name;
        this.positionName = positionName;
        this.startDate = startDate;
        this.endDate = endDate;
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

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
