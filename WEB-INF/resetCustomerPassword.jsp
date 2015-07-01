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
			<h2>Reset Customer Password</h2>
		</div>
		<hr />
		<div class="span8">
			<p align="center">Please enter the username, the new password and
				confirm it.</p>
			<c:forEach var="error" items="${errors}">
				<h5 style="color: red; text-align: center">${error}</h5>
			</c:forEach>
			<form method="post" action="resetCustomerPassword.do">
				<div class="well login-register">
					<h5>Reset Customer Password</h5>
					<hr />
					<form class="form-horizontal">
						<div class="control-group">
							<label class="control-label"></label>
							<div class="controls">
								<input type="hidden" name="customer_id" value="${customer_id }" />
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">New Password:</label>
							<div class="controls">
								<input type="password" name="newPassword" value="" />
							</div>
						</div>
						<div class="control-group">
							<label class="control-label">Confirm New Password:</label>
							<div class="controls">
								<input type="password" name="confirmPassword" value="" />
							</div>
						</div>
						<tr>
							<td colspan="2" align="center"><input type="submit"
								name="button" value="Reset Password" /></td>
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