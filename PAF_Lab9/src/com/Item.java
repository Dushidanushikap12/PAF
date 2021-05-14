
package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Item {

	 
	 
	public Connection connect()
	{
	 Connection con = null;

	 try
	 {
	 Class.forName("com.mysql.jdbc.Driver");
	 con= DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/researcher", "root",  "Dushi321@");
	 //For testing

//	 System.out.print("Successfully connected");
	 }
	 catch(Exception e)
	 {
	 e.printStackTrace();
	 }

	 return con;
	}
	
	public String insertusers(String name, String email, String phone, String address)
	{
	 String output = "";
	try
	 {
	 Connection con = connect();
	 if (con == null)
	 {
	 return "Error while connecting to the database for inserting";
	 }
	 // create a prepared statement
	 String query = " insert into userss(`userID`,`userName`,`userEmail`,`userPhoneNumber`,`userAddress`)"
	 + " values (?, ?, ?, ?, ?)";
	 PreparedStatement preparedStmt = con.prepareStatement(query);
	 // binding values
	 preparedStmt.setInt(1, 0);
	 preparedStmt.setString(2, name);
	 preparedStmt.setString(3, email);
	 preparedStmt.setString(4, phone);
	 preparedStmt.setString(5, address); 

	
	 
	//execute the statement
	 preparedStmt.execute();
	 con.close();
	 String newItems = readusers();
	 output = "{\"status\":\"success\", \"data\": \"" +
	 newItems + "\"}"; 
	 }
	catch (Exception e)
	 {
		output = "{\"status\":\"error\", \"data\":\"Error while inserting the item.\"}";
				 System.err.println(e.getMessage());
	 }
	return output;
	}

	
	public String readusers()
	{
	 String output = "";
	try
	 {
	 Connection con = connect();
	 if (con == null)
	 {
	 return "Error while connecting to the database for reading.";
	 }
	 // Prepare the HTML table to be displayed
	 
//	 output = "<table border='1'><tr><th>User Name</th>"
//	 +"<th>User Email</th><th>Phone Number</th>"
//	 + "<th>User Address</th>"
//	 + "<th>Update</th><th>Remove</th></tr>";
	 
	 output = "<table class=\"table table-bordered\">\r\n"
	 		+ "  <thead>\r\n"
	 		+ "    <tr>\r\n"
	 		+ "      <th scope=\"col\">User Name</th>\r\n"
	 		+ "      <th scope=\"col\">User Email</th>\r\n"
	 		+ "      <th scope=\"col\">Phone Number</th>\r\n"
	 		+ "      <th scope=\"col\">User Address</th>\r\n"
	 		+ "      <th scope=\"col\" colspan=\"2\">Upadate/Delete</th>\r\n"
	 		+ "    </tr>\r\n"
	 		+ "  </thead>\r\n"
	 		+ "</table";
	 String query = "select * from users";
	 Statement stmt = con.createStatement();
	 ResultSet rs = stmt.executeQuery(query);
	 // iterate through the rows in the result set
	 while (rs.next())
	 {
	 String userID = Integer.toString(rs.getInt("userID"));
	 String userName = rs.getString("userName");
	 String userEmail = rs.getString("userEmail");
	 String userPhoneNumber = rs.getString("userPhoneNumber");
	 String userAddress = rs.getString("userAddress");
	 
	 // Add a row into the HTML table
	 output += "<tr><td><input id='hidItemIDUpdate'name='hidItemIDUpdate'type='hidden' value='" + userID + "'>"
			 		  + userName + "</td>";
	 output += "<td>" + userEmail + "</td>";
	 output += "<td>" + userPhoneNumber + "</td>"; 
	 output += "<td>" + userAddress + "</td>";
	 
	 // buttons
	 output += "<td>"
	 + "<input name='btnUpdate' "
     + " type='button' class='btnUpdate btn btn-outline-dark' value='Update'>"
	 + "<input name='userID' type='hidden' "
	 + " value='" + userID + "'>" + "</td>"
	 + "<td><form method='post' action='items.jsp'>"
	 + "<input name='btnRemove' "
	 + " type='submit' class='btn btn-outline-danger' value='Remove'>"
	 + "<input name='hidItemIDDelete' type='hidden' "
	 + " value='" + userID + "'>" + "</form></td></tr>";

	 }
	 con.close();
	 // Complete the html table
	 output += "</table>";
	 }
	catch (Exception e)
	 {
	 output = "Error while reading the users.";
	 System.err.println(e.getMessage());
	 }
	return output;
	}
	
	
	public String removeUser(String userID)
	{
	 String output = "";
	try
	 {
	 Connection con = connect();
	 if (con == null)
	 {
	 return "Error while connecting to the database";
	 }
	
	 Statement st=con.createStatement();
	// int i=
	 st.executeUpdate("DELETE FROM items WHERE userID="+userID);
	 con.close();
	 String newItems = readusers();
	 output = "{\"status\":\"success\", \"data\": \"" +
	 newItems + "\"}";

	 
	 }
	catch (Exception e)
	 {
		output = "{\"status\":\"error\", \"data\":\"Error while deleting the item.\"}";
				 System.err.println(e.getMessage());
	 }
	return output;
	}

	public String updateusers(String UserID,String name, String email, String phone, String address)
	{
	 String output = "";
	try
	 {
	 Connection con = connect();
	 if (con == null)
	 {
	 return "Error while connecting to the database";
	 }
	 // create a prepared statement
	 String query = "update items set userName=?,  userEmail=?, userAddress=?, userPhoneNumber=? where userID=?";
	 PreparedStatement preparedStmt = con.prepareStatement(query);
	 // binding values
	 
	 preparedStmt.setString(1, name);
	 preparedStmt.setString(2, email);
	 preparedStmt.setString(3, address);
	 preparedStmt.setString(4, phone); 
	
	 
	//execute the statement
	 preparedStmt.execute();
	 con.close();
	 String newItems = readusers();
	 output = "{\"status\":\"success\", \"data\": \"" +
	 newItems + "\"}"; 
	 }
	catch (Exception e)
	 {
		output = "{\"status\":\"error\", \"data\":\"Error while updating the item.\"}";
				 System.err.println(e.getMessage()); 
	 }
	return output;
	}


}

