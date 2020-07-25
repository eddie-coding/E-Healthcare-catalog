<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
  <title>Patient</title>
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
</style>
<body>
	<!-- Navigation -->
	<nav class="w3-bar w3-black">
  		<a href="/medical" class="w3-button w3-bar-item">Home</a>
  		
  		<div class = "topnav-right"><a href="logout" class="w3-button w3-bar-item">Logout</a></div>
	</nav> 
	<div class="card-deck">
	<div class="d-flex justify-content-around" style="margin-top:5%" >
  <div class="card">
  <a href="${id }/bookings"><img class="card-img-top" src="<%=request.getContextPath()%>/resources/images/booking.png" alt="Card image cap"></a>
    <div class="card-body">
      <h2 class="card-title"><a href="${id }/bookings">Bookings</a></h2>
    </div>
    <div class="card-footer">
    </div>
  </div>
  <div class="card">
  <a href="${id }/bookdoctor"><img class="card-img-top" src="<%=request.getContextPath()%>/resources/images/bookdoc.png" alt="Card image cap"></a>
    <div class="card-body">
      <h2 class="card-title"><a href="${id }/bookdoctor">Book Doctor Slots</a></h2>
    </div>
    <div class="card-footer">
    </div>
  </div>
  <div class="card">
  <a href="${id }/booklab"><img class="card-img-top" src="<%=request.getContextPath()%>/resources/images/booklab.png" alt="Card image cap"></a>
    <div class="card-body">
      <h2 class="card-title"><a href="${id }/booklab">Book Lab Slots</a></h2>
    </div>
    <div class="card-footer">
    </div>
    </div>
  </div>
</div>
</body>
</html>