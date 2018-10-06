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
//Create the connection for the user and the other person 
    public static void openConnection() {
        if (conn == null) {
            try {
                conn = DriverManager.getConnection("jdbc:derby:"
                        + System.getProperty("user.dir")
                        + System.getProperty("file.separator")
                        + "Suber;create=true");
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public static void createConsultationTable() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
    //Create the member table 
    public static void createMemberTable() {
        PreparedStatement createMemberTable = null;
        PreparedStatement insertDemoData = null;
        ResultSet rs = null;
        openConnection();
        //try catch exception
        try {
            System.out.println("Checking Member table ");
            DatabaseMetaData dbmd = conn.getMetaData();
            rs = dbmd.getTables(null, "APP", "MEMBER", null);
            if (!rs.next()) {
                createMemberTable = conn.prepareStatement("CREATE TABLE APP.MEMBER (EMAIL_ADDRESS VARCHAR (50) primary key, PASSWORD VARCHAR(100), MEMBER_FNAME VARCHAR (50), MEMBER_LNAME VARCHAR (50), STATE VARCHAR (50), POSTCODE VARCHAR(50), SUBURB VARCHAR(50), ADDRESS VARCHAR (50), MEM_ID INT not null unique GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1))");
                createMemberTable.execute();
                System.out.println("MEMBER table created");
                insertDemoData = conn.prepareStatement("INSERT INTO APP.MEMBER(EMAIL_ADDRESS, PASSWORD, MEMBER_FNAME, MEMBER_LNAME, STATE, POSTCODE, SUBURB, ADDRESS)"
                    + "VALUES ('martinzhang1997@outlook.com', '123', 'Leo', 'Lin', 'NSW', '2033', 'Kensington', '177 anzc parade'),"
                    + "('2@gmail.com', '123', 'John', 'Snow', 'NSW', '2033', 'Kensington', '179 anzc parade'),"
                    + "('3@gmail.com', '123', 'Mem3', 'Mem3', 'QSL', '2039', 'Kensington', '175 anzc parade'),"
                    + "('4@gmail.com', '123', 'Mem4', 'Mem4', 'NSW', '2034', 'Maroubra', '123 darling rd'),"
                    + "('5@gmail.com', '123', 'Mem5', 'Mem5', 'JBL', '123', 'Random', '112 Moo Ave') ");
                            insertDemoData.execute();

            //if they have already created the table, then they will need to delete it
            } else {
                System.out.println("MEMBER table exists");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
      public static void createAdministratorTable() {
        PreparedStatement createAdministratorTable = null;
        PreparedStatement insertDemoData = null;
        ResultSet rs = null;
        openConnection();
        //try catch exception
        try {
            System.out.println("Checking Administrator table ");
            DatabaseMetaData dbmd = conn.getMetaData();
            rs = dbmd.getTables(null, "APP", "ADMIN", null);
            if (!rs.next()) {
                createAdministratorTable = conn.prepareStatement("CREATE TABLE APP.ADMIN (EMAIL_ADDRESS VARCHAR (50), PASSWORD VARCHAR(100), ADMIN_FNAME VARCHAR (50), ADMIN_LNAME VARCHAR (50), ADMIN_ID INT not null primary key GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1))");
                createAdministratorTable.execute();
                System.out.println("ADMINISTRATOR table created");
                insertDemoData = conn.prepareStatement("INSERT INTO APP.ADMIN(EMAIL_ADDRESS, PASSWORD, ADMIN_FNAME, ADMIN_LNAME)"
                    + "Values ('123@gmail.com', '123', 'Leo', 'Lin')");
                insertDemoData.execute();

            //if they have already created the table, then they will need to delete it
            } else {
                System.out.println("ADMIN table exists");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void createAgreementStatusTable() {
        PreparedStatement createAgreementStatusTable = null;
        ResultSet rs = null;
        openConnection();
        try {
            System.out.println("Checking AgreementStatus table ");
            DatabaseMetaData dbmd = conn.getMetaData();
            PreparedStatement insertDemoData = null;
            rs = dbmd.getTables(null, "APP", "AgreementStatus", null);
            if (!rs.next()) {
                createAgreementStatusTable = conn.prepareStatement("CREATE TABLE AGREEMENTSTATUS (A_STATUS_ID int not null Default 1 PRIMARY KEY ,  AGREEMENT_STATUS varchar(50))");
                createAgreementStatusTable.execute();
                System.out.println("AgreementStatus table created");


            } else {
                System.out.println("AgreementStatus table exists");
            }
        } catch (Exception e) {
            e.printStackTrace();   
       

 
        }}

       
       
        public static void createDriverpostTable() {
        PreparedStatement createDriverpostTable = null;
        PreparedStatement insertDemoData = null;
        ResultSet rs = null;
        openConnection();
        try {
            System.out.println("Checking Driverpost table ");
            DatabaseMetaData dbmd = conn.getMetaData();
            rs = dbmd.getTables(null, "APP", "DRIVERPOST", null);
            if (!rs.next()) {
                createDriverpostTable = conn.prepareStatement("CREATE TABLE DRIVEPOST(POST_ID INT not null PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), DRIVER_EMAIL_ADDRESS varchar (50), START_POSTCODE varchar(40), START_Street_Address varchar (50), FINISH_POSTCODE varchar (40), FINISH_Street_Address varchar (50), DRIVE_DATE date, DRIVE_TIME_HR varchar(2), DRIVE_TIME_MIN varchar(2), ARRIVAL_TIME_HR varchar(2), ARRIVAL_TIME_MIN varchar(2), ADDITIONAL_DETAIL varchar (100), AVAILABLE_SEATS varchar(2), FOREIGN KEY (DRIVER_EMAIL_ADDRESS) References APP.MEMBER(EMAIL_ADDRESS))");
                createDriverpostTable.execute();
                System.out.println("Driverpost table created");
                            insertDemoData = conn.prepareStatement("INSERT INTO APP.DRIVEPOST( DRIVER_EMAIL_ADDRESS, START_POSTCODE, START_Street_Address, FINISH_POSTCODE, FINISH_Street_Address, DRIVE_DATE, DRIVE_TIME_HR, DRIVE_TIME_MIN, ARRIVAL_TIME_HR, ARRIVAL_TIME_MIN, ADDITIONAL_DETAIL , AVAILABLE_SEATS )"
                    + "Values ('1@gmail.com', '123', '123 Street', '2033', '456 Street', '12/12/2017', '12', '25', '14', '25', null, '3'),"
                    + "('2@gmail.com', '123', '112 Street', '2033', '456 Street', '11/11/2017', '21', '40', '22', '30', 'Regular routine', '4'),"
                    + "('3@gmail.com', '1213', '123 Street', '2033', '456 Street', '10/10/2017', '20', '40', '21', '20', 'Regular routine', '5'),"
                    + "('4@gmail.com', '4332', '324 Street', '2033', '456 Street', '11/11/2017', '21', '40', '22', '30', null, '4'),"
                    + "('5@gmail.com', '256', '123 Street', '2033', '456 Street', '11/11/2017', '21', '40', '22', '30', 'Regular routine', '4') ");
                            insertDemoData.execute();    
        } else {
                System.out.println("Driverpost table exists");
            }
        } catch (Exception e) {
            e.printStackTrace();   
       

 
        }}
        
        public static void createAgreementTable() {
        PreparedStatement createAgreementTable = null;
        PreparedStatement insertDemoData = null;
        ResultSet rs = null;
        openConnection();
        try {
            System.out.println("Checking AGREEMENT table ");
            DatabaseMetaData dbmd = conn.getMetaData();
            rs = dbmd.getTables(null, "APP", "DRIVERPOST", null);
            if (!rs.next()) {
                createAgreementTable = conn.prepareStatement("CREATE TABLE AGREEMENT (AGREEMENT_ID int PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),  POST_ID INT not null, price varchar (50))");
                createAgreementTable.execute();
                System.out.println("Driverpost table created");

            } else {
                System.out.println("AGREEMENT table exists");
            }
        } catch (Exception e) {
            e.printStackTrace();   
       

 
        }}            
        public static void createMembershippaymentTable() {
        PreparedStatement createMembershippaymentTable = null;
        PreparedStatement insertDemoData = null;
        ResultSet rs = null;
        openConnection();
        try {
            System.out.println("Checking MEMBERSHIP_PAYMENT table ");
            DatabaseMetaData dbmd = conn.getMetaData();
            rs = dbmd.getTables(null, "APP", "MEMBERSHIP_PAYMENT", null);
            if (!rs.next()) {
                createMembershippaymentTable = conn.prepareStatement("CREATE TABLE MEMBERSHIP_PAYMENT( MEMBERSHIP_PAYMENT_ID varchar (50) not null,"
                        + "MEMBERSHIP_PAYMENT_DATE date,"
                        + "PAYMENT_AMOUNT varchar (50),"
                        + "MEMBERSHIP_DURATION varchar (50),"
                        + "PRIMARY KEY (MEMBERSHIP_PAYMENT_ID)"
                        + "MEM_ID varchar(9) not null,"
                        + "Foreign Key (MEM_ID) References MEMBER (MEM_ID);");
                createMembershippaymentTable.execute();
                System.out.println("MembershipPayment table created");
                insertDemoData = conn.prepareStatement("INSERT INTO APP.MEMBERSHIP_PAYMENT(MEMBERSHIP_PAYMENT_ID,"
                        + "MEMBERSHIP_PAYMENT_DATE,"
                        + "PAYMENT_AMOUNT,"
                        + "MEMBERSHIP_DURATION, PAYMENTTYPE, ACCOUNTEXPIRY, ACCOUNTOWNERNAME, PAMENTMEDIA) "
                        + "VALUES ('1','D','POO','S','Paa', '1', '50', '16/05/2017', '14/05/2017','2033','2035','18/05/2017', '12:40')");
                insertDemoData.execute();
            } else {
                System.out.println("MembershippaymentTable table exists");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }}
        public static void createDrivepaymentTable() {
        PreparedStatement createDrivepaymentTable = null;
        PreparedStatement insertDemoData = null;
        ResultSet rs = null;
        openConnection();
        try {
            System.out.println("Checking DRIVE_PAYMENT table ");
            DatabaseMetaData dbmd = conn.getMetaData();
            rs = dbmd.getTables(null, "APP", "DRIVE_PAYMENT", null);
            if (!rs.next()) {
                createDrivepaymentTable = conn.prepareStatement("CREATE TABLE DRIVE_PAYMENT(DRIVE_PAYMENT_ID varchar (50) not null,"
                        + "DRIVE_PAYMENT_DATE date,"
                        + "PAYMENT_AMOUNT varchar (50),"
                        + "DRIVE_RATE_ID varchar (50),"
                        + "AGREEMENT_ID char(9) not null,"
                        + "MEM_ID varchar(9) not null,"
                        + "PRIMARY KEY (DRIVE_PAYMENT_ID))"
                        + "Constraint fk_RATEPAYMENT Foreign Key (DRIVE_RATE_ID) References DRIVE_RATE (DRIVE_RATE_ID)" 
                        + "Constraint fk_MEMBERDRIVEPAYMENT Foreign Key (MEM_ID) References MEMBER (MEM_ID);");
                createDrivepaymentTable.execute();
                System.out.println("DrivePayment table created");
                insertDemoData = conn.prepareStatement("INSERT INTO APP.AGREEMENT(PAY_AMOUNT,PAY_DATE,PAYMENT_ID,PAYMENTACCOUNT, PAYMENTTYPE, ACCOUNTEXPIRY, ACCOUNTOWNERNAME, PAMENTMEDIA) "
                        + "VALUES ('1','D','POO','S','Paa', '1', '50', '16/05/2017', '14/05/2017','2033','2035','18/05/2017', '12:40')");
                insertDemoData.execute();
            } else {
                    System.out.println("DrivepaymentTable table exists");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }}
        public static void createCorpMemTable() {
        PreparedStatement createCorpMemTable = null;
        PreparedStatement insertDemoData = null;
        ResultSet rs = null;
        openConnection();
        //try catch exception
        try {
            System.out.println("Checking Corporate Member table ");
            DatabaseMetaData dbmd = conn.getMetaData();
            rs = dbmd.getTables(null, "APP", "CORPMEM", null);
            if (!rs.next()) {
                createCorpMemTable = conn.prepareStatement("CREATE TABLE APP.CORPMEM "
                        + "COMP_NAME VARCHAR (50), "
                        + "COMP_ADD VARCHAR (100), "
                        + "COMP_PHONE VARCHAR (50), "
                        + "COMPMEM_ID INT not null primary key GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1))");
                createCorpMemTable.execute();
                System.out.println("CORPORATE MEMBER table created");
                insertDemoData = conn.prepareStatement("INSERT INTO APP.CORPMEM(COMP_NAME, COMP_ADD, COMP_PHONE)"
                + "VALUES ('Google','Sydney','9863 8699')");

            //if they have already created the table, then they will need to delete it
            } else {
                System.out.println("CORPMEMBER table exists");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
               
 
   
    
    //if they do not have an @ account then registration/ login is not accepted
    //Need to create a main function which creates all tables when you run it

    

}
    

