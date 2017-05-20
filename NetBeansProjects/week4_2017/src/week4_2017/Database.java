/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package week4_2017;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author yu
 */
public class Database {
    
    public static Connection conn;

    public static void openConnection() {
        if (conn == null) {
            try {
                conn = DriverManager.getConnection("jdbc:derby:"
                        + System.getProperty("user.dir")
                        + System.getProperty("file.separator")
                        + "memberDB;create=true");
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public ResultSet getResultSet(String sqlstatement) throws SQLException {
        openConnection();
        java.sql.Statement statement = conn.createStatement();
        ResultSet rs = statement.executeQuery(sqlstatement);
        return rs;
    }

    public void insertStatement(String insert_query) throws SQLException {
        java.sql.Statement stmt = null;
        openConnection();
        try {
            System.out.println("Database opened successfully");
            stmt = conn.createStatement();
            System.out.println("The query was: " + insert_query);
            stmt.executeUpdate(insert_query);
            stmt.close();
            conn.commit();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        stmt.close();
    }
    
    public static void createMemberTable() {
        PreparedStatement createMemberTable = null;
        PreparedStatement insertDemoData = null;
        ResultSet rs = null;
        openConnection();
        try {
            System.out.println("Checking Member table ");
            DatabaseMetaData dbmd = conn.getMetaData();
            rs = dbmd.getTables(null, "APP", "MEMBER", null);
            if (!rs.next()) {
                createMemberTable = conn.prepareStatement("CREATE TABLE APP.MEMBER (USERNAME VARCHAR(100), PASSWORD VARCHAR(100))");
                createMemberTable.execute();
                System.out.println("MEMBER table created");
                insertDemoData = conn.prepareStatement("INSERT INTO APP.MEMBER(USERNAME,PASSWORD) "
                        + "VALUES ('Poo','Poo')");
                insertDemoData.execute();
            } else {
                System.out.println("MEMBER table exists");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    
}
