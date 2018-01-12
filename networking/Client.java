package networking;

import java.io.*;
import java.net.*;

public class Client {
	interface func {
		String call(String s);
	}
	
	public static BufferedReader input = new BufferedReader( new InputStreamReader(System.in) );
	public static func inFunc = (String s) -> { return s; };
	public static func outFunc = (String s) -> { return s; };
	public static String enterText = "";
	public static String exitText = "";
	public static String status = "n";
	public static String ip = "10.137.41.82";
	public static int port = 6792;
	
	private static Socket socket;
	private static PrintStream out;
	private static BufferedReader in;
	
	public static void main(String[] args) throws IOException  {
		socket = new Socket(ip, port);
		out = new PrintStream(socket.getOutputStream());
		in = new BufferedReader( new InputStreamReader(socket.getInputStream()) );
		
		out.println( status );
		
		if ( !enterText.equals("") ) {
			out.println(enterText);
		}
		
		while (true) {
			if ( input.ready() ) {
				String inpt = input.readLine();
				if ( inpt.startsWith("\\") ) {
					//quit
					if ( inpt.equals("\\quit") ) {
						if ( !exitText.equals("") ) {
							out.println(exitText);
						}
						out.println(inpt);
						break;
					}
				} else {
					print( inpt );
				}
			}
			if ( in.ready() ) {
				System.out.println( outFunc.call( in.readLine() ) );
			}
		}
		
		in.close();
		input.close();
		socket.close();
	}
	
	public static void run() {
		try {
			main( new String[0] );
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void print(String msg) {
		out.println( inFunc.call(msg) );
	}
}