<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
  <title>Labs</title>
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
 	 min-height: 400px;
	}
	table tr {
   	 text-align: center; 
	}
</style>
</head>
<body>
<div class="jumbotron">
  </div>
  <div class="container"> 
<div align="center">
           <h1>Lab List</h1>
           <h3><a href="labadd">Add Lab</a></h3>
           <table class="table table-dark table-striped">
    <thead>
      <tr>
                  <th>SlNo.</th>
                  <th>UserID</th>
                  <th>Name</th>
                  <th>Department</th>
                  <th>Action</th>
      </tr>
    </thead>
    <tbody>
    <c:forEach items="${labslist}" var="lab" varStatus="status">
             <tr>
                 <td>${status.index +1}</td>
                 <td>${lab.id}</td>
                 <td>${lab.name}</td>
                 <td>${lab.type}</td>
                 <td>
                      <a href="labedit/${lab.id }">Edit</a>
                      &nbsp;&nbsp;
                      <a href="labdelete/${lab.id }">Delete</a>
                      
                 </td>     
             
             
             
             </c:forEach>
    </tbody>
  </table>
</div>
</div>
</body>
</html>             