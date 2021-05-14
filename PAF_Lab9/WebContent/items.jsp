<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="com.Item"%>
<%
//Save---------------------------------
if (request.getParameter("userName") != null) { 
Item itemObj = new Item(); 
String stsMsg = ""; 
	//Insert--------------------------
	if (request.getParameter("hidItemIDSave") == "") 
	{ 
	stsMsg = itemObj.insertusers(request.getParameter("userName"), 
	request.getParameter("userEmail"), 
	request.getParameter("userPhoneNumber"), 
	request.getParameter("userAddress")); 
	} 
	else//Update----------------------
	{ 
	stsMsg = itemObj.updateusers(request.getParameter("hidItemIDSave"), 
	request.getParameter("userName"), 
	request.getParameter("userEmail"), 
	request.getParameter("UserPhoneNumber"), 
	request.getParameter("usrAddress")); 
	} 
	session.setAttribute("statusMsg", stsMsg); 
} 
//Delete-----------------------------
if (request.getParameter("hidItemIDDelete") != null) 
{ 
Item itemObj = new Item(); 
String stsMsg = 
itemObj.removeUser(request.getParameter("hidItemIDDelete")); 
session.setAttribute("statusMsg", stsMsg); 
}
%>

<html>
<head>
<meta charset=ISO-8859-1">
<title>User Management</title>
</head>
<link href="Views/bootstrap.min.css" rel="stylesheet" type="text/css">
<script src="Components/jquery.js"></script>
<script src="Components/items.js"></script>
<body>
<!--  
<script type="text/javascript">

if(typeof alertSuccess == "undefined"){
	alert ("not working");
}else{
	alert("working");
}
</script>
-->


<div class="container">
<h1 >Items Management</h1>
<form method="post" action="items.jsp" id ="formItem">
	 <div class="mb-1 col-6">
   		 <label for="userName" class="form-label">User Name</label>
    	<input type="text"name="userName"  class="form-control" id="userName">
    	<input  type="hidden" name="hidItemIDSave" id ="hidItemIDSave" >
    </div>
    <div class="mb-1 col-6">
   		 <label for="userEmail" class="form-label">User Email</label>
    	<input type="text" name="useEmail"  class="form-control" id="userEmail">
    </div>
    <div class="mb-1 col-6">
   		 <label for="userPhoneNumber" class="form-label">Phone Number</label>
    	<input type="text"name="userPhoneNumber"  class="form-control" id="userPhoneNumber">
    </div>
    <div class="mb-4 col-6">
   		 <label for="userAddress" class="form-label">User Address</label>
    	<input type="text"name="userAddress"  class="form-control" id="userAddress">
    </div>

 <input name="btnSubmit" class="btn btn-primary mb-3" id="btnSave" type="button" value="Save">
 
</form>
<br>
<div class="alert alert-primary"  id="alertSuccess">
	<%
 		out.print(session.getAttribute("statusMsg"));
	%>
</div>
<div class="alert alert-danger"  id="alertError"> </div>


<%
 Item itemObj = new Item();
 out.print(itemObj.readusers());
%>
</div>
</body>

</html>
