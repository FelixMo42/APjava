package phantasia;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import org.json.*;

public class Chatbot {
	public String greeting = "Welcom to Phantasia the RP chat bot.";
	
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
		String json = scanner.useDelimiter("\\A").next();
		scanner.close();
		
		data = new JSONObject(json);
	}
	
	public String getResponse(String inpt) {
		inpt = "_ " + inpt.replaceAll("[.,'\"!?]" , " ").toLowerCase();
		inpt = inpt.replaceFirst("[ ]*[^ ]*[ ]*", "");
		
		return inpt;
	}
}