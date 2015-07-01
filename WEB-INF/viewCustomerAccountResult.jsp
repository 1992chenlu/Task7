<!-- Team 1 -->
<!-- 01/15/2015 -->
<!-- Task 7-->

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<jsp:include page="template-top.jsp" />
<c:forEach var="error" items="${errors}">
	<h5 style="color: red; text-align: center">${error}</h5>
	<br>
</c:forEach>

<div class="matter">
	<div class="container-fluid">
		<div class="page-title">
			<h2>${viewedCustomer.username}'sAccount:</h2>
			<hr />
		</div>
		<div class="box-body timeline">
			<div class="row-fluid">
				<div class="span6">
					<div class="time">
						<c:choose>
							<c:when test="${(empty viewedCustomer)}">
								<span style="font-weight: bold;"> <a
									href="viewCustomerAccountAction.do">Click here to enter the
										username of a customer</a>
								</span>
							</c:when>
							<c:otherwise>
								<div class="timatter">
									<h5>First Name: ${viewedCustomer.firstname}</h5>
									<h5>Last Name: ${viewedCustomer.lastname}</h5>
									<h5>username: ${viewedCustomer.username}</h5>
									<h5>Address: ${viewedCustomer.addr_line1}
										${viewedCustomer.addr_line2}</h5>
									<h5>
										Cash Balance: $
										<fmt:formatNumber type="number" pattern="#,##0.00"
											value="${viewedCustomer.cash/100}"/></h5>
								</div>
							</c:otherwise>
						</c:choose>
					</div>
				</div>
			</div>
		</div>
		<hr />
		<br> <br> <br> <br> <br> <br> <br>
	</div>
</div>

<jsp:include page="template-bottom.jsp" />