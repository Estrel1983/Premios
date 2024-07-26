package org.example.datebase.columns;

public enum PremiosPercentColumnEnum {
    ID("id"),
    POSITION_NAME ("positionName"),
    PERCENT ("percent");
    private final String columnName;
    PremiosPercentColumnEnum  (String columnName){
        this.columnName = columnName;
    }

    public String getColumnName() {
        return columnName;
    }
}
