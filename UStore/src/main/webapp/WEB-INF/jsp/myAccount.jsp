 <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<h5><b>MY ACCOUNT</b></h5>
<b>Orders</b><br>
<a onclick=showMyOrders()>My Orders</a>

<div id="user" name="${user.registerId}"></div>
<div align="left">
<table id="myorder" border="1">

<tr id="orderlist"><td></td></tr>
</table>
</div>

<div align="left">
<table border="1">
<tr id="orderitemlist"><td></td></tr>
</table>
</div>