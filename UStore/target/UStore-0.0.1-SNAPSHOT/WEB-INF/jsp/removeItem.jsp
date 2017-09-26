<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="content" align="center">
<table border="1">
	<tr>
		<td><b>Item Id</b></td>
		<td><b>Item Name</b></td>
		<td><b>Company Name</b></td>
		<td><b>Price</b></td>
		<td></td>
	</tr>
	<c:forEach items="${itemList}" var="item">
	<tr>
		<td><c:out value="${item.itemId}"/></td>
		<td><c:out value="${item.itemName}"/></td>
		<td><c:out value="${item.companyName}"/></td>
		<td><c:out value="${item.price}"/></td>
		<td><a onclick=removeItem(${item.itemId}) class="removeItem">Remove</a></td>
	</tr>
	</c:forEach>
</table>
</div>