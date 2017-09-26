<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<div class="content" align="center">
	<form:form action="addCategory" method="post" commandName="category">
		<form:label path="categoryName">Category Name:</form:label>
		<form:input path="categoryName" name="categoryName"/>
		<form:errors path="categoryName"/><br>
		<input type="submit" value="ADD">
	</form:form>
</div>