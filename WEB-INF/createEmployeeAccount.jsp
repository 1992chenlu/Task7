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
			<h2>Create Employee Account</h2>
		</div>
		<hr />
		<div class="span8">
			<p align="center">Please enter new employee information:</p>
			<c:forEach var="error" items="${errors}">
				<h5 style="color: red; text-align: center">${error}</h5>
			</c:forEach>
			<form method="post" action="createEmployeeAccount.do">
				<div class="well login-register">
					<h5>Create Employee Account</h5>
					<hr />
					<form class="form-horizontal">
						<div class="control-group">
							<label class="control-label" for="username1">Username:</label>
							<div class="controls">
								<input type="text" name="username" value="" required />
							</div>
						</div>
						<div class="control-group">
							<label class="control-label" for="password1">Password:</label>
							<div class="controls">
								<input type="password" name="password" value="" required />
							</div>
						</div>
						<div class="control-group">
							<label class="control-label" for="confirmpassword1">Confirm
								Password:</label>
							<div class="controls">
								<input type="password" name="confirmPassword" value="" required />
							</div>
						</div>

						<div class="control-group">
							<label class="control-label" for="firstname1">First Name:</label>
							<div class="controls">
								<input type="text" name="firstname" value="" required />
							</div>
						</div>
						<div class="control-group">
							<label class="control-label" for="lastname1">Last Name:</label>
							<div class="controls">
								<input type="text" name="lastname" value="" required />
							</div>
						</div>
						<tr>
							<button type="submit" name="button" class="btn btn-danger">Create
								Employee User</button>
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