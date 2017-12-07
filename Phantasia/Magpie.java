package phantasia;
public class Magpie {
	final String greeting = "Hello, let's talk.";
	
	private int findKeyword(String statement, String goal, int startPos) {
		String phrase = statement.trim();
		int psn = phrase.toLowerCase().indexOf(goal.toLowerCase(), startPos);
		while (psn >= 0) {
			String before = " ", after = " ";
			if (psn > 0) {
				before = phrase.substring (psn - 1, psn).toLowerCase();
			}
			if (psn + goal.length() < phrase.length()) {
				after = phrase.substring(psn + goal.length(), psn + goal.length() + 1).toLowerCase();
			}
			/* determine the values of psn, before, and after at this point in the method. */
			if ( (before.compareTo("a") < 0 || before.compareTo("z") > 0) && (after.compareTo("a") < 0 || after.compareTo("z") > 0) ) {
				return psn;
			}
			psn = phrase.indexOf(goal.toLowerCase(), psn + 1);
		}
		return -1;
	}
	
	private int findKeyword(String statement, String goal) {
		return findKeyword(statement, goal , 0);
	}
	
	private String getSomething(String statement, String start) {
		statement = statement.replaceAll("[.,'\"!?]" , " ");
		int s = findKeyword(statement, start, 0) + start.length();
		return statement.substring(s == -1 ? 0 : s, statement.length()).trim();
	}
	
	private String getSomething(String statement, String start, String end) {
		statement = statement.replaceAll("[.,'\"!?]" , " ");
		int s = findKeyword(statement, start, 0) + start.length();
		int e = findKeyword(statement, end, 0);
		return statement.substring(s == -1 ? 0 : s, e == -1 ? statement.length() : e).trim();
	}
	
	public String getResponse(String statement) {
		if (statement.trim().length() == 0) {
			return "Say something, please.";
		} else if ( findKeyword(statement,"no") > 0 ) {
			return "Why so negative? :-(";
		} else if ( findKeyword(statement,"yes") >= 0) {
			return "Yay! :-D";
		} else if ( findKeyword(statement,"maybe") >= 0) {
			return "Oh really? Pick a side! :-?";
		} else if ( findKeyword(statement,"exist") >= 0) {
			return "I exist for however long you want me to. ;-)";
		} else if ( findKeyword(statement,"name") >= 0) {
			return "Nice to meet you! I'm Magpie. ;-)";
		} else if ( findKeyword(statement,"nuzzle") >= 0) {
			return "*nuzzles back* :-B";
		} else if ( findKeyword(statement,"want") >= 0 && findKeyword(statement,"to") >= 0 ) {
			return "Would you really be happy if you were " + getSomething(statement , "want").replaceAll("your", "my") + "?";
		} else if ( findKeyword(statement,"want") >= 0) {
			return "Would you really be happy if you had " + getSomething(statement , "want").replaceAll("your", "my") + "?";
		} else if ( findKeyword(statement,"I") >= 0 && findKeyword(statement,"you") >= 0) {
			return "Why do you " + getSomething(statement , "I" , "you").replaceAll("your", "my") + " me?";
		} else if ( findKeyword(statement,"pet") >= 0 || statement.indexOf("cat") >= 0 || statement.indexOf("dog") >= 0) {
			return "Tell me more about your pets";
		} else if ( findKeyword(statement,"mother") >= 0 || findKeyword(statement,"father") >= 0 || findKeyword(statement,"mom") >= 0 || findKeyword(statement,"dad") >= 0 || findKeyword(statement,"sister") >= 0 || findKeyword(statement,"brother") >= 0) {
			return "Tell me more about your family. :-O";
		} else if ( findKeyword(statement,"Holston") >= 0 || findKeyword(statement,"Ira") >= 0) {
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
			return "Interesting";
		} else {
			return "Nothing to say?";
		}
	}
}