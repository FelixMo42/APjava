package networking;

import java.io.*;
import java.net.*;
import java.util.*;

public class Client {
	public static void main(String[] args) throws UnknownHostException, IOException {
		String inpt;
		
		Scanner user = new Scanner(System.in);
		
		Socket socket = new Socket("localhost", 6792);
		PrintStream out = new PrintStream(socket.getOutputStream());
		Scanner in = new Scanner( new InputStreamReader(socket.getInputStream()) );
		
		System.out.println("connected");
		
		while (true) {
			if ( user.hasNext() ) {
				inpt = user.next();
				out.println(inpt);
				System.out.println("sent");
				if ( inpt.equals("quit") ) {
					break;
				}
			}
			if ( in.hasNext() ) {
				System.out.println( in.nextLine() );
			}
		}
		in.close();
		user.close();
		socket.close();
	}
}