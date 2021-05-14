
$(document).ready(function(){ 

if ($("#alertSuccess").text().trim() == "null" ) { 
 $("#alertSuccess").hide(); 
 } 
 $("#alertError").hide(); 
}); 





// SAVE ======================================
$(document).on("click", "#btnSave", function(event) { 
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
// If valid----------------------
 $("#formItem").submit(); 
}); 
// UPDATE=====================================
$(document).on("click", ".btnUpdate", function(event) 
{ 
 $("#hidItemIDSave").val($(this).closest("tr").find('#hidItemIDUpdate').val()); 
 $("#userName").val($(this).closest("tr").find('td:eq(0)').text()); 
 $("#userEmail").val($(this).closest("tr").find('td:eq(1)').text()); 
 $("#userPhoneNumber").val($(this).closest("tr").find('td:eq(2)').text()); 
 $("#userAddress").val($(this).closest("tr").find('td:eq(3)').text()); 
}); 
// CLIENT-MODEL========================================
function validateItemForm() 
{ 
// NAME
if ($("#userName").val().trim() == "") 
 { 
 return "Insert User Name."; 
 } 
// NAME
if ($("#useName").val().trim() == "") 
 { 
 return "Insert User Name."; 
 }
//EMAIL-------------------------------
if ($("#userEmail").val().trim() == "") 
 { 
 return "Insert User Phone."; 
 } 
 //Address------------------------------
if ($("#userAddress").val().trim() == "") 
 { 
 return "Insert User Address."; 
 }
 //Phone Number------------------------
if ($("#userPhoneNumber").val().trim() == "") 
 { 
 return "Insert Phone Number."; 
 }
 
 //SAVE-------------------------------
 $(document).on("click", "#btnSave", function(event)
{
// Clear alerts-------------------
 $("#alertSuccess").text("");
 $("#alertSuccess").hide();
 $("#alertError").text("");
 $("#alertError").hide();
// Form validation-----------------
var status = validateItemForm();
if (status != true)
 {
 $("#alertError").text(status);
 $("#alertError").show();
 return;
 }
// If valid------------------------
var type = ($("#hidItemIDSave").val() == "") ? "POST" : "PUT";
 $.ajax(
 {
 url : "ItemsAPI",
 type : type,
 data : $("#formItem").serialize(),
 dataType : "text",
 complete : function(response, status)
 {
 onItemSaveComplete(response.responseText, status);
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
		 $("#divItemsGrid").html(resultSet.data);
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
// DESCRIPTION------------------------
if ($("#itemDesc").val().trim() == "") 
 { 
 return "Insert Item Description."; 
 } 
return true; 
}
