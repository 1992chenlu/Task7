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
			<h2>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbspSell
				Funds</h2>
		</div>
		<hr />
		<div class="span8">
			<p align="center">Please enter the fund information. All fields
				are required.</p>
			<c:forEach var="error" items="${errors}">
				<h5 style="color: red; text-align: center">${error}</h5>
			</c:forEach>
			<p contenteditable="false">
			<div class="box-body">
				<div class="row-fluid">
					<br>
					<h4>List of funds available</h4>
					<br>
					<table class="table table-striped table-bordered table-hover">
						<thead>
							<tr>
								<th><h5>Sr. No</h5></th>
								<th><h5>Fund Name</h5></th>
								<th><h5></h5></th>
								<th><h5>Closing Price</h5></th>
								<th><h5>Available Shares</h5></th>
								<th><h5></h5></th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="customerFundShow" items="${customerFundShowList}"
								varStatus="status">
								<tr>
									<td>${status.index+1}</td>
									<td>${customerFundShow.fund_name}</td>
									<td><a
										href="researchFundCust.do?fund_id=${customerFundShow.fund_id}">Research
											Fund</a></td>
									<td style="text-align: right">$ <fmt:formatNumber
											type="number" pattern="#,##0.00"
											value="${customerFundShow.price/100}" /></td>
									<td style="text-align: right"><fmt:formatNumber
											type="number" pattern="#,##0.000"
											value="${customerFundShow.available_shares/1000}" /></td>
									<td><a href="sell.do?id=${customerFundShow.fund_id}">Sell
											This Fund</a></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					<hr />
				</div>
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