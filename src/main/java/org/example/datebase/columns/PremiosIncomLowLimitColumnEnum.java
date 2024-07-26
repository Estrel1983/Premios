package org.example.datebase.columns;

public enum PremiosIncomLowLimitColumnEnum {
    ID("id"),
    POSITION_NAME ("positionName"),
    LIMIT_SUM ("limitSum");
    private final String columnName;
    PremiosIncomLowLimitColumnEnum  (String columnName){
        this.columnName = columnName;
    }

    public String getColumnName() {
        return columnName;
    }
}
