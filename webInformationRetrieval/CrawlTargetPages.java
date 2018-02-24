package webInformationRetrieval;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class CrawlTargetPages {

	public static void main(String[] args) throws Exception {
		
		 JSONParser parser = new JSONParser();

		 JSONArray a = (JSONArray) parser.parse(new FileReader("SE_BING_1_2016280141.json"));

		 //System.out.println(a);
		 
		 int i=1;
		 
		 for (Object o : a)
		 {
		    JSONObject link = (JSONObject) o;

		    String url = (String) link.get("url");
		    System.out.println(url);
		    
		    Document doc = Jsoup.connect(url).userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/33.0.1750.152 Safari/537.36").get();
		    //Thread.sleep(3000);

		    
		    try (FileWriter file = new FileWriter("CD_BING_1_"+i+"_2016280141.html")) {
		    	file.write(url+"\n\n");
	            file.write(doc.toString());
	            file.flush();

	        } catch (IOException e) {
	            e.printStackTrace();
	        }

		    i++;
		 }
	}

}
