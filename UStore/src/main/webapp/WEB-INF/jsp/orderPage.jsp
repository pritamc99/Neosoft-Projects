<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<style>
nav {
    float: left;
    max-width: 960px;
   	margin: 10px;
    /*  padding: 1em; */
}

article {
    margin-left: 170px;
    border-left: 1px solid gray;
    padding: 1em;
    overflow: hidden; 
}

.headings{
	color: red;
}
</style>

<body>
<nav >
<h5 class="headings">Order Details:</h5>
<table border="1">
	<tr>
		<!-- <th></th> -->
		<th>Item</th>
		<th>Quantity</th>
		<th>Price</th>
		<th>Subtotal</th>
	</tr>
	<c:forEach items="${order.orderItem}" var="item">
    <tr>
        <%-- <td><img src="static/img/<c:out value="${item.item.imageName}"/>"></td> --%>
        <td><c:out value="${item.item.itemName}"/></td>
        <td><c:out value="${item.quantity}"/></td>
        <td><c:out value="${item.item.price}"/></td> 
        <td><c:out value="${item.subTotal}"/></td> 
    </tr>
    </c:forEach>
</table>
</nav>
<article>
	<label class="headings">Customer Name:</label><c:out value="${order.user.firstName} ${order.user.lastName}"/></td>
	<h5 class="headings">Delivery Address:</h5>
	<table border="1">
			<c:forEach items="${user.addressList}" var="item">
    <tr>
    	<td><input type="radio" name="delAddress" value="${item.deliveryAddress}"></td>
        <td><c:out value="${item.deliveryAddress}"/></td>
    </tr>
    </c:forEach>
	</table>
	<a onclick=addNewAddress()>Add New Delivery Address</a><br>
	<textarea rows="5" cols="70" id="deliveryAddress"></textarea>
	<button id="saveAddress" onclick=saveAddress()>SAVE ADDRESS</button>
	<br><label class="headings">Total Amount:</label><c:out value="${order.orderAmount}"/>
	<br><button class="headings" onclick=placeOrder()>PLACE ORDER</button>
</article>


</body>
