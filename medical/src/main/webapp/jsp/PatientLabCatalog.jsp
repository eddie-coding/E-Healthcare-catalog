<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
  <title>Doctor Catalog</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/bootstrap.min.css"/>
  <link rel="stylesheet" href="https://www.w3schools.com/w3css/3/w3.css">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
  <style>
	.topnav-right {
 	 float: right;
	}
	.jumbotron {
 	 background-image: url("<%=request.getContextPath()%>/resources/images/bookings.jpg");
 	 background-size: cover;
 	 min-height: 300px;
	}
</style>
</head>
<body>
	<!-- Navigation -->
	<nav class="w3-bar w3-black">
  		<a href="/medical" class="w3-button w3-bar-item">Home</a>
  		
  		<div class = "topnav-right"><a href="/medical/patient/logout" class="w3-button w3-bar-item">Logout</a></div>
	</nav> 
  <div class="jumbotron"></div> 
  <div class="container"> 
  <h1>Welcome</h1>
  <h2>Available Labs and slots are as follows :</h2>    
  </div>    
  <table class="table table-dark table-striped">
    <thead>
      <tr>
        <th>Lab id</th>
        <th>Lab Name</th>
        <th>Lab Type</th>
        <th>Available Slots</th>
      </tr>
    </thead>
    <tbody>
	<c:if test="${not empty labs}">
    <c:forEach var="lab" items="${labs}" varStatus="status">      <tr>
        <td>${lab.id}</td>
        <td>${lab.name}</td>
        <td>${lab.type}</td>
        <td><form:form id="booked${status.index }" action="labbookingprocess" method="post" modelAttribute="bookedlabslots" >
					<form:select path="bookedlabslot" >
						<form:options items="${slots[status.index] }" />
					</form:select>
					<form:input type="hidden" path="patientid" value="${id }" />
					<form:input type="hidden" path="id" value="${lab.id }" />
		 			<input type="submit" name="Submit" value="Submit" tabindex="2" />
				</form:form></td>
      </tr>
    </c:forEach>
    </c:if>
    </tbody>
  </table>
</body>