package Phantasia;
public class Magpie {
	final String greeting = "Hello, let's talk.";
	
	public String getResponse(String statement) {
		if (statement.trim().length() == 0) {
			return "Say something, please.";
		} else if (statement.indexOf("no") >= 0) {
			return "Why so negative? :-(";
		} else if (statement.indexOf("yes") >= 0) {
			return "Yay! :-D";
		} else if (statement.indexOf("maybe") >= 0) {
			return "Oh really? Pick a side! :-?";
		} else if (statement.indexOf("exist") >= 0) {
			return "I exist for however long you want me to. ;-)";
		} else if (statement.indexOf("name") >= 0) {
			return "Nice to meet you! I'm Magpie. ;-)";
		} else if (statement.indexOf("nuzzle") >= 0) {
			return "*nuzzles back* :-B";
		} else if (statement.indexOf("pet") >= 0 || statement.indexOf("cat") >= 0 || statement.indexOf("dog") >= 0) {
			return "Tell me more about your pets";
		} else if (statement.indexOf("mother") >= 0 || statement.indexOf("father") >= 0 || statement.indexOf("mom") >= 0 || statement.indexOf("dad") >= 0 || statement.indexOf("sister") >= 0 || statement.indexOf("brother") >= 0) {
			return "Tell me more about your family. :-O";
		} else if (statement.indexOf("Holston") >= 0 || statement.indexOf("Ira") >= 0) {
			return "Please be nice to Mr. H";
		} else {
			return getRandomResponse();
		}
	}
	
	private String getRandomResponse() {
		int NUMBER_OF_RESPONSES = 2;
		int whichResponse = (int)(NUMBER_OF_RESPONSES * Math.random());
		
		if (whichResponse == 0) {
		return "Interesting, tell me more. :-O";
		} else if (whichResponse == 1) {
			return "Hi";
		} else {
			return "Nothing to say?";
		}
	}
}