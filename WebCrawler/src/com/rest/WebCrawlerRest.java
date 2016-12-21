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
 
		String returnStr = "CCCC";		 
		System.out.println("Input URL >> "+url);
 		/*StringBuilder sb = new StringBuilder();
		URLConnection urlConn = null;
		InputStreamReader in = null;*/

		try {
		 
			 Connection connection = Jsoup.connect(url);
				connection.timeout(20000);
				Document doc = connection.get();
				//System.out.println(doc);		 
				 
				Elements questions = doc.select("a[href]");
				for(Element link: questions){
					System.out.println("All Sub URLS----:  " +link);
						 
				}
			
			/*URL url1 = new URL(url);
			urlConn = url1.openConnection();
			if (urlConn != null)
				urlConn.setReadTimeout(60 * 1000);
			if (urlConn != null && urlConn.getInputStream() != null) {
				in = new InputStreamReader(urlConn.getInputStream(),
						Charset.defaultCharset());
				BufferedReader bufferedReader = new BufferedReader(in);
				if (bufferedReader != null) {
					int cp;
					while ((cp = bufferedReader.read()) != -1) {
						System.out.println(((char) cp);
					}
					bufferedReader.close();
				}
			}
		in.close();
*/
	             
	            
			
			
			
			 
		} catch (Exception e) {
			e.printStackTrace();
		}
		return returnStr;
	}

}
