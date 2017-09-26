<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<form:form id="loginForm" method="post" action="signIn" commandName="login">
	${error}<br>
	<form:label path="userName">Username</form:label>
	<form:input path="userName" name="userName"/>
	<form:errors path="userName"/><br><br>	
	<form:label path="password">Password</form:label>
	<form:input path="password" name="password"/>
	<form:errors path="password"/><br><br>	
	<input type="submit" value="LOGIN"/>
</form:form>

<div id="status">
<button onclick="Login()">Sign In Using Facebook</button>
<button onclick="Glogin()">Sign In Using Google</button>

</div>
<div id="profile"></div>
