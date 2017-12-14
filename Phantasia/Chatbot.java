package phantasia;

import java.io.*;
import java.util.*;
import java.util.Map.Entry;

import org.json.*;

public class Chatbot {
	public String greeting = "Welcom to Phantasia the RP chat bot.";
	public Map<String, String> info;
	
	private JSONObject data;
	
	public Chatbot(String path) {
		try {
			init(path);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public Chatbot() {
		try {
			init("phantasia/data.json");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void init(String path) throws FileNotFoundException {
		Scanner scanner;
		scanner = new Scanner(new File("src/phantasia/data.json"), "UTF-8");
		data = new JSONObject( scanner.useDelimiter("\\A").next() );
		scanner.close();
		info = new HashMap<String, String>();
	}
	
	public String getResponse(String inpt) {
		inpt = "_ " + format( inpt.replaceAll("[.,'\"!?]" , " ").toLowerCase() );
		
		JSONObject response = data;
		
		MAIN_LOOP:
		while (true) {	
			inpt = inpt.replaceFirst("[ ]*[^ ]*[ ]*", "");
			
			JSONArray keys = response.getJSONArray("keys");
			
			for (int i = 0; i < keys.length(); i++) {
				if ( is( keys , i , inpt ) ) {
					response = keys.getJSONObject(i);
					continue MAIN_LOOP;
				}
			}
			
			break;
		}
		
		return response( response );
	}
	
	public String response(JSONObject response) {
		return response.getString( "response" );
	}
	
	public boolean is(JSONArray keys, int i, String inpt) {
		return inpt.matches( keys.getJSONObject(i).getString("is") + ".*" );
	}

	public String format(String str) {
		Iterator<Entry<String, String>> it = info.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<String, String> pair = (Map.Entry<String, String>)it.next();
			str.replace("#" + pair.getKey() , pair.getValue());
			it.remove();
		}
		return str;
	}
}