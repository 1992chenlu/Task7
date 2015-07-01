<!-- Team 1 -->
<!-- 01/15/2015 -->
<!-- Task 7-->

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<jsp:include page="template-top.jsp" />

<p style="font-size: medium"></p>

<p>
<table>
	<tr>
		<td>
			<h3>List of Customers</h3>
		</td>
		<td></td>
		<c:forEach var="error" items="${errors}">
			<h5 style="color: red; text-align: center">${error}</h5>
		</c:forEach>
	</tr>
	<c:forEach var="customer" items="${customerList}">
		<tr>
			<td><a href="viewAccount.do?id=${customer.customer_id}">
					${customer.lastname}, ${customer.firstname}</a></td>
			<td><input type="hidden" name="customer_id"
				value="${customer.customer_id}" /></td>
		</tr>

	</c:forEach>
</table>


</p>
<hr />

<br>
<br>
<br>
<br>
<br>
<br>
<br>
<jsp:include page="template-bottom.jsp" />