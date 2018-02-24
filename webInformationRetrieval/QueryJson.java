package webInformationRetrieval;

import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.util.LinkedHashMap;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

public class QueryJson {

	 public static void main(String[] args) throws IOException {

	        Map obj=new LinkedHashMap();
	        
	        obj.put("queryNum", new Integer(1));
	        obj.put("query", "Make pancake");
	        obj.put("description", "Want to know how to cook pancake. All the steps involved and ingredients required for it");

	        Map obj1=new LinkedHashMap();
	        obj1.put("queryNum", new Integer(2));
	        obj1.put("query", "Liverpool upcoming matches");
	        obj1.put("description", "Want to know the upcoming matches of English Football club team Liverpool F.C");
	        
	        StringWriter out = new StringWriter();
	        StringWriter out1 = new StringWriter();
	        
	        JSONValue.writeJSONString(obj, out);
	        JSONValue.writeJSONString(obj, out1);
	        
	        try (FileWriter file = new FileWriter("QD_2016280141.json")) {

	        	file.write("[\n");
	            file.write(out.toString());
	            file.write("\n,\n");
	            file.write(out1.toString());
	            file.write("\n]");
	            file.flush();

	        } catch (IOException e) {
	            e.printStackTrace();
	        }

	        System.out.print(obj);

	    }
}
