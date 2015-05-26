package dk.cbjerregaard.miniproject.DAL;

import java.sql.*;

/**
 * Created by Christoffer on 12-05-2015.
 */
public enum DatabaseConnection {
    INSTANCE;
    public static final String DATABASE_FILE = "jdbc:sqlite:miniproject_db.db";

    public Connection getConnection() throws  SQLException{
        return DriverManager.getConnection(DATABASE_FILE);
    }

    public DatabaseConnection getInstance() {
        return INSTANCE;
    }
}