package org.example.model;

public class PremiosPercent {
    private Integer id;
    private String positionName;
    private Long percent;

    public PremiosPercent(String positionName, Long percent) {
        this.positionName = positionName;
        this.percent = percent;
    }

    public PremiosPercent(Integer id, String positionName, Long percent) {
        this.id = id;
        this.positionName = positionName;
        this.percent = percent;
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

    public Long getPercent() {
        return percent;
    }

    public void setPercent(Long percent) {
        this.percent = percent;
    }
}
