<!DOCTYPE html>
<%@ page contentType="text/html;charset=windows-1252" import="java.sql.ResultSet"%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1252"/>
    </head>
    <body>
<jsp:useBean id="empsbean" class="HR.DataHandler" scope="session"/><%boolean userIsValid=false;
String host = request.getParameter("host");
String userid = request.getParameter("userid");
String password = request.getParameter("password");
String jdbcUrl = "jdbc:oracle:thin:@"+host+":1521:cdb1";
userIsValid = empsbean.authenticateUser(jdbcUrl,userid,password,session);%>

<%if(userIsValid){%>
<jsp:forward page="employees.jsp"/>
<%} else {%>
<jsp:forward page="login.jsp"/>
<%}%>
    </body>
</html>
