<!-- Team 1 -->
<!-- 01/15/2015 -->
<!-- Task 7-->

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<jsp:include page="template-top.jsp" />

<p contenteditable="false">
<div class="matter">
	<div class="container-fluid">
		<div class="page-title">
			<br>
			<h2>Sell Funds</h2>
		</div>
		<hr />

		<div class="span8">
			<p align="center">Please enter the fund information. All fields
				are required.</p>
			<c:forEach var="error" items="${errors}">
				<h5 style="color: red; text-align: center">${error}</h5>
			</c:forEach>
			<div class="form">

				<div class="box-body">
					<div class="row-fluid">
						<h4>List of funds</h4>
						<br>
						<table class="table table-striped table-bordered table-hover">
							<thead>
								<tr>
									<!-- <th><h5>Fund ID</h5></th> -->
									<th><h5>Fund Name</h5></th>
									<th></th>
									<th><h5>Last Closing price</h5></th>
									<th><h5>Enter the number of shares</h5></th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="fund" items="${fundsList}" varStatus="status">

									<form method="post" action="sell.do">
										<tr>
											<td><input type="text" name="name" value="${fund.name }"
												readonly /></td>
											<td><a href="researchFunds.do?id=${fund.fund_id}">Research
													Fund</a></td>
											<td><input type="text" name="price"
												value="${fund.fund_price}" readonly /></td>
											<td><input type="submit" name="sell" value="Sell"></td>
										</tr>
									</form>
								</c:forEach>
							</tbody>
						</table>
					</div>
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