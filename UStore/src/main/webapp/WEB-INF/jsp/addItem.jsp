<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="content" align="center">
	<form:form action="addItem" method="post" commandName="item">
		<table>
			<tr>
				<td><form:label path="itemName">Item Name:</form:label></td>
				<td><form:input path="itemName"/></td>
			</tr>
			<tr>
				<td><form:label path="companyName">Company Name:</form:label></td>
				<td><form:input path="companyName"/></td>
			</tr>
			<tr>
				<td><form:label path="price">Price:</form:label></td>
				<td><form:input path="price"/></td>
			</tr>
			<tr>
				<td><form:label path="imageName">Image Name:</form:label></td>
				<td><form:input path="imageName"/></td>
			</tr>
			<tr>
				<td><form:label path="categoryType">Select Category Type:</form:label></td>
				<td><form:select path="categoryType" id="selectedCategory">
					<form:option value="0" label="--- Select ---"/>
		 				<c:forEach var="categ" items="${categoryList}">
		 					<form:option value="${categ.catId}" class="selectedCategoryClass">${categ.categoryName}</form:option>
		 				</c:forEach>	
				</form:select></td>
			</tr>
			<tr>
				<td><form:label path="productType">Select Product Type:</form:label></td>
				<td><form:select path="productType" id="productList1">
				</form:select></td>
			</tr>
			<tr>
				<td><form:label path="quantity">Quantity:</form:label></td>
				<td><form:input path="quantity"/></td>
			</tr>
			<tr>
				<td><input type="submit" value="SUBMIT"></td>
				<td></td>
			</tr>
		</table>
	</form:form>
</div>