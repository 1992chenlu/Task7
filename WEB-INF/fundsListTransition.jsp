<!-- Team 1 -->
<!-- 01/15/2015 -->
<!-- Task 7-->

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<jsp:include page="template-top.jsp" />

<p style="font-size: medium">Please enter the fund information. all
	fields are required.</p>
<p contenteditable="false">
<h2>List of funds</h2>
<br>
<br>
<c:forEach var="error" items="${errors}">
	<h5 style="color: red; text-align: center">${error}</h5>
</c:forEach>
<form method="post" action="transition.do">
	<table style="border: thin; border-color: #680511;">

		<tr style="border: thin; border-color: #680511;">
			<td style="padding: 10px;">
				<h3></h3>
			</td>
			<td>Serial Number</td>
			<td style="padding: 10px;"><h3>Fund Name</h3></td>
			<td style="padding: 10px;"><h3>Fund Symbol</h3></td>
			<td style="padding: 10px;"><h3>New Closing price</h3></td>

		</tr>
		<c:forEach var="fund" items="${fundsList}" varStatus="status">


			<tr style="border: thin; border-color: #680511;">
				<td style="padding: 10px;"><input type="hidden" name="fund_id"
					value="${fund.fund_id }" readonly></td>
				<td>${status.index}</td>
				<td style="padding: 10px;">${fund.name }</td>
				<td style="padding: 10px;">${fund.symbol}</td>
				<td style="padding: 10px;"><input type="text" name="price"
					value="" required /></td>
			</tr>
		</c:forEach>
	</table>
	<input type="date" name="date" value="" required /> <input
		type="submit" name="Set Closing" class="btn btn-danger"
		value="Set Closing" />
</form>


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