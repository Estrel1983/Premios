package org.example.utils;

public class Constants {
    public static final String INSERT_ERROR_MESSAGE = "Данные не могут быть внесены в базу данных";
    public static final String SELECT_ERROR_MESSAGE = "в настоящий момент данные не могут быть получены из базы данных";
    public static final String JAVA_FROM_SQL_DATA_FORMAT = "yyyy-MM-dd";
    public static final String SQL_DATE_FORMAT = "YYYY-MM-dd";
    public static String DATE_FORMAT_FOR_TABLE = "dd-MM-yyyy";
    public static final String GET_MAX_ID_QUERY = "SELECT MAX(id) FROM %s";
    public static final String SELECT_ALL_QUERY = "SELECT * FROM %s ORDER BY %s ASC";

    //Table Names
    public static final String POSITION_TABLE_NAME = "position";
    public static final String INDIVIDUALS_TABLE_NAME = "individuals";
    public static final String EMPLOYEES_TABLE_NAME = "employees";
    private Constants(){}
}
