package org.example.model;

public class PremiosIncomLowLimit {
    private Integer id;
    private String positionName;
    private Integer limitSum;

    public PremiosIncomLowLimit(String positionName, Integer limitSum) {
        this.positionName = positionName;
        this.limitSum = limitSum;
    }

    public PremiosIncomLowLimit(Integer id, String positionName, Integer limitSum) {
        this.id = id;
        this.positionName = positionName;
        this.limitSum = limitSum;
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

    public Integer getLimit() {
        return limitSum;
    }

    public void setLimit(Integer limit) {
        this.limitSum = limit;
    }
}
