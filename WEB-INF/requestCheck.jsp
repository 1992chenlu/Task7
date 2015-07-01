<!-- Team 1 -->
<!-- 01/15/2015 -->
<!-- Task 7-->

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<jsp:include page="template-top.jsp" />

<div class="matter">
	<div class="container-fluid">
		<div class="page-title">
			<br>
			<h2>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbspRequest
				Check</h2>
		</div>
		<hr />
		<div class="span8">
			<p align="center">Please enter the fund information. All fields
				are required.</p>
			<p align="center">Note: &nbsp&nbspYour Current Balance is:
				${customer.cash/100} &nbsp&nbsp&nbsp&nbsp&nbsp&nbspYour Available
				Balance is: ${customer.available_cash/100}</p>
			<c:forEach var="error" items="${errors}">
				<h5 style="color: red; text-align: center">${error}</h5>
			</c:forEach>
			<form method="post" action="requestCheck.do">
				<div class="well login-register">
					<form class="form-horizontal">
						<div class="control-group">
							<label class="control-label"> Amount to be withdrawn: </label>
							<div class="controls">
								<input type="text" name="cash" value="${cash}" required />
							</div>
						</div>
						<button type="submit" class="btn btn-danger">Withdraw
							Amount</button>
					</form>
				</div>
			</form>
			<hr />
		</div>
	</div>
</div>
<br>
<br>
<br>
<br>
<br>
<br>
<br>

<jsp:include page="template-bottom.jsp" />