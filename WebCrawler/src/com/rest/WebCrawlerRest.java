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
	@Produces(MediaType.TEXT_HTML)
	public String WCRESTService(String url) {
 
		  
		System.out.println("Input URL >> "+url);
 		/*StringBuilder sb = new StringBuilder();
		URLConnection urlConn = null;
		InputStreamReader in = null;*/
		String urlValue = "";
		String arr[] = url.split("://");
		System.out.println(">>>>"+arr[1]);
		String domineStr = arr[1];
		 StringBuilder JSON =new StringBuilder();
		try {
		 
			Connection connection = Jsoup.connect(url);
			connection.timeout(20000);
			Document doc = connection.get();
			Elements questions = doc.select("a");			
			JSON.append("{'urllist': [");			
			for(Element link: questions){				 
				urlValue = link.attr("href");
				if(urlValue.indexOf(domineStr) >= 0){
					//System.out.println("11111111 :  " +link.attr("href") );	
					 JSON.append("{'DOM':'NATIVE','VALUE':"+urlValue+"}");
				}else{					
					//System.out.println("22222222 :  " +link.attr("href") );
					JSON.append("{'DOM':'OTHER','VALUE':'"+urlValue+"'}");
				} 
				JSON.append(",");
			}			
		    JSON.append("]}");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JSON.toString();
	}

}
