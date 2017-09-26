<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<body>
	<br><br>
	
	<center>
	<form:form action="sineUp" method="post" commandName="register" >
	<h3>Customer Information</h3>
	<table>
		<tr>
			<td>First Name:</td>
			<td><form:input type="text" name="firstName" path="firstName"/></td>
			<td><form:errors path="firstName"></form:errors></td>
		</tr>
		<tr>
			<td>Last Name:</td>
			<td><form:input type="text" name="lastName" path="lastName"/></td>
			<td><form:errors path="lastName"></form:errors></td>
		</tr>
		<tr>
			<td>Address:</td>
			<td><form:input type="text" name="address" path="address"/></td>
			<td><form:errors path="address"></form:errors></td>
		</tr>
		<tr>
			<td>Email Id:</td>
			<td><form:input type="text" name="emailId" path="emailId"/></td>
			<td><form:errors path="emailId"></form:errors></td>
		</tr>
		<tr>
			<td>Mobile number:</td>
			<td><form:input type="text" name="mobileNumber" path="mobileNumber"/></td>
			<td><form:errors path="mobileNumber"></form:errors></td>
		</tr>
		<tr>
			<td>Username:</td>
			<td><form:input type="text" name="userName" path="userName"/></td>
			<td><form:errors path="userName"></form:errors></td>
		</tr>
		<tr>
			<td>Password</td>
			<td><form:input type="password" name="password" path="password"/></td>
			<td><form:errors path="password"></form:errors></td>
		</tr>
		<tr>
			<td><input type="submit" value="Register"></td>
		</tr>
	</table>
	</form:form></form>
	</center>
</body>
</html>