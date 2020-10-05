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
    public Employee findEmployeeById(int id) throws SQLException{
        Employee selectedEmp = new Employee();
        getDBConnection();
        stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
        query = "SELECT * FROM EMPLOYEES WHERE employee_id = "+id;
        System.out.println("\nExecuting: "+query);
        rset = stmt.executeQuery(query);
        while (rset.next()) {
            selectedEmp.setEmployeeId(new Integer(rset.getInt("employee_id")));
            selectedEmp.setFirstName(rset.getString("first_name"));
            selectedEmp.setLastName(rset.getString("last_name"));
            selectedEmp.setEmail(rset.getString("email"));
            selectedEmp.setPhoneNumber(rset.getString("phone_number"));
            selectedEmp.setHireDate(rset.getDate("hire_date"));
            selectedEmp.setSalary(new Double(rset.getDouble("salary")));
            selectedEmp.setJobId(rset.getString("job_id"));
        }
        return selectedEmp;
    }
    public String updateEmployee(int employee_id, String first_name,String last_name, String email, String phone_number,
    String salary, String job_id) throws SQLException{
        Employee oldEmployee = findEmployeeById(employee_id);
        getDBConnection();
        stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
        StringBuffer columns = new StringBuffer( 255 );
        if ( first_name != null && !first_name.equals(oldEmployee.getFirstName() ) )
        {
            columns.append( "first_name = '" + first_name + "'" );
        }
        if ( last_name != null && !last_name.equals(oldEmployee.getLastName() ) ) {
        if ( columns.length() > 0 ) {
            columns.append( ", " );
        }
            columns.append( "last_name = '" + last_name + "'" );
        }
        if ( email != null && !email.equals(oldEmployee.getEmail() ) ) {
            if ( columns.length() > 0 ) {
                columns.append( ", " );
            }
                columns.append( "email = '" + email + "'" );
        }
        if ( phone_number != null && !phone_number.equals(oldEmployee.getPhoneNumber() ) ) {
            if ( columns.length() > 0 ) {
                columns.append( ", " );
            }
                columns.append( "phone_number = '" + phone_number + "'" );
        }
        if ( salary != null && !salary.equals( oldEmployee.getSalary().toString() ) ) {
            if ( columns.length() > 0 ) {
                columns.append( ", " );
        }
            columns.append( "salary = '" + salary + "'" );
        }
        if ( job_id != null && !job_id.equals( oldEmployee.getJobId() ) ) {
            if ( columns.length() > 0 ) {
                columns.append( ", " );
        }
            columns.append( "job_id = '" + job_id + "'" );
        }
        if ( columns.length() > 0 )
        {
            sqlString = "UPDATE Employees SET " + columns.toString() + " WHERE employee_id = " + employee_id;
            System.out.println("\nExecuting: " + sqlString);
            stmt.execute(sqlString);
        }
        else
        {
            System.out.println( "Nothing to do to update Employee Id: " + employee_id);
        }
        return "success";
    }
}
