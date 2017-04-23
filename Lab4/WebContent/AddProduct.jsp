<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>AddProduct</title>
</head>
<body>
	<form action="AddProductServlet" method="post">
	<table>
		<tr>
			<th>id</th>
			<td><input type="text" name="ProductID"></td>
		</tr>
		<tr>
			<th>name</th>
			<td><input type="text" name="ProductName"></td>
		</tr>
		<tr>
			<th>price</th>
			<td><input type="text" name="ProductPrice"></td>
		</tr>
		<tr>
			<th>stock</th>
			<td><input type="text" name="ProductStock"></td>
		</tr>
		<tr>
			<th>comments</th>
			<td><input type="text" name="ProductComments"></td>
		</tr>
		<tr>
        	<td colspan="2" align="center">
        	<input type="submit" value="save"/>&nbsp;
        	<input type="reset" value="reset"/></td>
    	</tr>
	</table>
	</form>
</body>
</html>