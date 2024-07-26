package org.example.dataMapper;

import org.example.datebase.columns.EmployeColumnsEnum;
import org.example.datebase.columns.PremiosPercentColumnEnum;
import org.example.model.PremiosPercent;
import org.example.utils.Constants;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import static org.example.datebase.columns.PremiosPercentColumnEnum.POSITION_NAME;
import static org.example.db.DateBaseOperation.getDbConnection;

public class PremiosPercentMapper {
    private static Connection conn = getDbConnection();
    public ArrayList <PremiosPercent> selectByColumnValue (PremiosPercentColumnEnum columnName, String searchValue) {
        ArrayList<PremiosPercent> premArrayList = new ArrayList<>();
        try (PreparedStatement updateSt = conn.prepareStatement(String.format(Constants.SELECT_BY_COLUMN_QUERY,
                Constants.PREMIOS_PERCENT_TABLE_NAME, columnName.getColumnName()))) {
            switch (columnName) {
                case POSITION_NAME:
                    updateSt.setString(1, searchValue);
                    break;
                case PERCENT:
                    updateSt.setDouble(1, Double.parseDouble(searchValue));
                    break;
            }
            ResultSet resultSet = updateSt.executeQuery();
            while (resultSet.next()){
                premArrayList.add(new PremiosPercent(resultSet.getInt("id"),
                        resultSet.getString("positionName"), resultSet.getDouble("percent")));
            }
        } catch (
                SQLException e) {
            //TODO
            return null;
        }
        return premArrayList;
    }

    public boolean updateByColumnValue(PremiosPercentColumnEnum columnName, String oldValue, String newValue) {
        try (PreparedStatement updateSt = conn.prepareStatement(String.format(Constants.UPDATE_SINGLE_COLUMN_QUERY, Constants.PREMIOS_PERCENT_TABLE_NAME, columnName.getColumnName()))){
            switch (columnName){
                case POSITION_NAME:
                    updateSt.setString(1, newValue);
                    updateSt.setString(2, oldValue);
                    break;
                case PERCENT:
                    updateSt.setDouble(1, Double.parseDouble(newValue));
                    updateSt.setDouble(2, Double.parseDouble(oldValue));
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
