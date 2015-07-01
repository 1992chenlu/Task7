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
			<h2>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbspTransition Day</h2>
			
		</div>
		<hr />
		<div class="span8">
			<p align="center">Please enter the fund information. all fields
				are required.</p>
				<br>
			<c:forEach var="error" items="${errors}">
				<h5 style="color: red" align="center">${error}</h5>
				<br>
			</c:forEach>
			<p contenteditable="false">
			<h4>List of funds to update</h4>
			<br>
			<form method="post" action="transition.do">
				Last Closing Date:
				<fmt:formatDate pattern="MM/dd/yyyy" value="${lastClosingDate}" />
				<br> <br> New Closing Date: <input type="text" name="date"
					value="" required placeholder="MM/DD/YYYY" />
				<div class="box-body">
					<div class="row-fluid">
						<table class="table table-striped table-bordered table-hover">
							<thead>
								<tr>
									<th><h5>Name</h5></th>
									<th><h5>Ticker</h5></th>
									<th><h5>Closing Price($)</h5></th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="fund" items="${fundsList}" varStatus="status">
									<tr>
										<td>${ fund.getName() }</td>
										<td>${ fund.getSymbol() }</td>
										<td><input name="fund_${fund.getFund_id()}" type="text"
											required class="form-control"
											value="${price_map.get(fund.getFund_id())}"></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
						<input type="submit" name="button" class="btn btn-danger"
							value="setclosing">
					</div>
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