package networking;

import java.io.*;
import java.net.*;

public class Client {
	interface func {
		String call(String s);
	}
	
	public static func inFunc = (String s) -> { return s; };
	public static func outFunc = (String s) -> { return s; };
	public static String enterText = "";
	public static String exitText = "";
	
	public static void main(String[] args)  {
		run();
	}
	
	public static void run() {
		try {
			code();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static void code() throws IOException {
		String inpt;
		
		BufferedReader user = new BufferedReader( new InputStreamReader(System.in) );
		
		Socket socket = new Socket("10.137.41.82", 6792);
		PrintStream out = new PrintStream(socket.getOutputStream());
		BufferedReader in = new BufferedReader( new InputStreamReader(socket.getInputStream()) );
		
		if ( !enterText.equals("") ) {
			out.println(enterText);
		}
		
		while (true) {
			if ( user.ready() ) {
				inpt = inFunc.call( user.readLine() );
				if ( inpt.equals("quit") ) {
					if ( !exitText.equals("") ) {
						out.println(exitText);
					}
					out.println(inpt);
					break;
				}
				out.println(inpt);
			}
			if ( in.ready() ) {
				System.out.println( outFunc.call( in.readLine() ) );
			}
		}
		
		in.close();
		user.close();
		socket.close();
	}
}