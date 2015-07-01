<!-- Team 1 -->
<!-- 01/15/2015 -->
<!-- Task 7-->

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<jsp:include page="template-top.jsp" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<c:forEach var="error" items="${errors}">
	<h3 style="color: red">${error}</h3>
</c:forEach>

<div class="matter">
	<div class="container-fluid">
		<div class="page-title">
			<br>
			<h2>Statistics for Fund Name : <c:out value="${funds.name}"></c:out></h2>
		</div>
		<hr />
		<p>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp Fund Name : <c:out value="${funds.name}"></c:out></p>
		<p>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp Ticker : <c:out value="${funds.symbol}"></c:out></p>
	</div>
</div>

<script type="text/javascript"
	src="https://www.google.com/jsapi?autoload={
            'modules':[{
              'name':'visualization',
              'version':'1',
              'packages':['corechart']
            }]
          }"></script>

<script type="text/javascript">
	google.setOnLoadCallback(drawChart);

	function drawChart() {

		var data = google.visualization.arrayToDataTable([

				[ {
					label : 'Date',
					id : 'string'
				}, {
					label : 'Price',
					type : 'number'
				}, // Use object notation to explicitly specify the data type.
				],
				<c:forEach items="${fundsDetails}" var="fund">[
						"${fund.price_date}", "${fund.price/100}"],
				</c:forEach> ]);

		var options = {
			title : 'Fund Statistics',
			legend : {
				position : 'bottom'
			},
			vAxis : {
				format : '#,##0.00',
				title : 'Price'
			},
			
			hAxis : {
				title : 'Date'
			}

		};

		// Create a formatter.
		// This example uses object literal notation to define the options.
		var formatter = new google.visualization.DateFormat({
			formatType : 'long'
		});

		// Reformat our data.
		formatter.format(data, 1);

		var chart = new google.visualization.LineChart(document
				.getElementById('curve_chart'));

		chart.draw(data, options);
	}
</script>
</head>
<body>
	<div id="curve_chart" style="width: 900px; height: 500px"></div>
</body>
</html>

<br> <br> <br> <br> <br> <br> <br>
<jsp:include page="template-bottom.jsp" />