<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/3/w3.css">
<head>
	<title>Welcome</title>
</head>
<body>

 <!-- Navigation -->
<nav class="w3-bar w3-black">
  <a href="#" class="w3-button w3-bar-item">Home</a>
  <a href="adminlogin" class="w3-button w3-bar-item">Administrator</a>
  <a href="doctorlogin" class="w3-button w3-bar-item">Doctor</a>
  <a href="patientloginandsignup" class="w3-button w3-bar-item">Patient</a>
</nav> 

<section>
  <img class="mySlides" src="<%=request.getContextPath()%>/resources/images/image1.png" style="width:100%">
  <img class="mySlides" src="<%=request.getContextPath()%>/resources/images/image2.jpg" style="width:100%">
  <img class="mySlides" src="<%=request.getContextPath()%>/resources/images/image3.jpg" style="width:100%">
</section>

<script>
//Automatic Slideshow - change image every 3 seconds
var myIndex = 0;
carousel();

function carousel() {
  var i;
  var x = document.getElementsByClassName("mySlides");
  for (i = 0; i < x.length; i++) {
    x[i].style.display = "none";
  }
  myIndex++;
  if (myIndex > x.length) {myIndex = 1}
  x[myIndex-1].style.display = "block";
  setTimeout(carousel, 3000);
}
</script>

 <section class="w3-container w3-center">
  <h2 class="w3-wide">E-HealthCare Catalog</h2>
  <p class="w3-opacity"><i>Welcome</i></p>
</section> 

 <section class="w3-container w3-content w3-center" style="max-width:600px">
<p class="w3-justify">
Book appointments with doctor according to your time and availability of a particular doctor.Sign up with us for pre booking your appointments with the best doctors.</p>
</section> 

 <section class="w3-row-padding w3-center w3-light-grey">
  <article class="w3-third">
    <p>Home</p>
    <a href="#">
    <img src="<%=request.getContextPath()%>/resources/images/home.png" alt="Random Name" style="width:100%">
  	</a>
  </article>
  <article class="w3-third">
    <p>Doctor</p>
    <a href="doctorlogin">
    <img src="<%=request.getContextPath()%>/resources/images/doctor.png" alt="Random Name" style="width:100%">
  	</a>
  </article>
  <article class="w3-third">
    <p>Patient</p>
    <a href="patientloginandsignup">
    <img src="<%=request.getContextPath()%>/resources/images/patient.png" alt="Random Name" style="width:100%">
  	</a>
  </article>
</section> 
</body>
</html>