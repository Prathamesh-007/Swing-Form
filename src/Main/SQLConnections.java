package Main;

import java.sql.*;
import java.util.Scanner;

public class SQLConnections {
    private static final Scanner cin = new Scanner(System.in);
    public static Connection con = null;
    public static Connection getCon() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Internship", "root", "<password>");
            System.out.println("Successfully connected to database!");

            return con;
        }
        catch(ClassNotFoundException e) {
            System.out.println("Exception: " + e);
        }

        catch(SQLException e) {
            System.out.println("Exception: " + e);
        }

        finally {
            return con;
        }
    }

    public static void closeCon(Connection C) {
        try {
            C.close();
        }
        catch(SQLException e) {
            System.out.println("Exception: " + e);
        }
    }
}
