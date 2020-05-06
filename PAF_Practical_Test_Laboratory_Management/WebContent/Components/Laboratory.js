$(document).ready(function() 
{  
	if ($("#alertSuccess").text().trim() == "")  
	{   
		$("#alertSuccess").hide();  
	}  
	$("#alertError").hide(); }); 
 
// SAVE ============================================ 
$(document).on("click", "#btnSave", function(event) 
{  
	// Clear alerts---------------------  
	$("#alertSuccess").text("");  
	$("#alertSuccess").hide();  
	$("#alertError").text("");  
	$("#alertError").hide(); 
 
	// Form validation-------------------  
	var status = validateItemForm();  
	if (status != true)  
	{   
		$("#alertError").text(status);   
		$("#alertError").show();   
		return;  
	} 
 
	// If valid------------------------  
	var type = ($("#hidLabIDSave").val() == "") ? "POST" : "PUT"; 
	
	$.ajax( 
	{  
		url : "LaboratoryAPI",  
		type : type,  
		data : $("#formLaboratory").serialize(),  
		dataType : "text",  
		complete : function(response, status)  
		{   
			onItemSaveComplete(response.responseText, status);  
		} 
	}); 
}); 

function onItemSaveComplete(response, status) 
{  
	if (status == "success")  
	{   
		var resultSet = JSON.parse(response); 

		if (resultSet.status.trim() == "success")   
		{    
			$("#alertSuccess").text("Successfully saved.");    
			$("#alertSuccess").show(); 

			$("#divLabsGrid").html(resultSet.data);   
		} else if (resultSet.status.trim() == "error")   
		{    
			$("#alertError").text(resultSet.data);    
			$("#alertError").show();   
		} 

	} else if (status == "error")  
	{   
		$("#alertError").text("Error while saving.");   
		$("#alertError").show();  
	} else  
	{   
		$("#alertError").text("Unknown error while saving..");   
		$("#alertError").show();  
	} 

	$("#hidLabIDSave").val("");  
	$("#formLaboratory")[0].reset(); 
} 
 
// UPDATE========================================== 
$(document).on("click", ".btnUpdate", function(event) 
{     
	$("#hidLabIDSave").val($(this).closest("tr").find('#hidLabIDUpdate').val());     
	$("#laboratoryCode").val($(this).closest("tr").find('td:eq(0)').text());     
	$("#name").val($(this).closest("tr").find('td:eq(1)').text());     
	$("#address").val($(this).closest("tr").find('td:eq(2)').text());     
	$("#telNo").val($(this).closest("tr").find('td:eq(3)').text()); 
	$("#city").val($(this).closest("tr").find('td:eq(4)').text()); 
}); 

//REMOVE===========================================
$(document).on("click", ".btnRemove", function(event) 
{  
	$.ajax(  
	{   
		url : "LaboratoryAPI",   
		type : "DELETE",   
		data : "labID=" + $(this).data("labid"),   
		dataType : "text",   
		complete : function(response, status)   
		{    
			onItemDeleteComplete(response.responseText, status);   
		}  
	}); 
}); 

function onItemDeleteComplete(response, status) 
{  
	if (status == "success")  
	{   
		var resultSet = JSON.parse(response); 

		if (resultSet.status.trim() == "success")   
		{    
			$("#alertSuccess").text("Successfully deleted.");    
			$("#alertSuccess").show(); 
		
			$("#divLabsGrid").html(resultSet.data);   
		} else if (resultSet.status.trim() == "error")   
		{    
			$("#alertError").text(resultSet.data);    
			$("#alertError").show();   
		}

	} else if (status == "error")  
	{   
		$("#alertError").text("Error while deleting.");   
		$("#alertError").show();  
	} else  
	{   
		$("#alertError").text("Unknown error while deleting..");   
		$("#alertError").show();  
	}
}
 
// CLIENT-MODEL========================================================================= 
function validateItemForm() 
{  
	// c  
	if ($("#laboratoryCode").val().trim() == "")  
	{   
		return "Insert Laboratory Code.";  
	} 
 
	// Name  
	if ($("#name").val().trim() == "")  
	{   
		return "Insert Laboratory Name.";  
	} 
	//Address-------------------------------  
	if ($("#address").val().trim() == "")  
	{   
		return "Insert Laboratory Address.";  
	} 

	// is a valid telephone number  
	var tmpPrice = $("#telNo").val().trim();  
	if (!$.isNumeric(tmpPrice))  
	{   
		return "Insert a valid number for telephone number.";  
	} 


	// City------------------------  
	if ($("#city").val().trim() == "")  
	{   
		return "Insert a City.";  
	} 

	return true; 
}