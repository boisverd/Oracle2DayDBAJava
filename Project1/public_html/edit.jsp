<!DOCTYPE html>
<%@ page contentType="text/html;charset=windows-1252"%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1252"/>
        <title>edit</title>
    </head>
    <body>
    <h2 style="text-align:center">AnyCo Corporation: HR Application</h2>
    <h3>Edit Employee Record</h3>
    <jsp:useBean id="empsbean" class="HR.DataHandler" scope="session"/>
    <jsp:useBean id="employee" class="HR.Employee"/>
    <%Integer employee_id = new Integer(request.getParameter("emp_id"));
        employee = empsbean.findEmployeeById(employee_id.intValue());%>
        <form action="update_action.jsp">
        <table>
            <tr>
                <th>
                    First Name
                </th>
                <td><input type="text" name="first_name" value="<%= employee.getFirstName()%>"/>
                <input type="hidden" name="employee_id" value="<%= employee.getEmployeeId()%>"/>
                    </td>
            </tr><tr>
                <th>
                    Last Name
                </th>
                <td>
                        <input type="text" name="last_name" value="<%= employee.getLastName()%>"/>
                    </td>
            </tr><tr>
                <th>
                    Email
                </th>
                <td>
                        <input type="text" name="email" value="<%= employee.getEmail()%>"/>
                    </td>
            </tr><tr>
                <th>
                    Phone
                </th>
                <td>
                        <input type="text" name="Phone_Number" value="<%= employee.getPhoneNumber()%>"/>
                    </td>
            </tr><tr>
                <th>
                    Job
                </th>
                <td>
                        <input type="text" name="Job" value="<%= employee.getJobId()%>"/>
                    </td>
            </tr><tr>
                <th>
                    Monthly Salary
                </th>
                <td>
                        <input type="text" name="Salary" value="<%= employee.getSalary()%>"/>
                    </td>
            </tr>
        </table>
            <input type="submit" name="Submit" value="Update"/>
        </form>
    </body>
</html>