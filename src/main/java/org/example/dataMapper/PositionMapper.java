package org.example.dataMapper;

import org.example.model.Position;
import org.example.utils.Constants;

import java.sql.*;
import java.util.ArrayList;

import static org.example.db.DateBaseOperation.getDbConnection;

public class PositionMapper {
    private static Connection conn = getDbConnection();
    private static final String INSERT_QUERY = "INSERT INTO %s (id, positionName) VALUES (%s, '%s')";
    private static final String UPDATE_QUERY = "UPDATE %s SET positionName = ? WHERE id = ?";
    private static final String SORT_FIELD = "positionName";

    public ArrayList<Position> selectAll() {
        ArrayList<Position> posList = new ArrayList<>();
        try (Statement statement = conn.createStatement()) {
            ResultSet result = statement.executeQuery(String.format(Constants.SELECT_ALL_QUERY, Constants.POSITION_TABLE_NAME, SORT_FIELD));
            while (result.next()) {
                posList.add(new Position(result.getInt("id"), result.getString("positionName")));
            }

        } catch (SQLException e) {
            return null;
            //TODO
        }
        return posList;
    }

    public boolean insert(Position position) {
        try (Statement statement = conn.createStatement()) {
            ResultSet resultSet = statement.executeQuery(String.format(Constants.GET_MAX_ID_QUERY, Constants.POSITION_TABLE_NAME));
            int nextId = 0;
            if (resultSet.next())
                nextId = resultSet.getInt(1) + 1;
            if ((statement.executeUpdate(String.format(INSERT_QUERY, Constants.POSITION_TABLE_NAME, nextId,
                    position.getPositionName()))) > 0) {
                return true;
            }
        } catch (SQLException e) {
            return false;
        }
        return false;
    }

    public boolean update(Position position){
        try (PreparedStatement updateStatement = conn.prepareStatement(String.format(UPDATE_QUERY, Constants.POSITION_TABLE_NAME));){
            updateStatement.setString(1, position.getPositionName());
            updateStatement.setInt(2, position.getId());
            if (updateStatement.executeUpdate() == 1)
                return true;
        } catch (SQLException e) {
            return false;
        }
        return false;
    }
}
