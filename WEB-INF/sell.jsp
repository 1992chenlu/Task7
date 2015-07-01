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
			<h2>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
				&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbspSell Funds</h2>
		</div>
		<hr />

		<div class="span8">

			<p align="center">Please enter the fund information. All fields
				are required. All fields are required.</p>

			<br>
			<h5 align="center">Note: Your Current Share is:
				${customerFundShow.fund_name}</h5>

			<form method="post" action="sell.do">
				<h5 align="center">You wish to sell
					${customerFundShow.fund_name} 's shares.</h5>
				<p align="center">
					Available shares:
					<fmt:formatNumber type="number" pattern="#,##0.000"
						value="${customerFundShow.available_shares / 1000}" />
				</p>
				<c:forEach var="error" items="${errors}">
					<h5 style="color: red; text-align: center">${error}</h5>
				</c:forEach>
				<br>
				<div class="well login-register">
					<form class="form-horizontal">
						<div class="control-group">
							<label class="control-label">Shares want to sell:</label>
							<div class="controls">
								<input type="text" name="shares" value="" required />
							</div>
						</div>
						<tr>
							<td></td>
							<td><input type="hidden" name="fund_id"
								value="${customerFundShow.fund_id}" /></td>
						</tr>
						<tr>
							<button type="submit" name="button" class="btn btn-danger">Sell
								${customerFundShow.fund_name}'s shares</button>
						</tr>
					</form>
				</div>
			</form>
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