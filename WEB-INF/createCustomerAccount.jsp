<!-- Team 1 -->
<!-- 01/15/2015 -->
<!-- Task 7-->

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<jsp:include page="template-top.jsp" />

<div class="matter">
	<div class="container-fluid">
		<div class="page-title">
			<br>
			<h2>Create Customer Account</h2>
		</div>
		<hr />
		<div class="span8">
			<p align="center">Please enter new customer information:</p>
			<c:forEach var="error" items="${errors}">
				<h5 style="color: red; text-align: center">${error}</h5>
			</c:forEach>
			<form method="post" action="createCustomerAccount.do">
				<div class="well login-register">

					<h5>Create Customer Account</h5>
					<hr />
					<form class="form-horizontal">
						<div class="control-group">
							<label class="control-label">Username: </label>
							<div class="controls">
								<input type="text" name="username" value="" required />
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">Password: </label>
							<div class="controls">
								<input type="password" name="password" value="" required />
							</div>
						</div>
						<div class="control-group">
							<label class="control-label" for="confirmpassword">Confirm
								Password: </label>
							<div class="controls">
								<input type="password" name="confirmPassword" value="" required />
							</div>
						</div>
						<div class="control-group">
							<label class="control-label" for="firstname">First Name:</label>
							<div class="controls">
								<input type="text" name="firstname" value="" required />
							</div>
						</div>
						<div class="control-group">
							<label class="control-label" for="lastname">Last Name:</label>
							<div class="controls">
								<input type="text" name="lastname" value="" required />
							</div>
						</div>
						<div class="control-group">
							<label class="control-label" for="addrline1">Address
								Line1:</label>
							<div class="controls">
								<input type="text" name="addr_line1" value="" required />
							</div>
						</div>
						<div class="control-group">
							<label class="control-label" for="addrline2">Address
								Line2:</label>
							<div class="controls">
								<input type="text" name="addr_line2" value="" required />
							</div>
						</div>
						<div class="control-group">
							<label class="control-label" for="city">City:</label>
							<div class="controls">
								<input type="text" name="city" value="" required />
							</div>
						</div>
						<div class="control-group">
							<label class="control-label" for="state">State:</label>
							<div class="controls">
								<input type="text" name="state" value="" required />
							</div>
						</div>
						<div class="control-group">
							<label class="control-label" for="zip">Zip Code:</label>
							<div class="controls">
								<input type="text" name="zip" value="" required />
							</div>
						</div>

						<tr>
							<button type="submit" name="button" class="btn btn-danger">Create
								Customer User</button>
						</tr>
					</form>
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