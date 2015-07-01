<!-- Team 1 -->
<!-- 01/15/2015 -->
<!-- Task 7-->

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<jsp:include page="template-top.jsp" />

<c:forEach var="error" items="${errors}">
	<h3 style="color: red">${error}</h3>
</c:forEach>

<div class="matter">
	<div class="container-fluid">
		<div class="page-title">
			<br>
			<h2>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp${customerInfo.username}'s
				&nbspAccount</h2>
		</div>
		<hr />
		<div class="span8">
			<c:forEach var="error" items="${errors}">
				<h5 style="color: red; text-align: center">${error}</h5>
				<br>
			</c:forEach>
			<div class="box-body timeline">
				<div class="row-fluid">
					<div class="time">
						<div class="tidate b-red">${customerInfo.username}</div>
						<div class="timatter">
							<h5>First Name: ${customerInfo.firstname}</h5>
							<h5>Last Name: ${customerInfo.lastname}</h5>
							<h5>Username: ${customerInfo.username}</h5>
							<h5>Address: ${customerInfo.addr_line1}
								${customerInfo.addr_line2}</h5>
							<h5>
								Cash Balance:
								<fmt:formatNumber type="number" pattern="#,##0.00"
									value="${customerInfo.cash/100}" />
							</h5>
							<h5>
								Available Cash Balance:
								<fmt:formatNumber type="number" pattern="#,##0.00"
									value="${customerInfo.available_cash/100}" />
							</h5>
						</div>
						<div class="box-body">
							<div class="row-fluid">

								<div class="span12">
									<table class="table table-striped table-bordered table-hover">
										<thead>
											<tr>
												<th><h5>Fund Name</h5></th>
												<!-- <th><h5>Fund Id</h5></th> -->
												<th><h5>Shares</h5></th>
											</tr>
										</thead>
										<tbody>
											<c:forEach var="customerFundShow"
												items="${customerFundShowList}" varStatus="status">
												<tr>
													<td>${customerFundShow.fund_name}</td>

													<td style="text-align: right"><fmt:formatNumber
															type="number" pattern="#,##0.000"
															value="${customerFundShow.available_shares/1000}" /></td>
												</tr>
											</c:forEach>
									</table>
								</div>
							</div>
						</div>
					</div>
				</div>
				<hr />

				<br> <br> <br> <br> <br> <br> <br>
			</div>
		</div>
	</div>
</div>


<jsp:include page="template-bottom.jsp" />