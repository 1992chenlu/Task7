<!-- Team 1 -->
<!-- 01/15/2015 -->
<!-- Task 7-->

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<jsp:include page="template-top.jsp" />

<div class="matter">
	<div class="container-fluid">
		<div class="page-title">
			<h2>View Customer Account</h2>
		</div>
		<hr />
		<p>Please enter the username of the customer:</p>
		<div class="span8">
			<c:forEach var="error" items="${errors}">
				<h5 style="color: red; text-align: center">${error}</h5>
				<br>
			</c:forEach>
			<div class="form">
				<form method="post" action="viewCustomerAccount.do">
					<form class="form-horizontal">
						<div class="control-group">
							<label class="control-label" for="username1">Customer
								username:</label>
							<div class="controls">
								<input type="text" name="customerUsername" value="" />
							</div>
						</div>
						<tr>
							<td colspan="2" align="center"><input type="submit"
								name="button" class="btn btn-danger" value="View Account" /></td>
						</tr>
					</form>
				</form>
			</div>
			<hr />
			<br> <br> <br> <br> <br> <br> <br>
		</div>
	</div>
</div>

<jsp:include page="template-bottom.jsp" />