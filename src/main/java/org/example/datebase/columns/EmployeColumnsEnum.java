package org.example.datebase.columns;

public enum EmployeColumnsEnum {
    ID("id"),
    NAME("name"),
    POS_NAME("positionName"),
    STAR_DATE("startDate"),
    END_DATE("endDate");

    private final String columnName;
    EmployeColumnsEnum (String columnName){
        this.columnName = columnName;
    }

    public String getColumnName() {
        return columnName;
    }
}
