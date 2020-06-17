package com.getLocation.serivces;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class openWeatherMapServices extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String appId = "1fc4880121ddaa0002f98233a446aaf2";
	
	private static String baseURL = "https://api.openweathermap.org/data/2.5/"
			+ "weather?units=metric&lat=";  
       
    public openWeatherMapServices() {
        super();
        // TODO Auto-generated constructor stub
    }
    public Map<String,String> getWeather(String longitude, String latitude) {
    	String URLWithCoordinates="";
    	String weatherURL="";
    	Map<String,String> resmap = new HashMap<String,String>();
    	try {
            if(longitude!=null && longitude!=""&&latitude!=null&&latitude!="") {
            	//System.out.println("in weather");
            URLWithCoordinates= (String)baseURL+URLEncoder.encode(latitude, "utf-8");
            URLWithCoordinates= URLWithCoordinates+ "&lon=";
            URLWithCoordinates= URLWithCoordinates+URLEncoder.encode(longitude, "utf-8");
            weatherURL = URLWithCoordinates+"&appid=" + URLEncoder.encode(appId,"utf-8");          
            }
            else
            	throw new IOException("Invalid Coordinates"); 
        } catch (UnsupportedEncodingException e1) {               
            e1.printStackTrace();                     
        } catch (IOException e) {
        	e.printStackTrace(); 
        }
    	
StringBuilder strBuf = new StringBuilder();  
        
        HttpURLConnection conn=null;
        BufferedReader reader=null;
        try{  
            //Declare the connection to weather api urltln
        	//System.out.println("in url connection "+ weatherURL );
            URL url = new URL(weatherURL);  
            conn = (HttpURLConnection)url.openConnection();  
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");
            reader = new BufferedReader(new InputStreamReader(conn.getInputStream(),"utf-8"));
            String output = null;  
            while ((output = reader.readLine()) != null)  
                strBuf.append(output); 
            
            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("HTTP GET Request Failed with Error code : "
                              + conn.getResponseCode());
            }
            else
            {
            	try {
            		//JsonObject obj = new JsonParser().parse(jsonString).getAsJsonObject();
                		JsonObject obj = new JsonParser().parse(strBuf.toString()).getAsJsonObject();
                		
                		
                		JsonObject ele =  obj.getAsJsonObject("main") ;
                		resmap.put("temp", ele.get("temp").toString());
                		resmap.put("feels_like", ele.get("feels_like").toString());
                		resmap.put("temp_min", ele.get("temp_min").toString());
                		resmap.put("temp_max", ele.get("temp_max").toString());
                		resmap.put("pressure", ele.get("pressure").toString());
                		resmap.put("humidity", ele.get("humidity").toString());
                		
                		
                		//result = obj1.get("center").toString();
            	     //JSONObject jsonObject = new JSONObject();
            	}catch (Exception e){
            	     e.printStackTrace();
            	}
            }
		
		
        }catch(MalformedURLException e) {  
            e.printStackTrace();   
        }catch(IOException e){  
            e.printStackTrace();   
        }
        finally {
        	
        	 if(reader!=null)
             {
                 try {
                     reader.close();
                 } catch (IOException e) {
                     e.printStackTrace();
                 }
             }
             if(conn!=null)
             {
                 conn.disconnect();
             }
        }

    	
    	
    	
    	
    	
    	
    	return resmap;
    }
}
