package org.example.dataMapper;

import org.example.model.Employe;
import org.example.model.TableModel;
import org.example.utils.Constants;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import static org.example.db.DateBaseOperation.getDbConnection;

public class EmployeMapper {
    private static Connection conn = getDbConnection();
    private static final String SORT_FIELD = "name";

    public ArrayList<TableModel> selectAll() {
        ArrayList<TableModel> empList = new ArrayList<>();
        try (Statement statement = conn.createStatement()) {
            ResultSet result = statement.executeQuery(String.format(Constants.SELECT_ALL_QUERY, Constants.EMPLOYEES_TABLE_NAME, SORT_FIELD));
            while (result.next()) {

                empList.add(new Employe(result.getInt("id"), result.getString("name"),
                        result.getString("positionName"),
                        (new SimpleDateFormat(Constants.JAVA_FROM_SQL_DATA_FORMAT)).parse(result.getString("startDate")),
                        result.getString("endDate").equals("NULL") ? null : (new SimpleDateFormat(Constants.JAVA_FROM_SQL_DATA_FORMAT)).parse(result.getString("endDate"))));
            }

        } catch (SQLException | ParseException e) {
            return null;
            //TODO
        }
        return empList;
    }
}
