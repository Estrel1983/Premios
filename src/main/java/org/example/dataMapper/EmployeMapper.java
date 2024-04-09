package org.example.dataMapper;

import org.example.model.Employe;
import org.example.utils.Constants;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import static org.example.db.DateBaseOperation.getDbConnection;

public class EmployeMapper {
    private static Connection conn = getDbConnection();
    private static final String SORT_FIELD = "name";
    private static final String UPDATE_QUERY = "UPDATE %s SET startDate = ?, endDate = ? WHERE id = ?";
    private static final String INSERT_QUERY = "INSERT INTO %s (id, name, positionName, startDate, endDate) VALUES (?, ?, ?, ?, ?)";
    public ArrayList<Employe> selectAll() {
        ArrayList<Employe> empList = new ArrayList<>();
        try (Statement statement = conn.createStatement()) {
            ResultSet result = statement.executeQuery(String.format(Constants.SELECT_ALL_QUERY, Constants.EMPLOYEES_TABLE_NAME, SORT_FIELD));
            while (result.next()) {
                empList.add(new Employe(result.getInt("id"), result.getString("name"),
                        result.getString("positionName"),
                        (new SimpleDateFormat(Constants.JAVA_FROM_SQL_DATA_FORMAT)).parse(result.getString("startDate")),
                        result.getString("endDate") == null ? null : (new SimpleDateFormat(Constants.JAVA_FROM_SQL_DATA_FORMAT)).parse(result.getString("endDate"))));
            }

        } catch (SQLException | ParseException e) {
            return null;
            //TODO
        }
        return empList;
    }
    public boolean update (Employe employe){
        SimpleDateFormat dateFormat = new SimpleDateFormat(Constants.SQL_DATE_FORMAT);
        try (PreparedStatement updateStatement = conn.prepareStatement(String.format(UPDATE_QUERY, Constants.EMPLOYEES_TABLE_NAME));){
            updateStatement.setString(1, dateFormat.format(employe.getStartDate()));
            if (employe.getEndDate() != null)
                updateStatement.setString(2, dateFormat.format(employe.getEndDate()));
            else
                updateStatement.setNull(2, Types.VARCHAR);
            updateStatement.setInt(3, employe.getId());
            if (updateStatement.executeUpdate() == 1)
                return true;
        } catch (SQLException e) {
            return false;
        }
        return false;
    }
    public boolean insert (Employe employe){
        int nextId = 1;
        try (Statement statement = conn.createStatement()){
            ResultSet resultSet = statement.executeQuery(String.format(Constants.GET_MAX_ID_QUERY, Constants.EMPLOYEES_TABLE_NAME));
            if (resultSet.next())
                nextId = resultSet.getInt(1) + 1;
        } catch (SQLException e) {
            return false;
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat(Constants.SQL_DATE_FORMAT);
        try (PreparedStatement insertStatement = conn.prepareStatement(String.format(INSERT_QUERY , Constants.EMPLOYEES_TABLE_NAME))) {
            insertStatement.setInt(1, nextId);
            insertStatement.setString(2, employe.getName());
            insertStatement.setString(3, employe.getPositionName());
            insertStatement.setString(4, dateFormat.format(employe.getStartDate()));
            if (employe.getEndDate() != null)
                insertStatement.setString(5, dateFormat.format(employe.getEndDate()));
            else
                insertStatement.setNull(5, Types.VARCHAR);
            if (insertStatement.executeUpdate() == 1)
                return true;
        } catch (SQLException e) {
            return false;
        }
        return false;
    }
}
