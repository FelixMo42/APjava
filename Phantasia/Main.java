package Phantasia;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Chatbot maggie = new Chatbot();
	
		System.out.println (maggie.greating);
		Scanner in = new Scanner (System.in);
		String statement = in.nextLine();
	
		while (!statement.equals("Bye")) {
			System.out.println (maggie.getResponse(statement));
			statement = in.nextLine();
		}
		
		in.close();
	}
}