package com.rest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;





@Path("/")
public class WebCrawlerRest { 
	
	@POST
	@Path("/StartCrawle")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public JSONArray WCRESTService(String url) {
 
		  
		System.out.println("Input URL >> "+url);
		String urlValue = "";
		String arr[] = null;
		String error = null;
		String domineStr = "";
		if(url.indexOf("http://www.") >= 0){                  // for url type http://wiprodigital.com
			arr = url.split("://www.");	
			domineStr = arr[1]; 
		}else if(url.indexOf("http://") >=0){         // for url type http://www.wiprodigital.com
			 arr = url.split("://");	
			 domineStr = arr[1]; 
		}else if(url.indexOf("https://") >= 0){           // for url type https://wiprodigital.com
			arr = url.split("s://");
			domineStr = arr[1]; 
		}else if(url.indexOf("https://www.") >=0){          // for url type https://www.wiprodigital.com
			arr = url.split("s://www");
			domineStr = arr[1]; 
		}else if(url.indexOf("www.") >=0){                // for url type www.wiprodigital.com
			/*arr = url.split("\\.");
			domineStr = arr[1]; */
			error = "Please enter valid Url with HTTP";
		}else if(url.indexOf(".com") >=0){                // for url type wiprodigital.com
			/*arr = url.split("\\.");
			domineStr = arr[0]; */
			error = "Please enter valid Url with HTTP";
		}else{
			error = "Please enter valid Url  with HTTP";
		}
		 
		System.out.println(">>>>"+arr);
		  
		  
		 JSONObject obj = null;
		 
		 JSONArray list = new JSONArray();
		 
		 if(error == null){
		 
		 
				try { 
					Connection connection = Jsoup.connect(url);
					connection.timeout(1000*6);
					Document doc = connection.get();
					Elements questions = doc.select("a");
					 
					for(Element link: questions){	 
						obj = new JSONObject();
						urlValue = link.attr("href");
						if(urlValue.indexOf(domineStr) >= 0){
							System.out.println("11111111 :  " +urlValue );					   
							obj.put("DOM","NATIVE");
							obj.put("VALUE",urlValue);
							
							list.add(obj); 
						}else if((urlValue.indexOf("http://") >= 0 || urlValue.indexOf("https://") >= 0)&& urlValue.indexOf(domineStr) < 0){					
							System.out.println("22222222 :  " +urlValue);					  
							obj.put("DOM","OTHER");
							obj.put("VALUE",urlValue);
							list.add(obj); 
						} 
					}  
						
					/*}else{
						obj.put("ERROR",error);				 
					}			
				   */ 
				   System.out.println("Final : "+list);
				} catch (Exception e) {
					obj = new JSONObject();
					obj.put("DOM","ERROR");	
					obj.put("VALUE","Unknown Host Exception Occured. Please enter valid Url");
					list.add(obj); 
					e.printStackTrace();
					
				}
		 }else{
			    obj = new JSONObject();
				obj.put("DOM","ERROR");		
				obj.put("VALUE",error);		
				list.add(obj); 
			}
		
		
		return list;
		 
	}

}
