<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/bootstrap.min.css"/>
<link rel="stylesheet" href="https://www.w3schools.com/w3css/3/w3.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Welcome</title>
</head>
<body>
	<!-- Navigation -->
	<nav class="w3-bar w3-black">
  		<a href="/medical" class="w3-button w3-bar-item">Home</a>
  		<a href="adminlogin" class="w3-button w3-bar-item">Administrator</a>
  		<a href="doctorlogin" class="w3-button w3-bar-item">Doctor</a>
  		<a href="patientloginandsignup" class="w3-button w3-bar-item">Patient</a>
	</nav>  
	<!--<div class="d-flex justify-content-around" style="margin-top:20%" >
		<div style="font-size: xxx-large;text-align: center;width: 100%;">
    		<a href="patientlogin" style="text-decoration: none">Login</a>
		</div>
        <div style="font-size: xxx-large;text-align: center;width: 100%;">
            <a href="patientregister" style="text-decoration: none">Register</a>
        </div>
        <div style="font-size: xxx-large;text-align: center;width: 100%;">
            <a href="home.jsp" style="text-decoration: none">Home</a>
        </div>
    </div> -->
	<div class="card-deck">
	<div class="d-flex justify-content-around" style="margin-top:5%" >
  <div class="card">
  <a href="/medical"><img class="card-img-top" src="<%=request.getContextPath()%>/resources/images/home3.png" alt="Card image cap"></a>
    <div class="card-body">
      <h2 class="card-title"><a href="/medical">Home</a></h2>
    </div>
    <div class="card-footer">
    </div>
  </div>
  <div class="card">
  <a href="doctors"><img class="card-img-top" src="<%=request.getContextPath()%>/resources/images/doctor-login.jpg" alt="Card image cap"></a>
    <div class="card-body">
      <h2 class="card-title"><a href="doctors">Doctors List</a></h2>
    </div>
    <div class="card-footer">
    </div>
  </div>
  <div class="card">
  <a href="labs"><img class="card-img-top" src="<%=request.getContextPath()%>/resources/images/booklab.png" alt="Card image cap"></a>
    <div class="card-body">
      <h2 class="card-title"><a href="labs">Labs List</a></h2>
    </div>
    <div class="card-footer">
    </div>
    </div>
  </div>
</div>
</body>
</html>