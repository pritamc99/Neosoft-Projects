<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<table id="cartList" border="1">
	<tr>
		<th></th>
		<th>Item</th>
		<th>Quantity</th>
		<th>Price</th>
		<th>Subtotal</th>
	</tr>
	<c:forEach items="${cartList}" var="item">
    <tr>
        <td><img src="static/img/<c:out value="${item.imageName}"/>"></td>
        <td><c:out value="${item.itemName}"/></td>
        <td><input type="number" class="quantityValue" id="${item.itemId}" value="1" width="1" height="2" min="1" max="10" onchange=subTotal("${item.itemId}","${item.price}")></td>
        <td><c:out value="${item.price}"/></td> 
        <td class="subtotal" id="subtotal${item.itemId}"><c:out value="${item.price}"/></td> 
    </tr>
    <tr><td><button onclick=removeItemFromCart(<c:out value="${item.itemId}"/>)>Remove From Cart</button></td></tr>
	</c:forEach>
	
	<tr>
		<td>Estimated Total:</td>
		<td id="estimatedTotal"></td>
		<td><button onclick=checkout()>CHECKOUT</button></td>
	</tr>
</table>

