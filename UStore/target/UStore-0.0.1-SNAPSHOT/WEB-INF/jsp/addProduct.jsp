<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="content" align="center">
<form:form action="addProduct" method="post" commandName="product"> 
	<form:label path="productName">Add Product Name:</form:label>
	<form:input path="productName" name="productName"/>
	<form:errors path="productName"/><br>
	<form:label path="category.catId">Select Category:</form:label>
	<form:select path="category.catId" cssClass="categoryOptionList" id="categoryOptionList">
		 	
		 	<form:option value="0" label="--- Select ---"/>
		 	<c:forEach var="categ" items="${categoryList}">
		 		<form:option value="${categ.catId}">${categ.categoryName}</form:option>
		 	</c:forEach>	
	</form:select>
	<form:errors path="category.catId"/><br>
	<input type="submit" value="ADD"/>
</form:form>
</div>