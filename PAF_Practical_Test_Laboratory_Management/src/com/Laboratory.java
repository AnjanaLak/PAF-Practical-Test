package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Laboratory {

	private Connection connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");

			con = DriverManager.getConnection("jdbc:mysql://localhost:3308/labdb?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "root");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

	public String insertLab(String laboratoryCode, String name, String address, String telNo, String city)  
	{   
		String output = ""; 
	 
		try   
		{    
			Connection con = connect(); 
	 
			if (con == null)    
			{return "Error while connecting to the database for inserting."; } 
	 
			// create a prepared statement    
			String query = " insert into laboratory(`idlaboratory`,`laboratoryCode`,`name`,`address`,`telNo`, `city`)" + " values (?, ?, ?, ?, ?,?)"; 
	 
			PreparedStatement preparedStmt = con.prepareStatement(query); 
	 
			// binding values    
			preparedStmt.setInt(1, 0);    
			preparedStmt.setString(2, laboratoryCode);    
			preparedStmt.setString(3, name);    
			preparedStmt.setString(4, address);    
			preparedStmt.setInt(5, Integer.parseInt(telNo));
			preparedStmt.setString(6, city);    

			
			// execute the statement    
			preparedStmt.execute();    
			con.close(); 
	   
			String newLabs = readLabs(); 
			output =  "{\"status\":\"success\", \"data\": \"" +        
					newLabs + "\"}";    
		}   
		catch (Exception e)   
		{    
			output =  "{\"status\":\"error\", \"data\": \"Error while inserting the item.\"}";  
			System.err.println(e.getMessage());   
		} 
		
	  return output;  
	} 
	
	public String readLabs()  
	{   
		String output = ""; 
	
		try   
		{    
			Connection con = connect(); 
		
			if (con == null)    
			{
				return "Error while connecting to the database for reading."; 
			} 
	 
			// Prepare the html table to be displayed    
			output = "<table border=\'1\'><tr><th>Laboratory Code</th><th>Laboratory Name</th><th>Lab Address</th><th>Tel No</th><th>City</th><th>Update</th><th>Remove</th></tr>"; 
	 
			String query = "select * from laboratory";    
			Statement stmt = con.createStatement();    
			ResultSet rs = stmt.executeQuery(query); 
	 
			// iterate through the rows in the result set    
			while (rs.next())    
			{     
				String labID = Integer.toString(rs.getInt("idLaboratory"));     
				String labCode = rs.getString("laboratoryCode");     
				String labName = rs.getString("name");  
				String labAddress = rs.getString("address"); 
				String labTel = Integer.toString(rs.getInt("telNo"));     
				String labCity = rs.getString("city"); 
			
	 
				// Add into the html table 
				output += "<tr><td><input id=\'hidLabIDUpdate\' name=\'hidLabIDUpdate\' type=\'hidden\' value=\'" + labID + "'>" 
							+ labCode + "</td>";      
				output += "<td>" + labName + "</td>";     
				output += "<td>" + labAddress + "</td>";     
				output += "<td>" + labTel + "</td>"; 
				output += "<td>" + labCity + "</td>"; 

	 
				output +="<td><input name='btnUpdate' type='button' value='Update' class='btnUpdate btn btn-secondary'></td>"       
							+ "<td><input name='btnRemove' type='button' value='Remove' class='btnRemove btn btn-danger' data-labid='" + labID + "'>" + "</td></tr>"; 
			} 
	 
			con.close(); 
	 
			// Complete the html table    
			output += "</table>";   
		}   
		catch (Exception e)   
		{    
			output = "Error while reading the Laboratories.";    
			System.err.println(e.getMessage());   
		} 
	 
		return output;  
	}
	 
	
	public String updateLab(String idLaboratory, String laboratoryCode, String name, String address, String telNo, String city)  
	{   
		String output = ""; 
	 
		try   
		{    
			Connection con = connect(); 
	 
			if (con == null)    
			{return "Error while connecting to the database for updating."; } 
	 
			// create a prepared statement    
			String query = "UPDATE laboratory SET laboratoryCode=?,name=?,address=?,telNo=?,city=? WHERE idLaboratory=?"; 
	 
			PreparedStatement preparedStmt = con.prepareStatement(query); 
	 
			// binding values    
			preparedStmt.setString(1, laboratoryCode);    
			preparedStmt.setString(2, name);  
			preparedStmt.setString(3, address);  
			preparedStmt.setInt(4, Integer.parseInt(telNo));    
			preparedStmt.setString(5, city);    
			preparedStmt.setInt(6, Integer.parseInt(idLaboratory)); 
	 
			// execute the statement    
			preparedStmt.execute();    
			con.close(); 
	 
			String newLabs = readLabs();    
			output = "{\"status\":\"success\", \"data\": \"" +        
					newLabs + "\"}";    
		}   
		catch (Exception e)   
		{    
			output =  "{\"status\":\"error\", \"data\": \"Error while updating the Laboratory.\"}";   
			System.err.println(e.getMessage());   
		} 
	 
	  return output;  
	} 
	
	public String deleteLab(String idLaboratory)  
	{   
		String output = ""; 
	 
		try   
		{    
			Connection con = connect(); 
	 
			if (con == null)    
			{return "Error while connecting to the database for deleting."; } 
	 
			// create a prepared statement    
			String query = "delete from laboratory where idLaboratory=?"; 
	 
			PreparedStatement preparedStmt = con.prepareStatement(query); 
	 
			// binding values    
			preparedStmt.setInt(1, Integer.parseInt(idLaboratory)); 
	 
			// execute the statement    
			preparedStmt.execute();    
			con.close(); 
	 
			String newLabs = readLabs();    
			output = "{\"status\":\"success\", \"data\": \"" +       
					newLabs + "\"}";    
		}   
		catch (Exception e)   
		{    
			output = "Error while deleting the Laboratory.";    
			System.err.println(e.getMessage());   
		} 
	 
		return output;  
	}
	 
	 
}



