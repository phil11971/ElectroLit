package dao;

import java.sql.*;

public class ConnectionDB {

    private final static String URL = "jdbc:postgresql://localhost:5432/ElectronicLiterature";
    private final static String USERNAME = "postgres";
    private final static String PASS = "aser47wd";

    public static Connection getConnection() {
        Connection connection = null;
        try{
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(URL, USERNAME, PASS);
        }
        catch(SQLException | ClassNotFoundException e){
            e.printStackTrace();
        }
        return connection;
    }

}
