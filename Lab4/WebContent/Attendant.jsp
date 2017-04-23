<%@page import="java.sql.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Attendant</title>
</head>
<body>
	<table>
	<tr>
		<th>ID</th>
		<th>Name</th>
		<th>Address</th>
		<th>Mobile</th>
		<th>Comments</th>
		<th>option</th>
	</tr>
	<%
	Connection cn = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/");
	PreparedStatement pstmt = cn.prepareStatement("SELECT * FROM Attendant");
	ResultSet rs = pstmt.executeQuery();
	while (rs.next()) {
		String id = rs.getString("ATT_ID");
		String name = rs.getString("ATT_NAME");
		String address = rs.getString("ATT_ADDRESS");
		String mobile = rs.getString("ATT_MOBILE");
		String comments = rs.getString("ATT_COMMENTS");%>
		<tr>
			<td><%=id%></td>
			<td><%=name%></td>
			<td><%=address%></td>
			<td><%=mobile%></td>
			<td><%=comments%></td>
			<td><a href="UpdateAttendant.jsp?id=<%=id%>">Update</a>
			<a href="DeleteAttendantServlet?id=<%=id%>">Delete</a></td>
		</tr>
	<%} %>
		<tr>
			<td colspan="2"><a href="AddAttendant.jsp">Add</a></td>
			<td colspan="2"><a href="Welcome.html">Back</a></td>
		</tr>
	</table>


</body>
</html> 