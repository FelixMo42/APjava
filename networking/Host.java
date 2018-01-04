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
				BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
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
	
	public static void main(String[] args) {
		try {
			run();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void run() throws IOException {
		//get ip address
        InetAddress iAddress = InetAddress.getLocalHost();
        String server_IP = iAddress.getHostAddress();
        System.out.println("Server IP address : " + server_IP);
		//open every thing up
		BufferedReader user = new BufferedReader( new InputStreamReader(System.in) );
		log = new ArrayList<String>();
		socket = new ServerSocket(6792);
		ArrayList<Client> connections = new ArrayList<Client>();
		//main loop
		while ( active ) {
			if ( next ) {
				connections.add( new Client() );
				next = false;
			}
			if ( user.ready() ) {
				String inpt = user.readLine();
				if ( inpt.equals("close") ) {
					active = false;
				}
			}
		}
		//close every thing up
		for (Client c : connections) {
			c.close();
		}
		user.close();
		socket.close();
		System.out.println( "server closed" );
	}
	
	private static void connect(Socket socket, BufferedReader in, PrintStream out) throws IOException {
		out.println( "==~ Welcome to ChatterBox!" );
		int pos = 0;
		while ( active ) {
			if ( in.ready() ) {
				String inpt = in.readLine();
				if (inpt.equals("quit")) { break; }
				log.add( inpt );
				System.out.println( log.size() + ": " + inpt );
				pos++;
			}
			if ( log.size() > pos ) {
				out.println( log.get(pos) );
				pos++;
			}
		}
	}
}