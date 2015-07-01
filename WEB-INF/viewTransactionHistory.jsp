<!-- Team 1 -->
<!-- 01/15/2015 -->
<!-- Task 7-->

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<jsp:include page="template-top.jsp" />


<div class="matter">
	<div class="container-fluid">
		<div class="page-title">
			<br>
			<h2>Showing Transaction Records</h2>
		</div>
		<hr />
		<div class="span8">
			<p align="center">Please enter the fund information. All fields
				are required.</p>
				
			<br>
			<c:forEach var="error" items="${errors}">
				<h5 style="color: red; text-align: center">${error}</h5>
				<br>
			</c:forEach>
			<br>
			<div class="box-body">
				<div class="row-fluid">
					<h4>Pending Transactions:</h4>
					<br>
					<table class="table table-striped table-bordered table-hover">
						<thead>
							<tr>
								<th><h5>Transaction ID</h5></th>
								<th><h5>Transaction_type</h5></th>
								<th><h5>Amount($)</h5></th>
								<th><h5>Fund_name</h5></th>
								<th><h5>Shares</h5></th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="tempTrans" items="${tempTransShowList}">
								<tr>
									<td style="text-align: center">${tempTrans.transaction_id}</td>
									<td>${tempTrans.transaction_type}</td>
									<c:choose>
										<c:when test="${tempTrans.amount >= 0}">
											<td style="text-align: right">$ <fmt:formatNumber
													type="number" pattern="#,##0.00"
													value="${tempTrans.amount/100}" /></td>
										</c:when>
										<c:otherwise>
											<td style="text-align: right">-$ <fmt:formatNumber
													type="number" pattern="#,##0.00"
													value="${tempTrans.amount/100*(-1)}" />
											</td>
										</c:otherwise>
									</c:choose>
									<td>${tempTrans.fund_name}</td>
									<td style="text-align: right"><fmt:formatNumber
											type="number" pattern="#,##0.000"
											value="${tempTrans.shares/1000}" /></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>

		<div class="span12">
			<div class="box-body">
				<div class="row-fluid">
					<br> <br>
					<h4>Completed Transactions:</h4>
					<br>
					<table class="table table-striped table-bordered table-hover">
						<thead>
							<tr>
								<th><h5>Transaction ID</h5></th>
								<th><h5>Transaction_type</h5></th>
								<th><h5>Amount($)</h5></th>
								<th><h5>Execution Date</h5></th>
								<th><h5>Fund_name</h5></th>
								<th><h5>Shares</h5></th>
							</tr>
						</thead>

						<tbody>
							<c:forEach var="trans" items="${transShowList}">
								<tr>
									<td style="text-align: center">${trans.transaction_id}</td>
									<td>${trans.transaction_type}</td>
									<c:choose>
										<c:when test="${trans.amount >= 0}">
											<td style="text-align: right">$ <fmt:formatNumber
													type="number" pattern="#,##0.00"
													value="${trans.amount/100}" /></td>
										</c:when>
										<c:otherwise>
											<td style="text-align: right">-$ <fmt:formatNumber
													type="number" pattern="#,##0.00"
													value="${trans.amount/100*(-1)}" />
											</td>
										</c:otherwise>
									</c:choose>
									<td style="text-align: center"><fmt:formatDate pattern="MM/dd/yyyy"
											value="${trans.execute_date}" /></td>
									<td>${trans.fund_name}</td>
									<td>
									<td style="text-align: right"><fmt:formatNumber
											type="number" pattern="#,##0.000"
											value="${trans.shares/1000}" /></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					<hr />
					<br> <br> <br> <br> <br> <br> <br>
				</div>
			</div>
		</div>
	</div>
</div>

<jsp:include page="template-bottom.jsp" />