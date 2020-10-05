<!DOCTYPE html>
<%@ page contentType="text/html;charset=windows-1252" import="java.sql.ResultSet"%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1252"/>
        <title>AnyCo Corporation: HR Application</title>
    </head>
    <body>
        <h2 style="text-align:center">AnyCo Corporation: HR Application</h2>
        <h3>Employee Data</h3>
        <jsp:useBean id="empsbean" class="HR.DataHandler" scope="session"/><form action="employees.jsp">
            Filter&nbsp;Employee&nbsp;By&nbsp;Name:
            <input name="query"/>
            <input type="submit" value="Filter"/>
        </form>
         <%ResultSet rset;
         String query = request.getParameter("query");
         if(query != null)
            rset = empsbean.getEmployeesByName(query);
           else
            rset=empsbean.getAllEmployees();
         %>
    </body>
</html>
<table>
    <tr>
        <th>
            First Name
        </th>
        <th>
            Last Name
        </th>
        <th>
            Email
        </th>
        <th>
            Job
        </th>
        <th>
            Phone Number
        </th>
        <th>
            Salary
        </th>
    </tr><%while(rset.next()){
out.println("<tr>");
out.println("<td>" +
    rset.getString("first_name") + "</td><td> " +
    rset.getString("last_name") + "</td><td> " +
    rset.getString("email") + "</td><td> " +
    rset.getString("job_id") + "</td><td> " +
    rset.getString("phone_number") + "</td><td> "+
    rset.getDouble("Salary")+"</td>");
out.println("</tr>");}%>
</table>