package com.example.aviacompany;
import java.sql.*;

public class DatabaseConnection {

    public Connection databaseLink;

    public Connection getConnection1(){
        String databaseName="mydb";
        String databaseUser="root";
        String databasePassword="";
        String url = "jdbc:mysql://localhost:3306/"+databaseName;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            databaseLink = DriverManager.getConnection(url,databaseUser,databasePassword);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return databaseLink;
    }


}
