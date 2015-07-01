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
			<h2>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbspBuy
				Funds</h2>
		</div>
		<hr />
		<div class="span8">
			<p align="center">Please enter the fund information. All fields
				are required.</p>
			<c:forEach var="error" items="${errors}">
				<h5 style="color: red; text-align: center">${error}</h5>
			</c:forEach>
			<c:choose>
				<c:when test="${not empty employee}">
					<p contenteditable="false">
					<form method="post" action="transition.do"></form>
					<div class="box-body">
						<div class="row-fluid">
						<br>
							<h4>List of funds</h4>
							<br>
							<form method="post" action="transition.do">
								Date: <input type="text" name="date" value="" required
									placeholder="MM/DD/YYYY" />
								<table class="table table-striped table-bordered table-hover">
									<thead>
										<tr>
											<th><h5>Sr. No</h5></th>
											<th><h5>Fund Name</h5></th>
											<th><h5>Fund Symbol</h5></th>
											<th><h5>Closing Price</h5></th>
										</tr>
									</thead>
									<tbody>
										<c:forEach var="fund" items="${fundsList}" varStatus="status">

											<tr>
												<td><input type="hidden" name="fund_id"
													value="${fund.fund_id}" /></td>
												<td style="text-align: center"><input type="text"
													value="${status.index+1}" readonly></td>
												<td><input type="text" name="name"
													value="${fund.name }" readonly></td>
												<td><input type="text" name="symbol"
													value="${fund.symbol}" readonly></td>
												<td><input type="text" name="price" value="" required /></td>
											</tr>

										</c:forEach>
									</tbody>
								</table>
								<input type="submit" name="Set Closing" value="setclosing">
							</form>
						</div>
					</div>
		</div>
		</c:when>
		<c:when test="${not empty customer}">
			<p contenteditable="false">
			<div class="box-body">
				<div class="row-fluid">
					<div class="span12">
						<h4>List of funds</h4>
						<br>
						<table class="table table-striped table-bordered table-hover">
							<thead>
								<tr>
									<th><h5>Sr. No</h5></th>
									<th><h5>Fund Name</h5></th>
									<th><h5>Ticker</h5></th>
									<th></th>
									<th><h5>Closing Price</h5></th>
									<th><h5>Operation</h5></th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="fund" items="${fundsList}" varStatus="status">


									<tr style="border: thin; border-color: #680511;">

										<td style="text-align: center">${status.index+1}</td>
										<td>${fund.name}</td>
										<td style="text-align: center">${fund.symbol}</td>
										<td><a href="researchFundCust.do?fund_id=${fund.fund_id}">Research
												Fund</a></td>
										<td style="text-align: right">$ <fmt:formatNumber
												type="number" pattern="#,##0.00"
												value="${fund.fund_price/100}" /></td>
										<td style="text-align: center"><a
											href="buy.do?id=${fund.fund_id}">Buy This Fund</a></td>
									</tr>

								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</c:when>
		<c:otherwise>
			<span class="menu-item"><a href="login.do">Login</a></span>
			<br />
		</c:otherwise>
		</c:choose>

		<hr />
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