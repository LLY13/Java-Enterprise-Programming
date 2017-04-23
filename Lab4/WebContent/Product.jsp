<%@page import="java.sql.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Product</title>
</head>
<body>
	<table>
	<tr>
		<th>ID</th>
		<th>Name</th>
		<th>Price</th>
		<th>Stock</th>
		<th>Comments</th>
		<th>option</th>
	</tr>
	<%
	Connection cn = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/");
	PreparedStatement pstmt = cn.prepareStatement("SELECT * FROM PRODUCT");
	ResultSet rs = pstmt.executeQuery();
	while (rs.next()) {
		String id = rs.getString("PRD_ID");
		String name = rs.getString("PRD_NAME");
		String price = rs.getString("PRD_PRICE");
		String stock = rs.getString("PRD_STOCK");
		String comments = rs.getString("PRD_COMMENTS");%>
		<tr>
			<td><%=id%></td>
			<td><%=name%></td>
			<td><%=price%></td>
			<td><%=stock%></td>
			<td><%=comments%></td>
			<td><a href="UpdateProduct.jsp?id=<%=id%>">Update</a>
			<a href="DeleteProductServlet?id=<%=id%>">Delete</a></td>
		</tr>
	<%} %>
		<tr>
			<td colspan="2"><a href="AddProduct.jsp">Add</a></td>
			<td colspan="2"><a href="Search.jsp">Search</a></td>
			<td colspan="2"><a href="Welcome.html">Back</a></td>
		</tr>
	</table>


</body>
</html> 