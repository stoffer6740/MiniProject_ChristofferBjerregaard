package dk.cbjerregaard.miniproject.DAL;

import java.sql.*;

/**
 * Created by Christoffer on 12-05-2015.
 */
public class DatabaseConnection {
    public static final String DATABASE_FILE = "jdbc:sqlite:miniproject_db.db";
    private static DatabaseConnection instance = null;

    public Connection getConnection() throws  SQLException{
        return DriverManager.getConnection(DATABASE_FILE);
    }

    public static DatabaseConnection getInstance() throws  SQLException {
        if(instance == null){
            instance = new DatabaseConnection();
        }
        return instance;
    }
}
