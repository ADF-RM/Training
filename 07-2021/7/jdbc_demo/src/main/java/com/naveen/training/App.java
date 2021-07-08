package com.naveen.training;
import java.io.Closeable;
import java.io.IOException;
import java.sql.*;
import java.util.*;

public class App implements Closeable {

    static long ACCOUNT_NO = 6100580029003700L;
    static int TRANSACTION_FEE = 100;
    static String ACCOUNT_TYPE = "SAVINGS";
    static String HOLDER_NAME = "NITHISH C";

    static String INSERT_QUERY = "INSERT INTO BANK_ACCOUNT(ACCOUNT_NO,TRANSACTION_FEE,ACCOUNT_TYPE,HOLDER_NAME) VALUES(?,?,?,?)";
    static String SELECT_QUERY = "SELECT * FROM BANK_ACCOUNT";

    static String LAST_TRANSACTION_QUERY = "SELECT * FROM BANK_ACCOUNT ORDER BY REQUEST_TIME DESC LIMIT 1";

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo_database", "root",
                    "ADFRM551$");
            conn.setAutoCommit(false);
            PreparedStatement statement = conn.prepareStatement(INSERT_QUERY);

            System.out.println("Enter your A/c No.");
            ACCOUNT_NO = sc.nextLong();
            System.out.println("Enter your A/c Type");
            ACCOUNT_TYPE = sc.next();
            System.out.println("Enter your A/c Holder Name.");
            HOLDER_NAME = sc.next();

            statement.setLong(1, ACCOUNT_NO);
            statement.setInt(2, TRANSACTION_FEE);
            statement.setString(3, ACCOUNT_TYPE);
            statement.setString(4, HOLDER_NAME);

            int rowsAffected = statement.executeUpdate();

            PreparedStatement statement1 = conn.prepareStatement(SELECT_QUERY);
            ResultSet rs = statement1.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();

            metaData(rsmd, rowsAffected);

            getAllTransactions(conn, rsmd);

            getMyTransactions(conn, rsmd);

            getConfirmation(conn);

            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally{
            sc.close();
        }
    }

    private static void getConfirmation( Connection conn) throws SQLException {
        System.out.println("\n 1 : Commit Transaction\n 2: Rollback Transaction\n");

        Scanner sc = new Scanner(System.in);
        int choice = sc.nextInt();

        if (choice == 1) {
            conn.commit();
        } else if (choice == 2) {
            conn.rollback();
        } else {
            System.out.println("Invalid Choice.. Please Try again");
        }
        sc.close();
    }

    private static void getMyTransactions(Connection conn, ResultSetMetaData rsmd) throws SQLException {
        System.out.println("\nYour Transaction....\n");

        Statement statement3 = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet result2 = statement3.executeQuery(LAST_TRANSACTION_QUERY);

        while (result2.next()) {
            for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                System.out.println(rsmd.getColumnName(i) + " : " + result2.getString(i));
            }
            System.out.println("------------------------");
        }

    }

    private static void getAllTransactions(Connection conn, ResultSetMetaData rsmd) throws SQLException {
        Statement statement2 = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet result = statement2.executeQuery(SELECT_QUERY);

        System.out.println("\nAll Transactions ..\n");
        while (result.next()) {
            for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                System.out.println(rsmd.getColumnName(i) + " : " + result.getString(i));
            }
            System.out.println("------------------------");
        }
    }

    private static void metaData(ResultSetMetaData rsmd, int rowsAffected) throws SQLException {
        System.out.println(rowsAffected + " " + "Rows Affected");
        System.out.println("\n---Meta data of table BANK_ACCOUNT---\n");
        System.out.println("Total columns: " + rsmd.getColumnCount());
        System.out.println("Column Name of 1st Column: " + rsmd.getColumnName(1));
        System.out.println("Column Type of 1st Column: " + rsmd.getColumnTypeName(1));
    }

    @Override
    public void close() throws IOException {
        System.out.println("Closing the connection");
    }

}
