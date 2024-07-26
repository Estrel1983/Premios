package org.example.dataMapper;

import org.example.datebase.columns.PremiosIncomLowLimitColumnEnum;
import org.example.datebase.columns.PremiosPercentColumnEnum;
import org.example.model.PremiosIncomLowLimit;
import org.example.model.PremiosPercent;
import org.example.utils.Constants;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import static org.example.db.DateBaseOperation.getDbConnection;

public class PremiosIncomLowLimitMapper {
    private static Connection conn = getDbConnection();
    public ArrayList<PremiosIncomLowLimit> selectByColumnValue (PremiosIncomLowLimitColumnEnum columnName, String searchValue) {
        ArrayList<PremiosIncomLowLimit> premArrayList = new ArrayList<>();
        try (PreparedStatement updateSt = conn.prepareStatement(String.format(Constants.SELECT_BY_COLUMN_QUERY,
                Constants.PREMIOS_INCOM_LOW_LIMIT_TABLE_NAME, columnName.getColumnName()))) {
            switch (columnName) {
                case POSITION_NAME:
                    updateSt.setString(1, searchValue);
                    break;
                case LIMIT_SUM:
                    updateSt.setInt(1, Integer.parseInt(searchValue));
                    break;
            }
            ResultSet resultSet = updateSt.executeQuery();
            while (resultSet.next()){
                premArrayList.add(new PremiosIncomLowLimit(resultSet.getInt(PremiosIncomLowLimitColumnEnum.ID.getColumnName()),
                        resultSet.getString(PremiosIncomLowLimitColumnEnum.POSITION_NAME.getColumnName()),
                        resultSet.getInt(PremiosIncomLowLimitColumnEnum.LIMIT_SUM.getColumnName())));
            }
        } catch (
                SQLException e) {
            //TODO
            return null;
        }
        return premArrayList;
    }
    public boolean updateByColumnValue(PremiosIncomLowLimitColumnEnum columnName, String oldValue, String newValue) {
        try (PreparedStatement updateSt = conn.prepareStatement(String.format(Constants.UPDATE_SINGLE_COLUMN_QUERY,
                Constants.PREMIOS_INCOM_LOW_LIMIT_TABLE_NAME, columnName.getColumnName()))){
            switch (columnName){
                case POSITION_NAME:
                    updateSt.setString(1, newValue);
                    updateSt.setString(2, oldValue);
                    break;
                case LIMIT_SUM:
                    updateSt.setInt(1, Integer.parseInt(newValue));
                    updateSt.setInt(2, Integer.parseInt(oldValue));
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
