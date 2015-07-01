<!-- Team 1 -->
<!-- 01/15/2015 -->
<!-- Task 7-->

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<jsp:include page="template-top.jsp" />
<div class="matter">
	<div class="container-fluid">
		<!-- Title starts -->
		<div class="page-title">
			<hr>
			<h1>Welcome to Carnegie Financial Services</h1>
		</div>
	</div>

	<c:forEach var="error" items="${errors}">
		<div class="span12">
			<div class="span8">

				<div class="well login-register">
					<h5 style="color: red; text-align: center">${error}</h5>
				</div>
			</div>
		</div>

	</c:forEach>
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