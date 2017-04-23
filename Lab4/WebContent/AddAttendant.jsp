<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>AddAttendant</title>
</head>
<body>
	<form action="AddAttendantServlet" method="post">
	<table>
		<tr>
			<th>id</th>
			<td><input type="text" name="ID"></td>
		</tr>
		<tr>
			<th>name</th>
			<td><input type="text" name="Name"></td>
		</tr>
		<tr>
			<th>address</th>
			<td><input type="text" name="Address"></td>
		</tr>
		<tr>
			<th>mobile</th>
			<td><input type="text" name="Mobile"></td>
		</tr>
		<tr>
			<th>comments</th>
			<td><input type="text" name="Comments"></td>
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