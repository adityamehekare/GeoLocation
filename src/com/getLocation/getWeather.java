package com.getLocation;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.getLocation.serivces.openWeatherMapServices;

public class getWeather extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public getWeather() {
        super();
        // TODO Auto-generated constructor stub
    }
/*
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
*/
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String longitude = (String) request.getParameter("longitude");
		String latitude = (String) request.getParameter("latitude");
		//System.out.println("in log--- "+ longitude );
		//System.out.println("in lati--- "+latitude  );
		//String longitude = "77.75";
		//String latitude = "20.93333";
		
		openWeatherMapServices getData = new openWeatherMapServices();
		Map<String,String> data = getData.getWeather(longitude, latitude);
		//System.out.println(data.get("temp"));
		request.setAttribute("longitude", longitude);
		request.setAttribute("latitude", latitude);
		request.setAttribute("temp", data.get("temp"));
		request.setAttribute("feels_like", data.get("feels_like"));
		request.setAttribute("temp_min", data.get("temp_min"));
		request.setAttribute("temp_max", data.get("temp_max"));
		request.setAttribute("pressure", data.get("pressure"));
		request.setAttribute("humidity", data.get("humidity"));
		
		
		
		//response.sendRedirect("coordinates.jsp");
		request.getRequestDispatcher("weather.jsp").forward(request,response);
		
	
	}

}
