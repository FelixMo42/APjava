package networking;

import java.io.*;
import java.net.*;
import java.util.*;

public class Client {
	public static void main(String[] args) throws UnknownHostException, IOException {
		String inpt;
		String outpt;
		
		Scanner console = new Scanner(System.in);
		
		Socket clientSocket = new Socket("localhost", 6792);
		DataOutputStream out = new DataOutputStream(clientSocket.getOutputStream());
		Scanner in = new Scanner( new InputStreamReader(clientSocket.getInputStream()) );
		
		while (true) {
			inpt = console.next();
			out.writeBytes(inpt + '\n');
			outpt = in.next();
			System.out.println("FROM SERVER: " + outpt);
			if ( inpt.equals("quit") ) {
				break;
			}
		}
		
		in.close();
		console.close();
		clientSocket.close();
	}
}