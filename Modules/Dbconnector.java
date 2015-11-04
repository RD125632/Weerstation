package Modules;

import java.sql.*;
import java.util.*;

/**
 * Database connect Object
 * 
 * @author Timothy Verdonck
 */
public class Dbconnector {
    private Connection myConn = null;

    /** Constructor
     * Initialize Database Connection
     */
    public Dbconnector(){
        this("84.24.41.72", "5329", "aws_data", "aws", "aws");
    }
   
    /** Constructor
     * 
     * @param host      - Hosting adress
     * @param port      - Hosting port
     * @param dbName    - Database name
     * @param username  - Database username
     * @param password  - Database user password
     */
    public Dbconnector(String host, String port, String dbName, String username, String password)
    {
        try
        {
            String url = "jdbc:mysql://" + host + ":" + port + "/"+ dbName + "?user="
                    + username
                    + "&password="
                    + password;
            Class.forName("com.mysql.jdbc.Driver").newInstance ();
            this.myConn = DriverManager.getConnection(url);
        }
        catch( SQLException ex)
        {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        catch(Exception ex)
        {
            System.out.println("Error : " + ex.getMessage());
        }
    }

    /**
     * @return myConn   - Gives database connection
     */
    public Connection getConnection(){
        return this.myConn;
    }

}
