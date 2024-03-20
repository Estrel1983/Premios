package org.example.db;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;

public class DateBaseOperation {
    private static Path curDir;

    static {
        curDir = Paths.get(System.getProperty("user.dir"));
    }
    public static void createDatebase() {
        Path dbPath = curDir.resolve("Premios.db");
        if (!dbPath.toFile().exists()){
            try (Connection conn = DriverManager.getConnection("jdbc:sqlite:" + dbPath.toString())) {
                if (conn != null) {
                    DatabaseMetaData meta = conn.getMetaData();
                    System.out.println("A new database has been created.");
                }
                Statement stmt = conn.createStatement();
                conn.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
