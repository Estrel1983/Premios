package org.example.dataMapper;

import org.example.model.Individual;
import org.example.utils.Constants;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import static org.example.db.DateBaseOperation.getDbConnection;

public class IndividualMapper {
    private static final Connection conn = getDbConnection();
    private static final String INSERT_QUERY = "INSERT INTO %s (id, name, birthDate) VALUES (%s, '%s', '%s')";
    private static final String UPDATE_QUERY = "UPDATE %s SET name = ?, birthDate = ? WHERE id = ?";
    private static final String SELECT_BY_NAME_QUERY = "SELECT * FROM %s WHERE name LIKE '%%%s%%' ORDER BY name ASC";
    private static final String SELECT_UNEMPLOYED_QUERY = "SELECT * FROM  %s p LEFT JOIN %s e ON p.name =  e.name WHERE e.name IS NULL OR e.endDate IS NOT NULL";
    private static final String SORT_FIELD = "name";

    public boolean insert (Individual individual){
        try (Statement statement = conn.createStatement()){
            SimpleDateFormat dateFormat = new SimpleDateFormat(Constants.SQL_DATE_FORMAT);
            ResultSet resultSet = statement.executeQuery(String.format(Constants.GET_MAX_ID_QUERY, Constants.INDIVIDUALS_TABLE_NAME));
            resultSet.next();
            int nextId = resultSet.getInt(1) +1;
            if ((statement.executeUpdate(String.format(INSERT_QUERY, Constants.INDIVIDUALS_TABLE_NAME, nextId,
                    individual.getName(), dateFormat.format(individual.getBirthDate())))) > 0) {
                return true;
            }
        } catch (SQLException e) {
            return false;
        }
        return false;
    }
    public ArrayList <Individual> selectAll(){
        ArrayList <Individual> indList = new ArrayList<>();
        try (Statement statement = conn.createStatement()){
            ResultSet result = statement.executeQuery(String.format(Constants.SELECT_ALL_QUERY, Constants.INDIVIDUALS_TABLE_NAME, SORT_FIELD));
            while (result.next()){
                indList.add(new Individual(result.getInt("id"), result.getString("name"),
                        (new SimpleDateFormat(Constants.JAVA_FROM_SQL_DATA_FORMAT)).parse(result.getString("birthDate"))));
            }

        }catch (SQLException | ParseException e) {
            return null;
            //TODO
        }
        return indList;
    }
    public boolean update(Individual individual){
        SimpleDateFormat dateFormat = new SimpleDateFormat(Constants.SQL_DATE_FORMAT);
        try (PreparedStatement updateStatement = conn.prepareStatement(String.format(UPDATE_QUERY, Constants.INDIVIDUALS_TABLE_NAME));){
            updateStatement.setString(1, individual.getName());
            updateStatement.setString(2, dateFormat.format(individual.getBirthDate()));
            updateStatement.setInt(3, individual.getId());
            if (updateStatement.executeUpdate() == 1)
                return true;
        } catch (SQLException e) {
            return false;
        }
        return false;
    }

    public ArrayList <Individual> selectByNamePart(String namePart){
        ArrayList <Individual> indList = new ArrayList<>();
        try (Statement statement = conn.createStatement()){
            ResultSet result = statement.executeQuery(String.format(SELECT_BY_NAME_QUERY, Constants.INDIVIDUALS_TABLE_NAME, namePart));
            while (result.next()){
                indList.add(new Individual(result.getInt("id"), result.getString("name"),
                        (new SimpleDateFormat(Constants.JAVA_FROM_SQL_DATA_FORMAT)).parse(result.getString("birthDate"))));
            }
        } catch (SQLException | ParseException e) {
            return null;
            //TODO
        }
        return indList;
    }
    public ArrayList <Individual> selectUnemployed(){
        ArrayList <Individual> indList = new ArrayList<>();
        try (Statement statement = conn.createStatement()){
            ResultSet result = statement.executeQuery(String.format(SELECT_UNEMPLOYED_QUERY, Constants.INDIVIDUALS_TABLE_NAME, Constants.EMPLOYEES_TABLE_NAME));
            while (result.next()){
                indList.add(new Individual(result.getInt("id"), result.getString("name"),
                        (new SimpleDateFormat(Constants.JAVA_FROM_SQL_DATA_FORMAT)).parse(result.getString("birthDate"))));
            }
        } catch (SQLException | ParseException e) {
            return null;
            //TODO
        }
        return indList;
    }
}
