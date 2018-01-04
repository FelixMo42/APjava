package networking;

import java.io.*;
import java.util.*;

public class Main {
	static String name = "";
	
	public static void main(String[] args) {
		//if not server
			//create server
			//tell every one
		//else
			//connect to it
		
		Scanner user = new Scanner( System.in );
		
		System.out.print("Enter user name: ");
		name = user.nextLine();
		
		user.close();
		
		Client.inFunc = (String s) -> { return name + ": " + s; };
		Client.enterText = "==~ " + name + " has joined the chat.";
		Client.exitText = "==~ " + name + " has left the chat.";
		
		Client.run();
	}
}