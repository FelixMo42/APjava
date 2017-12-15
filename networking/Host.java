package networking;

import java.io.*;
import java.net.*;
import java.util.*;

public class Host {
	private static class Client implements Runnable {
		private Thread thread = new Thread(this, "");
		
		public Client() {
			thread.start();
		}
		
		public void run() {
			try {
				Socket socket = Host.socket.accept();
				Scanner in = new Scanner(new InputStreamReader(socket.getInputStream()));
				PrintStream out = new PrintStream(socket.getOutputStream());
				Host.next = true;
				Host.connect(socket, in, out);
				in.close();
				out.println( "quiting server" );
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		public void close() {
			thread.interrupt();
		}
	}
	
	public volatile static boolean active = true;
	public volatile static boolean next = true;
	public volatile static ServerSocket socket;
	public volatile static ArrayList<String> log;
	
	public static void main(String[] args) throws IOException {
		log = new ArrayList<String>();
		socket = new ServerSocket(6792);
		ArrayList<Client> connections = new ArrayList<Client>();
		
		while ( active ) {
			if ( next ) {
				connections.add( new Client() );
				next = false;
			}
		}

		for (Client c : connections) {
			c.close();
		}
		
		socket.close();
		System.out.println( "server closed" );
	}
	
	private static void connect(Socket socket, Scanner in, PrintStream out) {
		out.println( "==~ Welcom to ChaterBox!" );
		int pos = 0;
		while ( active ) {
			if ( in.hasNext() ) {
				String inpt = in.nextLine();
				if (inpt.equals("quit")) { break; }
				log.add( inpt );
				pos++;
			}
			if ( log.size() > pos ) {
				out.println( log.get(pos) );
				pos++;
			}
		}
	}
}