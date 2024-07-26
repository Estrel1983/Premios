package org.example.dataMapper;

import org.example.datebase.columns.EmployeColumnsEnum;
import org.example.model.Employe;
import org.example.model.TableModel;
import org.example.utils.Constants;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import static org.example.db.DateBaseOperation.getDbConnection;

public class EmployeMapper {
    private static Connection conn = getDbConnection();
    private static final String SORT_FIELD = "name";

    private static final String INSERT_QUERY = "INSERT INTO %s (id, name, positionName, startDate) VALUES (?, ?, ?, ?)";
    private static final String UPDATE_QUERY = "UPDATE %s SET endDate = ?, startDate = ? WHERE ID = ?";

    public ArrayList<TableModel> selectAll() {
        ArrayList<TableModel> empList = new ArrayList<>();
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

    public boolean insert(Employe employe) {
        int nextId = 0;
        SimpleDateFormat dateFormat = new SimpleDateFormat(Constants.SQL_DATE_FORMAT);
        try (Statement st = conn.createStatement()) {
            ResultSet resultSet = st.executeQuery(String.format(Constants.GET_MAX_ID_QUERY, Constants.EMPLOYEES_TABLE_NAME));
            if (resultSet.next())
                nextId = resultSet.getInt(1) + 1;
        } catch (SQLException e) {
            //TODO
            return false;
        }
        try (PreparedStatement insertSt = conn.prepareStatement(String.format(INSERT_QUERY, Constants.EMPLOYEES_TABLE_NAME));) {
            insertSt.setInt(1, nextId);
            insertSt.setString(2, employe.getName());
            insertSt.setString(3, employe.getPositionName());
            insertSt.setString(4, dateFormat.format(employe.getStartDate()));
            if (insertSt.executeUpdate() == 1) {
                return true;
            }
        } catch (SQLException e) {
            //TODO
            return false;
        }
        return false;
    }

    public boolean update(Employe employe) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(Constants.SQL_DATE_FORMAT);
        try (PreparedStatement updateSt = conn.prepareStatement(String.format(UPDATE_QUERY, Constants.EMPLOYEES_TABLE_NAME))) {
            updateSt.setString(1, dateFormat.format(employe.getEndDate()));
            updateSt.setString(2, dateFormat.format(employe.getStartDate()));
            updateSt.setInt(3, employe.getId());
            if (updateSt.executeUpdate() == 1)
                return true;
        } catch (SQLException e) {
            //TODO
            return false;
        }
        return false;
    }

    public boolean updateByColumnValue(EmployeColumnsEnum columnName, String oldValue, String newValue) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(Constants.SQL_DATE_FORMAT);
        try (PreparedStatement updateSt = conn.prepareStatement(String.format(Constants.UPDATE_SINGLE_COLUMN_QUERY, Constants.EMPLOYEES_TABLE_NAME, columnName.getColumnName()))){
          switch (columnName){
              case NAME:
              case POS_NAME:
                  updateSt.setString(1, newValue);
                  updateSt.setString(2, oldValue);
                  break;
              case STAR_DATE:
              case END_DATE:
                  updateSt.setString(1, dateFormat.format(newValue));
                  updateSt.setString(2, dateFormat.format(oldValue));
                  break;
          }
          if (updateSt.executeUpdate() == 1){
              return true;
          }
        } catch (SQLException e) {
            //TODO
            return false;
        }
        return false;
    }
}
