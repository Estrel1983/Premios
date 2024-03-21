package org.example.db;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;

public class DateBaseOperation {
    private static Path curDir;
    private static Connection DB_CONNECTION;

    static {
        curDir = Paths.get(System.getProperty("user.dir"));
    }

    private static Connection createConnection() {
        Path dbPath = curDir.resolve("Premios.db");
        if (dbPath.toFile().exists()){
            try {
                DB_CONNECTION = DriverManager.getConnection("jdbc:sqlite:" + dbPath.toString());
            } catch (SQLException e){
                System.out.println(e.getMessage());
            }
        }
        else {
            try {
                DB_CONNECTION = DriverManager.getConnection("jdbc:sqlite:" + dbPath.toString());
                if (DB_CONNECTION != null) {
                    DatabaseMetaData meta = DB_CONNECTION.getMetaData();
                    System.out.println("A new database has been created.");
                }
                fillNewDB();
            } catch (SQLException e2) {
                System.out.println(e2.getMessage());
            }
        }
        return DB_CONNECTION;
    }

    private static void fillNewDB() {

        ClassLoader classLoader = DateBaseOperation.class.getClassLoader();
        InputStream inStr = classLoader.getResourceAsStream("CreateNewBDScrypt.txt");
        try (Statement stmt = DB_CONNECTION.createStatement()) {
            if (inStr != null) {
                try (BufferedReader br = new BufferedReader(new InputStreamReader(inStr))) {
                    String sqlCommand;
                    while ((sqlCommand = br.readLine()) != null){
                        stmt.execute(sqlCommand);
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public static Connection getDbConnection(){
        if (DB_CONNECTION != null)
            return DB_CONNECTION;
        return createConnection();
    }
}
