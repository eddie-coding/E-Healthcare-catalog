<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/bootstrap.min.css"/>
<title>Administrator</title>
</head>
<body>
	<form:form id="regForm" modelAttribute="admin" action="adminloginprocess"
		method="post" class="text-center border border-light p-5" style="margin-left: 20%; margin-right: 20%;margin-top: 10%;box-shadow: 0 4px 8px 0 #ba68f6, 0 6px 20px 0 rgba(0, 0, 0, 0.19);">
		<label style="font-size: 100;margin-bottom: 10%;font-size:40;font-family:none">Admin</label>
		<form:input path="id" id="defaultLoginFormEmail" class="form-control mb-4" placeholder="ID"></form:input>
		<form:input type="password" path="password" id="defaultLoginFormPassword" class="form-control mb-4" placeholder="Password"></form:input>
		<div class="d-flex justify-content-around">
        <div>
            <div >
                <div>${message}</div>
            </div>
        </div>
    </div>
		<form:button class="btn btn-info btn-block my-4" type="submit" style="background-color: purple;border-color: purple">Sign in</button></form:button>
	</form:form>
</body>