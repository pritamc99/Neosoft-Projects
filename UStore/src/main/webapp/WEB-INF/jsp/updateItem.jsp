<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="updatePage" align="center">
	<form:form action="finalUpdateitem" method="post" commandName="item">
		<table>
			<tr>
				<td><form:label path="itemId">Item ID:</form:label></td>
				<td><form:input path="itemId" id="updateItemId" readonly="true"/></td>
			</tr>	
			<tr>
				<td><form:label path="itemName">Item Name:</form:label></td>
				<td><form:input path="itemName" id="updateItemName"/></td>
			</tr>
			<tr>
				<td><form:label path="companyName">Company Name:</form:label></td>
				<td><form:input path="companyName" id="updateCompanyName"/></td>
			</tr>
			<tr>
				<td><form:label path="price">Price:</form:label></td>
				<td><form:input path="price" id="updatePrice"/></td>
			</tr>
			<tr>
				<td><form:label path="imageName">Image Name:</form:label></td>
				<td><form:input path="imageName" id="updateImageName"/></td>
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
				<td><input type="submit" value="UPDATE"></td>
				<td></td>
			</tr>
		</table>
	</form:form>
</div class="updatePage" align="center">

<br><br>
<div>
<table border="1">
	<tr>
		<td><b>Item Id</b></td>
		<td><b>Item Name</b></td>
		<td><b>Company Name</b></td>
		<td><b>Price</b></td>
		<td></td>
	</tr>
	<c:forEach items="${itemList}" var="item1">
	<tr>
		<td><c:out value="${item1.itemId}"/></td>
		<td><c:out value="${item1.itemName}"/></td>
		<td><c:out value="${item1.companyName}"/></td>
		<td><c:out value="${item1.price}"/></td>
		<td><a onclick=updateItem(${item1.itemId})>Update</a></td>
	</tr>
	</c:forEach>
</table>
</div>