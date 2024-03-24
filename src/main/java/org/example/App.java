package org.example;

import org.example.visual.mFrame;

import java.sql.Connection;
import java.sql.SQLException;

import static org.example.db.DateBaseOperation.getDbConnection;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        new mFrame();
        Connection connection = getDbConnection();
        try {
            System.out.println(connection.createStatement().executeQuery("SELECT * FROM individuals;"));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
