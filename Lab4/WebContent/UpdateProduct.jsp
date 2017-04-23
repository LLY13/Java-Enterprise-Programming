<%@page import="java.sql.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>UpdateProduct</title>
</head>
<body>
	<%
	Connection cn = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/");
	PreparedStatement pstmt = cn.prepareStatement("SELECT * FROM PRODUCT WHERE PRD_ID = ?");
	pstmt.setString(1, request.getParameter("id"));
	ResultSet rs = pstmt.executeQuery();
	while (rs.next()) {
		String id = rs.getString("PRD_ID");
		String name = rs.getString("PRD_NAME");
		String price = rs.getString("PRD_PRICE");
		String stock = rs.getString("PRD_STOCK");
		String comments = rs.getString("PRD_COMMENTS");
		%>
	<form action="UpdateProductServlet" method="post">
	<table>
		<tr>
			<th>id</th>
			<td><input type="text" name="ProductID" value="<%=id%>"></td>
		</tr>
		<tr>
			<th>name</th>
			<td><input type="text" name="ProductName" value="<%=name%>"></td>
		</tr>
		<tr>
			<th>price</th>
			<td><input type="text" name="ProductPrice" value="<%=price%>"></td>
		</tr>
		<tr>
			<th>stock</th>
			<td><input type="text" name="ProductStock" value="<%=stock%>"></td>
		</tr>
		<tr>
			<th>comments</th>
			<td><input type="text" name="ProductComments" value="<%=comments%>"></td>
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