<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Search</title>
</head>
<body>
	<form action="SearchResult.jsp" method="post">
		<table>
			<tr>
				<th>input id : </th>
				<td><input type="text"  name="id"></td>
			</tr>
			<tr>
				<td colspan="4" align="center">
	        	<input type="submit" value="search"/>&nbsp;
	        	<input type="reset" value="reset"/></td>
			</tr>
		</table>
	</form>
</body>
</html>