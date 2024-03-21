package org.example.model;

public class position {
    private Integer id;
    private String positionName;

    public position(String positionName) {
        this.positionName = positionName;
    }

    public position(Integer id, String positionName) {
        this.id = id;
        this.positionName = positionName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }
}
