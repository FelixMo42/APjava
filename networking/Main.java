package networking;

import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
        String[] data = Info.request("http://www.mosegames.com/school.php", "GET", Info.getWebIp() ).split("[\\r\\n]+");
		BufferedReader user = Client.input;
		
		System.out.print("Enter user name: ");
		String name = user.readLine();
		
		Client.ip = data[0];
		Client.port = Integer.parseInt( data[1] );
		Client.inFunc = (String s) -> { return name + ": " + s; };
		Client.enterText = "==~ " + name + " has joined the chat.";
		Client.exitText = "==~ " + name + " has left the chat.";
		
		if ( !Info.portOpen(Client.ip, Client.port) ) {
			Host.run();
			Client.ip = Host.ip;
			Client.port = Host.port;
		}
		
		Client.run();
		
		Host.close();
	}
}