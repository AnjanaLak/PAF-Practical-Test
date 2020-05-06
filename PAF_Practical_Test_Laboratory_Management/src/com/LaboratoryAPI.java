package com;

import com.Laboratory;
import java.io.IOException; 
import java.util.HashMap; 
import java.util.Map; 
import java.util.Scanner;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/LaboratoryAPI")
public class LaboratoryAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	Laboratory labObj = new Laboratory();
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LaboratoryAPI() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String output = labObj.insertLab(request.getParameter("laboratoryCode"),      
				request.getParameter("name"),     
				request.getParameter("address"),       
				request.getParameter("telNo"),
				request.getParameter("city")); 
	 
	 response.getWriter().write(output); 
		
		
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		Map paras = getParasMap(request); 
		 
		 String output = labObj.updateLab(paras.get("hidLabIDSave").toString(),   
				 paras.get("laboratoryCode").toString(),     
				 paras.get("name").toString(),        
				 paras.get("address").toString(), 
				 paras.get("telNo").toString(),
				 paras.get("city").toString());
		 
		 response.getWriter().write(output);
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		
		Map paras = getParasMap(request); 
		 
		 String output = labObj.deleteLab(paras.get("labID").toString()); 
		 
		 response.getWriter().write(output);
	}

	
	// Convert request parameters to a Map 
		private static Map getParasMap(HttpServletRequest request) 
		{  
			Map<String, String> map = new HashMap<String, String>();  
			try  
			{   
				Scanner scanner = new Scanner(request.getInputStream(), "UTF-8");   
				String queryString = scanner.hasNext() ?          
						scanner.useDelimiter("\\A").next() : "";  
						scanner.close(); 
		 
		  String[] params = queryString.split("&");   
		  for (String param : params)   
		  { 

	 
		   String[] p = param.split("=");    
		   map.put(p[0], p[1]);   
		   }  
		  }  
			catch (Exception e)  
			{  
				
			}  return map; 
			
		}
}
