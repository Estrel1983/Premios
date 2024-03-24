package org.example.dataMapper;

import org.example.model.Individual;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;

import static org.example.db.DateBaseOperation.getDbConnection;

public class IndividualMapper {
    public static Connection conn = getDbConnection();
    public static final String EMPLOYEE_TABLE_NAME = "individuals";
    public static final String INSERT_QUERY = "INSERT INTO %s (id, name, birthDate) VALUES (%s, '%s', '%s')";
    public static final String GET_MAX_ID_QUERY = "SELECT MAX(id) FROM %s";

    public static final String SQL_DATE_FORMAT = "YYYY-MM-dd";
    public boolean insert (Individual individual){
        try (Statement statement = conn.createStatement()){
            SimpleDateFormat dateFormat = new SimpleDateFormat(SQL_DATE_FORMAT);
            ResultSet resultSet = statement.executeQuery(String.format(GET_MAX_ID_QUERY, EMPLOYEE_TABLE_NAME));
            resultSet.next();
            int nextId = resultSet.getInt(1) +1;
            if ((statement.executeUpdate(String.format(INSERT_QUERY, EMPLOYEE_TABLE_NAME, nextId,
                    individual.getName(), dateFormat.format(individual.getBirthDate())))) > 0) {
                return true;
            }
        } catch (SQLException e) {
            return false;
        }
        return false;
    }
}
