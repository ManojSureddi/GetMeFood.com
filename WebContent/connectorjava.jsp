<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    import="java.sql.Connection"
    import="java.sql.DriverManager"
    import="java.sql.SQLException"
    %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%
String serverAddr = "172.24.137.30";
String port = "1521";
String sid = "orcl";
String url = "jdbc:mysql://54.68.228.237:3306/gmfdb?noAccessToProcedureBodies=true";
String username = "gmf";
String pass = "gmf";
Connection con = null;
Class.forName("com.mysql.jdbc.Driver");
con = DriverManager.getConnection(url,username,pass);
out.print(con);

%>
</body>
</html>