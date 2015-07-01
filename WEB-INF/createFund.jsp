<!-- Team 1 -->
<!-- 01/15/2015 -->
<!-- Task 7-->

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<jsp:include page="template-top.jsp" />
<div class="matter">
	<div class="container-fluid">
		<div class="page-title">
			<h2>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbspCreate
				Fund</h2>
		</div>
		<hr />
		<div class="span8">
			<p align="center">Please enter following information. All fields
				are required. Please</p>
			<p align="center">Note the default price for newly created fund
				will be set to $1.00.</p>
			<c:forEach var="error" items="${errors}">
				<h5 style="color: red; text-align: center">${error}</h5>
			</c:forEach>
			<div class="well login-register">
				<div class="form">
					<form method="post" action="createFund.do">
						<form class="form-horizontal">
							<div class="control-group">
								<label class="control-label" for="amount">Enter fund
									name:</label>
								<div class="controls">
									<input type="text" name="fundName" value="" required />
								</div>
							</div>
							<div class="control-group">
								<label class="control-label" for="amount">Enter fund
									ticker:</label>
								<div class="controls">
									<input type="text" name="fundSym" value="" required />
								</div>
							</div>
							<tr>
								<td></td>
								<td><input type="hidden" name="fundPrice" value="1"
									readonly /></td>
							</tr>
							<tr>
								<td colspan="2" align="center"><input type="submit"
									name="button" class="btn btn-danger" value="Create Fund" /></td>
							</tr>
						</form>
					</form>
				</div>
			</div>
		</div>
	</div>
</div>
<hr />
<br>
<br>
<br>
<br>
<br>
<br>
<br>


<jsp:include page="template-bottom.jsp" />