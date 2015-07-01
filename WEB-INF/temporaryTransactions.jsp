<!-- Team 1 -->
<!-- 01/15/2015 -->
<!-- Task 7-->

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
					<h4>List of Pending Transaactions</h4>
					<table class="table table-striped table-bordered table-hover">
						<thead>
							<tr>
								<th><h5>Serial No.</h5></th>
								<th><h5>Transaction_type</h5></th>
								<th><h5>Amount</h5></th>


								<th><h5>Fund_name</h5></th>
								<th><h5>Shares</h5></th>
							</tr>
						</thead>

						<tbody>
							<c:forEach var="trans" items="${showTemp}" varStatus="status">
								<tr>
									<td>${status.index+1}</td>
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

									<td>${trans.fund_name}</td>
									<td style="text-align: right"><fmt:formatNumber
											type="number" pattern="#,##0.000"
											value="${trans.shares/1000}" /></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					<hr />
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