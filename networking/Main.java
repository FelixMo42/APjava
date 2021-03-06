package networking;

import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader user = Client.input;
		
		System.out.print("Enter user name: ");
		String name = user.readLine();
		
		Client.inFunc = (String s) -> { return name + ": " + s; };
		Client.enterText = "==~ " + name + " has joined the chat.";
		Client.exitText = "==~ " + name + " has left the chat.";
		
		run();
		
		Host.close();
		user.close();
	}
	
	public static void host() {
		Host.run();
		Client.ip = Host.ip;
		Client.port = Host.port;
		Info.request("http://www.mosegames.com/school.php", "PUT", Info.getWebIp() + "\n" + Host.ip + "\n" + Host.port);
		connect();
	}
	
	public static void run() {
		String[] data = Info.request("http://www.mosegames.com/school.php", "GET", Info.getWebIp() ).split("[\\r\\n]+");
		Client.ip = data[0];
		Client.port = Integer.parseInt( data[1] );
		
		if ( !Info.portOpen(Client.ip, Client.port) ) {
			host();
		} else {
			connect();
		}
	}
	
	public static void connect() {
		if ( Client.socket != null && Client.socket.isConnected() ) {
			Client.out.println("\\quit");
			try {
				Client.in.close();
				Client.out.close();
				Client.socket.close();
			} catch (IOException e) { e.printStackTrace(); }
		}
		Client.run();
	}
}