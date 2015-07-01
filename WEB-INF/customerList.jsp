<!-- Team 1 -->
<!-- 01/15/2015 -->
<!-- Task 7-->

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<jsp:include page="template-top.jsp" />

<div class="matter">
	<div class="container-fluid">
		<div class="page-title">
			<br>
			<h2>View Customer Account</h2>
		</div>
		<hr />
		<div class="box-body">
			<div class="row-fluid">
				<div class="span8">
					<c:forEach var="error" items="${errors}">
						<h5 style="color: red; text-align: center">${error}</h5>
					</c:forEach>
					<h4>List of Customers</h4>
					<table class="table table-striped table-bordered table-hover">
						<thead>
							<tr>
								<th>Name</th>
								<th style="text-align:center" colspan="4" >Operation</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="customer" items="${customerList}"
								varStatus="status">
								<tr>

									<td> ${customer.firstname}&nbsp${customer.lastname}</td>
									<td><a href="viewAccount.do?id=${customer.customer_id}"><button
												type="submit" name="button" class="btn btn-danger">View
												Account Details</button></a></td>
									<td><a
										href="resetCustomerPassword.do?id=${customer.customer_id}"><button
												type="submit" name="button" class="btn btn-danger">Reset
												Password</button></a></td>
									<td><a href="employeeCheck.do?id=${customer.customer_id}"><button
												type="submit" name="button" class="btn btn-danger">Deposit
												Check</button></a></td>
									<td><a
										href="transactionHistory.do?id=${customer.customer_id}"><button
												type="submit" name="button" class="btn btn-danger">View
												Transaction History</button></a></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
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