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
			<h2>Research Fund</h2>
		</div>
		<hr />
		<p>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp Click Research Fund to
			research the respective Fund.</p>
		<div class="span8">
			<c:forEach var="error" items="${errors}">
				<h5 style="color: red; text-align: center">${error}</h5>
			</c:forEach>
			<div class="box-body timeline">
				<div class="row-fluid">
					<div class="time">
						<div class="tidate b-red">
							Below prices are with respect to the last Closing Date:
							<fmt:formatDate pattern="MM/dd/yyyy" value="${lastClosingDate}" />
						</div>
						<form method="post" action="researchFundCust.do">
							<div class="box-body">
								<div class="row-fluid">

									<div class="span12">
										<table class="table table-striped table-bordered table-hover">
											<thead>
												<tr>
													<th><h5>Sr. No.</h5></th>
													<th><h5>Name</h5></th>
													<th><h5>Ticker</h5></th>
													<th><h5>Last Closing Price($)</h5></th>
													<th><h5>Operation</h5></th>

												</tr>
											</thead>
											<tbody>
												<c:forEach var="fund" items="${fundsList}"
													varStatus="status">
													<tr>
														<td style="text-align: center">${status.index+1}</td>
														<td>${ fund.getName() }</td>
														<td>${ fund.getSymbol() }</td>
														<td style="text-align: right">${price_map.get(fund.getFund_id())}</td>
														<td style="text-align: center"><a
															href="researchFundCust.do?fund_id=${fund.fund_id}">Research
																Fund</a></td>
													</tr>
												</c:forEach>
										</table>
									</div>
								</div>
							</div>
						</form>
					</div>
				</div>
				<hr />

				<br> <br> <br> <br> <br> <br> <br>
			</div>
		</div>
	</div>
</div>


<jsp:include page="template-bottom.jsp" />