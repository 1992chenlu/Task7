<!-- Team 1 -->
<!-- 01/15/2015 -->
<!-- Task 7-->

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<jsp:include page="template-top.jsp" />

<p style="font-size: medium"></p>
<div class="matter">
	<div class="container-fluid">
		<div class="page-title">
			<br>
			<h2>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbspBuy
				${fund.name}</h2>
		</div>
		<hr />
		<div class="span8">
			<p align="center">Please enter the fund information. All fields
				are required.</p>
			<p align="center">Note: Your Current Balance is:
				${customer.cash/100} and Your Available Balance is:
				${customer.available_cash/100}</p>
			<c:forEach var="error" items="${errors}">
				<h5 style="color: red; text-align: center">${error}</h5>
			</c:forEach>
			<form method="post" action="buy.do">
				<div class="well login-register">
					<form class="form-horizontal">
						<div class="control-group">
							<label class="control-label"> Amount in dollars to buy
								shares:</label>
							<div class="controls">
								<input type="text" name="cash" value="" required />
							</div>
						</div>
						<tr>
							<td></td>
							<td><input type="hidden" name="fund_id"
								value="${fund.fund_id}" /></td>
						</tr>
						<button type="submit" class="btn btn-danger">Buy
							${fund.name} shares</button>

					</form>
					<hr />
				</div>
			</form>
		</div>
	</div>
</div>
<jsp:include page="template-bottom.jsp" />