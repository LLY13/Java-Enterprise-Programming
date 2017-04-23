<%@page import="java.sql.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>UpdateAttendant</title>
</head>
<body>
	<%
	Connection cn = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/");
	PreparedStatement pstmt = cn.prepareStatement("SELECT * FROM ATTENDANT WHERE ATT_ID = ?");
	pstmt.setString(1, request.getParameter("id"));
	ResultSet rs = pstmt.executeQuery();
	while (rs.next()) {
		String id = rs.getString("ATT_ID");
		String name = rs.getString("ATT_NAME");
		String address = rs.getString("ATT_ADDRESS");
		String mobile = rs.getString("ATT_MOBILE");
		String comments = rs.getString("ATT_COMMENTS");
		%>
	<form action="UpdateAttendantServlet" method="post">
	<table>
		<tr>
			<th>id</th>
			<td><input type="text" name="AttendantID" value="<%=id%>"></td>
		</tr>
		<tr>
			<th>name</th>
			<td><input type="text" name="AttendantName" value="<%=name%>"></td>
		</tr>
		<tr>
			<th>address</th>
			<td><input type="text" name="AttendantAddress" value="<%=address%>"></td>
		</tr>
		<tr>
			<th>mobile</th>
			<td><input type="text" name="AttendantMobile" value="<%=mobile%>"></td>
		</tr>
		<tr>
			<th>comments</th>
			<td><input type="text" name="AttendantComments" value="<%=comments%>"></td>
		</tr>
		<%} %>
		<tr>
        	<td colspan="4" align="center">
        	<input type="submit" value="save"/>&nbsp;
        	<input type="reset" value="reset"/></td>
    	</tr>
    	
	</table>
	</form>
</body>
</html>