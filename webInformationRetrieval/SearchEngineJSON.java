package webInformationRetrieval;

import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.util.LinkedHashMap;
import java.util.Map;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class SearchEngineJSON {
	
	 public void processBingQuery(String url, int num){
		 try {
			Document doc = Jsoup.connect(url).userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/33.0.1750.152 Safari/537.36").get();
			Elements links = doc.select(".b_algo");
			
			String mainString = "[\n";
			
			int i = 1;
			
			for (Element link : links) {
				Map obj=new LinkedHashMap();
				
				Elements listElements = link.select("h2");
				
				//Elements url1 = title.select("a[href]");
				
				String pureUrl = listElements.select("a").first().attr("abs:href");
				
				String title = listElements.select("a").text();
				
				Elements Paragraphs = link.select("p");
				
				System.out.println("Rank: "+i);
				obj.put("rank", new Integer(i));
				
				System.out.println("Title: "+title);
				obj.put("title", title);
				
				System.out.println("Url: "+pureUrl);
				obj.put("url", pureUrl);
				
				System.out.println("textSnipet: "+Paragraphs.text());
				obj.put("textSnipet", Paragraphs.text());
			
				StringWriter out = new StringWriter();
				
				JSONValue.writeJSONString(obj, out);
				
				mainString = mainString+out.toString();
				
				if(i!=10)
				mainString = mainString+"\n,\n";
				
				System.out.println();
				i++;
					
			}
			
			mainString = mainString+ "]";
			
			jsonCreator(mainString,num, "BING");
			
			//System.out.println(doc);
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
	 }
	
	 public void jsonCreator(String text, int num, String Engine){
		 
		 if(Engine == "BING")
		 text = text.replace("\\", "");
		 
	        try (FileWriter file = new FileWriter("SE_"+Engine+"_"+num+"_2016280141.json")) {
	            file.write(text);
	            file.flush();

	        } catch (IOException e) {
	            e.printStackTrace();
	        }
		 
	 }
	
	 public static void main(String[] args) {
		
		 SearchEngineJSON obj = new SearchEngineJSON(); 
		 
		 obj.processBingQuery("http://cn.bing.com/search?q=make+pancake",1);

	 }

}
