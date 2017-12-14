package networking;

import java.io.*;
import java.net.*;
import java.util.*;

public class Host {
	private static class Client implements Runnable {
		private Thread thread = new Thread(this, "");
		public boolean connected = false;
		
		public Client() {
			thread.start();
		}
		
		public void run() {
			try {
				Socket socket = Host.socket.accept();
				Scanner in = new Scanner(new InputStreamReader(socket.getInputStream()));
				PrintStream out = new PrintStream(socket.getOutputStream());
				connected = true;
				connect(socket, in, out);
				out.println( "quiting server" );
			
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		private void connect(Socket socket, Scanner in, PrintStream out) {
			while ( Host.active ) {
				if ( in.hasNext() ) {
					String inpt = in.next();
					if (inpt.equals("quit")) { 
						Host.active = false;
						break;
					}
					out.println( inpt );
				}
			}
		}
	}
	
	public static boolean active = true;
	public static ServerSocket socket;
	
	public static void main(String[] args) throws IOException {
		socket = new ServerSocket(6792);
		Client current = new Client();
		
		while ( active ) {
			if ( current.connected ) {
				current = new Client();
			}
		}

		socket.close();
	}
}