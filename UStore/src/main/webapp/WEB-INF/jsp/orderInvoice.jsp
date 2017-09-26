<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div>
<center><h1>Order Invoice</h1></center>
<hr size="10">
<table>
	<tr>
		<td>Customer Name:</td>
		<td><c:out value="${order.user.firstName}"/>&nbsp
			<c:out value="${order.user.lastName}"/>
		</td>
	</tr>
	<tr>
		<td>Contact Number:</td>
		<td><c:out value="${order.user.mobileNumber}"/></td>
	</tr>
	<tr>
		<td>Email:</td>
		<td><c:out value="${order.user.emailId}"/></td>
	</tr>
	<tr>
		<td>Delivery Address:</td>
		<td><c:out value="${order.deliveryAddress.deliveryAddress}"/></td>
	</tr>
	<tr>
		<td>Order Date:</td>
		<td><c:out value="${order.orderDate}"/></td>
	</tr>
	<tr>
		<td>Exp.Delivery Date:</td>
		<td><c:out value="${order.expectedDeliveryDate}"/></td>
	</tr>
</table>
<table border="1" style="width:100%">
	<tr>
		<th>Sr</th>
		<th>Item</th>
		<th>Price</th>
		<th>Qty</th>
		<th>Amount</th>
	</tr>
	<%! int i=1; %>
	<c:forEach items="${order.orderItem}" var="item" >
    <tr>
    	<td align="center"><%= i++ %></td>
        <td><c:out value="${item.item.itemName}"/></td>
        <td align="center"><c:out value="${item.item.price}"/></td>
        <td align="center"><c:out value="${item.quantity}"/></td> 
        <td align="center"><c:out value="${item.subTotal}"/></td> 
    </tr>
    </c:forEach>
  <% i=1; %>
  	<tr>
  		<td></td>
  		<td></td>
  		<td></td>
  		<td align="center" style="color: red;font-weight: bold;">Total:</td>
  		<td align="center" style="color: red;font-weight: bold;"><c:out value="${order.orderAmount}"/></td>
  	</tr>
</table>

<a href="downloadPdf" target="_blank">Download PDF</a>

</div>