package org.example.dto;

public class PremiosForPositions {
    private String positionName;
    private double percent;
    private int minLimit;

    public PremiosForPositions(String positionName, double percent, int minLimit) {
        this.positionName = positionName;
        this.percent = percent;
        this.minLimit = minLimit;
    }

    public PremiosForPositions() {
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    public double getPercent() {
        return percent;
    }

    public void setPercent(double percent) {
        this.percent = percent;
    }

    public int getMinLimit() {
        return minLimit;
    }

    public void setMinLimit(int minLimit) {
        this.minLimit = minLimit;
    }
}
