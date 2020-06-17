package com.getLocation;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.getLocation.serivces.mapboxService;


public class GetLocation extends HttpServlet {
	
	
	private static final long serialVersionUID = 1L;
       

	
	public GetLocation() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	
	
	
//	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	//}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String lname = request.getParameter("locationName");
		//System.out.println(lname);
		mapboxService getData = new mapboxService();
		String data = getData.getLocationJson(lname);
		
		String[] coordinates =  data.split("\\]")[0].split("\\[")[1].split(",");
		
		request.setAttribute("longitude", coordinates[0]);
		request.setAttribute("latitude", coordinates[1]);
		
		
		
		//response.sendRedirect("coordinates.jsp");
		request.getRequestDispatcher("coordinates.jsp").forward(request,response);
		//PrintWriter out = response.getWriter(); 
		//out.println("<p> Findind the location of "+lname +" </p>");
		
		//response.sendRedirect("Geo");
		
		
		
	
	}

}
