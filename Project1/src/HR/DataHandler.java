package HR;
import java.sql.Connection;
import java.sql.SQLException;

import oracle.jdbc.pool.OracleDataSource;
import java.sql.Statement;
import java.sql.ResultSet;

import javax.servlet.http.HttpSession;

public class DataHandler {
    public DataHandler() {
        super();
    }
    String jdbcUrl = "jdbc:oracle:thin:@192.168.1.16:1521:cdb1";
    String userid = "hr";
    String password = "montrea1";
    Connection conn;
    Statement stmt;
    ResultSet rset;
    String query;
    String sqlString;
    
    public void getDBConnection() throws SQLException{
        OracleDataSource ds;
        ds = new OracleDataSource();
        ds.setURL(jdbcUrl);
        conn = ds.getConnection(userid,password);
    }
    public ResultSet getAllEmployees() throws SQLException{
        getDBConnection();
        stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
        query = "SELECT * FROM EMPLOYEES ORDER BY employee_id";
        System.out.println("\nExecuting query: " + query);
        rset = stmt.executeQuery(query);
        return rset;
    }
    public ResultSet getEmployeesByName(String name) throws SQLException{
        name = name.toUpperCase();
        getDBConnection();
        stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
        query = "SELECT * FROM EMPLOYEES WHERE UPPER(first_name) LIKE \'%"+name+"%\'"+
            " OR UPPER(last_name) LIKE \'%"+name+"%\' ORDER BY employee_id";
        System.out.println("\nExecuting query: "+query);
        rset = stmt.executeQuery(query);
        return rset;
    }
    public boolean authenticateUser(String jdbcUrl, String userid, String password,
    HttpSession session) throws SQLException {
    this.jdbcUrl = jdbcUrl;
    this.userid = userid;
    this.password = password;
    try {
    OracleDataSource ds;
    ds = new OracleDataSource();
    ds.setURL(jdbcUrl);
    conn = ds.getConnection(userid, password);
    return true;
    } catch ( SQLException ex ) {
    System.out.println("Invalid user credentials");
    session.setAttribute("loginerrormsg", "Invalid Login. Try Again...");
    this.jdbcUrl = null;
    this.userid = null;
    this.password = null;
    return false;
    }
    }    
}
