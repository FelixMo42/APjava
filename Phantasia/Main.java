package phantasia;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Chatbot bot = new Chatbot();
	
		System.out.println(bot.greeting);
		Scanner in = new Scanner (System.in);
		String statement = in.nextLine();

		while (!statement.equals("Bye")) {
			System.out.println (bot.getResponse(statement));
			statement = in.nextLine();
		}
		
		in.close();
	}
}