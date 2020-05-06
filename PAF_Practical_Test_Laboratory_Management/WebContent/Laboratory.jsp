<%@ page import="com.Laboratory"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>    

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Laboratory Management</title>
<link rel="stylesheet" href="views/bootstrap.min.css"> 
<script src="Components/jquery-3.4.1.min.js"></script> 
<script src="Components/Laboratory.js"></script>
</head>
<body>
<div class="container"> 
	<div class="row">  
		<div class="col-6"> 
			<h1 class="text-center">Laboratory Management</h1>
				<form id="formLaboratory" name="formLaboratory" method="post" action="Laboratory.jsp">  
					Laboratory code:  
					<input id="laboratoryCode" name="laboratoryCode" type="text" class="form-control form-control-sm">  
					<br> Laboratory name:  
					<input id="name" name="name" type="text" class="form-control form-control-sm">  
					<br> Laboratory Address:  
					<input id="address" name="address" type="text" class="form-control form-control-sm">  
					<br> Laboratory TelNo:  
					<input id="telNo" name="telNo" type="text" class="form-control form-control-sm">  
					<br>  City:
					<input id="city" name="city" type="text" class="form-control form-control-sm">  
					<br>  
					<input id="btnSave" name="btnSave" type="button" value="Save" class="btn btn-primary">  
					<input type="hidden" id="hidLabIDSave" name="hidLabIDSave" value=""> 
				</form>
				
				<br><br>
				
				<div id="alertSuccess" class="alert alert-success">
					
				</div>
				<div id="alertError" class="alert alert-danger"></div>
				
				<br>
				<div id="divLabsGrid">
					<%
						Laboratory itemObj = new Laboratory();
						out.print(itemObj.readLabs());
					%>
				</div>
				
				 
			</div>
		</div>
</div>
 
</body>
</html>