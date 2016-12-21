package com.rest;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;





@Path("/")
public class WebCrawlerRest { 
	
	@POST
	@Path("/StartCrawle")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_HTML)
	public String loginRESTService(String url) {
 
		String returnStr = "CCCC";		 
		System.out.println("Input URL >> "+url);
		try {
			 Document doc = Jsoup.connect("http://www.google.com/").get();			 
				if(doc.text().contains("research")){
					System.out.println("---"+doc.text());
				}
			 
		} catch (Exception e) {
			e.printStackTrace();
		}
		return returnStr;
	}

}
