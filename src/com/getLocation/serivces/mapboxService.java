package com.getLocation.serivces;

import java.io.BufferedReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;

import javax.servlet.http.HttpServlet;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;



public class mapboxService extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static String accessToken = "pk.eyJ1IjoiYXNtZWhla2FyZSIs"
			+ "ImEiOiJja2JjOGcwczcwNHFlMnRueWI2djNqZzJyIn0.nK7"
			+ "VyaV8Oa6svhVt_szVrQ";
	
	private static String baseURL = "https://api.mapbox.com/geocoding/v5"
			+ "/mapbox.places/";  
   
    
    public mapboxService() {
        super();
        // TODO Auto-generated constructor stub
    }
    
   
    
	public String getLocationJson(String cityName) {
		  String cityURL="";
		  String result="";
		  String URLwithCity="" ;
		try {
            if(cityName!=null && cityName!="") {
            	//System.out.println("in mcityname");
            	URLwithCity = (String)baseURL+URLEncoder.encode(cityName, "utf-8");
            	URLwithCity = URLwithCity + ".json?limit=1&access_token=";
                cityURL = URLwithCity + URLEncoder.encode(accessToken,"utf-8");
            }
        } catch (UnsupportedEncodingException e1) {               
            e1.printStackTrace();                     
        }  
		
		StringBuilder strBuf = new StringBuilder();  
        
        HttpURLConnection conn=null;
        BufferedReader reader=null;
        try{  
            //Declare the connection to weather api urltln
        	//System.out.println("in maapping");
            URL url = new URL(cityURL);  
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
                		
                		JsonArray arr = (JsonArray) obj.getAsJsonArray("features") ;
                		JsonObject ele = arr.get(0).getAsJsonObject();
                		
            			result = ele.get("geometry").getAsJsonObject().get("coordinates").toString();
                		
                		
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

        return result;  
   }
}
