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
			<h2>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbspChange Password</h2>
		</div>
		<hr />
		<div class="span8">
			<p align="center">Please enter the new password and confirm it.</p>
			<c:forEach var="error" items="${errors}">
				<h5 style="color: red; text-align: center">${error}</h5>
			</c:forEach>
			<form method="post" action="changePasswordCustomer.do">
				<div class="well login-register">
					<h5>Change Yout Password</h5>
					<hr />
					<form class="form-horizontal">

						<div class="control-group">
							<label class="control-label">New Password: </label>
							<div class="controls">
								<input type="password" name="newPassword" value="" required />
							</div>
						</div>

						<div class="control-group">
							<label class="control-label" for="inputPassword">Confirm
								New Password: </label>
							<div class="controls">
								<input type="password" name="confirmPassword" value="" required />
							</div>
						</div>

						<button type="submit" name="button" class="btn btn-danger">Reset
							Password</button>
				</div>
			</form>
			</form>
			<hr />
			<br> <br> <br> <br> <br> <br> <br>
		</div>
	</div>
</div>


<jsp:include page="template-bottom.jsp" />