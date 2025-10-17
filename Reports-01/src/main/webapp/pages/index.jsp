<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Report Application</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
	crossorigin="anonymous">
</head>
<body>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
		crossorigin="anonymous"></script>
</body>
<body>

	<div class="container">
		<h3 class="pb-3 pt-3">ReportApplication</h3>

		<form:form action="searchData" modelAttribute="searchRequest"
			method="POST">

			<table>
				<tr>
					<td>Plan Name</td>
					<td><form:select path="planName">
							<form:option value="">-select-</form:option>
							<form:options items="${names}" />
						</form:select></td>

					<td>Plan Status</td>
					<td><form:select path="planStatus">
							<form:option value="">-select-</form:option>
							<form:options items="${status}" />
						</form:select></td>

					<td>Gender</td>
					<td><form:select path="gender">
							<form:option value="">-select-</form:option>
							<form:option value="MALE">MALE-</form:option>
							<form:option value="FE-MALE">FE-MALE</form:option>
						</form:select></td>
				</tr>
				<tr>
					<td>StartDate:</td>
					<td><form:input path="startDate" type="date" /></td>
					<td>EndDate:</td>
					<td><form:input path="endDate" type="date" /></td>
				</tr>

				<tr>
					<!-- <td><input type="submit" value="Reset" /></td> -->
					<td><a href="/" class="btn btn-secondary">Reset</a></td>
					<td><input type="submit" value="Search"
						class="btn btn-primary" /></td>

				</tr>
			</table>


		</form:form>


		<hr>
		<table class="table table-striped table-hover">
			<thead>
				<tr>
					<th>S.N0</th>
					<th>Holder Name</th>

					<th>Gender</th>
					<th>Plan Status</th>
					<th>Plan Name</th>
					<th>Start date</th>
					<th>End Date</th>
					<th>Benefit Amount</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${plans}" var="plan">
					<tr>
						<td>${plan.citezenId}</td>
						<td>${plan.citizenName}</td>
						<td>${plan.gender}</td>
						<td>${plan.planStatus}</td>
						<td>${plan.planName}</td>
						<td>${plan.planStartDate}</td>
						<td>${plan.planEndDate}</td>
						<td>${plan.beniftAmount}</td>
					</tr>
				</c:forEach>
				<c:if test="${empty plans}"> 
				 No records found
				</c:if>
			</tbody>
		</table>
		</hr>
		<%-- <p class="text-success">${msg}</p> --%>
		Export: <a href="excel">Excel</a> <a href="pdf">PDF</a>
	</div>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
		crossorigin="anonymous"></script>
</body>

</body>
</html>