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
			<h2>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbspDeposit
				Check</h2>
		</div>
		<hr />

		<div class="span8">
			<p align="center">Please enter the fund information. all fields
				are required.</p>
			<c:forEach var="error" items="${errors}">
				<h5 style="color: red; text-align: center">${error}</h5>
			</c:forEach>
			<div class="form">
				<form method="post" action="employeeCheck.do">
					<div class="well login-register">
						<form class="form-horizontal">
							<div class="control-group">
								<label class="control-label" for="amount">Amount to be
									deposited:</label>
								<div class="controls">
									<input type="text" name="cash" value="${cash}" required />
								</div>
							</div>

							<td><input type="hidden" name="customer_id"
								value="${customer.customer_id}" /></td>
							<tr>
								<td colspan="2" align="center"><input type="submit"
									name="button" class="btn btn-danger" value="Deposit Check" /></td>
							</tr>
						</form>
					</div>
				</form>
				<hr />
			</div>
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