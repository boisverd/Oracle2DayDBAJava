<!DOCTYPE html>
<%@ page contentType="text/html;charset=windows-1252"%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1252"/>
    </head>
    <body>
    <h2 style="text-align:center">AnyCo Corporation: HR Application</h2>
    <h3>Application Login</h3>
        <%
        String loginerrormsg = null;
        loginerrormsg = (String) session.getAttribute("loginerrormsg");
        if (loginerrormsg != null) {
        %>
       <h4>
            <%= loginerrormsg %>
        </h4>
       <%
       }
       %>
        <form action="login_action.jsp">
        <table>
   <tr>
      <th>
         User ID:
      </th>
      <td>
                  <input type="text" name="userid"/>
               </td>
   </tr><tr>
      <th>
         Password:
      </th>
      <td>
                  <input type="text" name="password"/>
               </td>
   </tr><tr>
      <th>
         Host:
      </th>
      <td>
                  <input type="text" name="host"/>
               </td>
   </tr>
</table>
         <input type="submit" value="Submit"/>
      </form>
    </body>
</html>
