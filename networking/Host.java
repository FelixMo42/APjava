package networking;

import java.io.*;
import java.net.*;
import java.util.*;

public class Host {
	private static class Client implements Runnable {
		public volatile Thread thread = new Thread(this, "Client");
		public volatile boolean occupy = false;
		public volatile Socket client;
		public volatile BufferedReader in;
		public volatile PrintStream out;
		
		public Client(Socket socket) {
			client = socket;
			thread.start();
		}
		
		public void run() {
			try {
				in = new BufferedReader(new InputStreamReader(client.getInputStream()));
				out = new PrintStream(client.getOutputStream());
				connect(this, in, out);
				in.close();
				out.println( "quiting server" );
				if ( Host.active ) {
					connections.remove(this);
				}
			} catch (IOException e) { e.printStackTrace(); }
		}
	}
	
	private static class Main implements Runnable {
		public void run() {
			try {
				while ( true ) {
					connections.add( new Client( socket.accept() ) );
				}
			} catch (IOException e) { e.printStackTrace(); }
		}
	}
	
	public volatile static boolean active = false;
	public static String ip;
	public static int port;
	
	private volatile static ArrayList<String> log;
	private static Thread mainLoop;
	private static ServerSocket socket;
	private static ArrayList<Client> connections = new ArrayList<Client>();
	
	public static void main(String[] args) throws IOException {
		active = true;
		log = new ArrayList<String>();
		socket = new ServerSocket(0);
		ip = Info.getLocalIp();
		port = socket.getLocalPort();
        mainLoop = new Thread(new Main(), "MainLoop");
        mainLoop.start();
	}
	
	public static void close() {
		if (!active) { return; }
		
		if (connections.size() >= 2) {
			connections.get(1).occupy = true;
			connections.get(1).out.println("\\host");
			try {
				connections.get(1).in.readLine();
			} catch (IOException e) { e.printStackTrace(); }
		}
		
		for (Client client : connections) {
			client.out.println("\\reconnect");
		}
		
		mainLoop.interrupt();
		
		active = false;
		
		for (Client client : connections) {
			try {
				client.thread.join();
			} catch (InterruptedException e) { e.printStackTrace(); }
		}
		
		try {
			socket.close();
		} catch (IOException e) { e.printStackTrace(); }
	}
	
	public static void run() {
		System.out.println("==~ Your hostring the server.");
		try {
			main( new String[0] );
		} catch (IOException e) { e.printStackTrace(); }
	}
	
	private static void connect(Client client, BufferedReader in, PrintStream out) throws IOException {
		int i = 10000;
		while ( !in.ready() ) {
			i--;
			if (i < 0) { return; }
		}
		String status = in.readLine();
		int pos = 0;
		if ( status.equals("n") ) {
			//pos = Math.max(0, log.size() - 10);
			out.println( "==~ Welcome to ChatterBox!" );
		} else if ( status.equals("o") ) {
			//pos = log.size();
		} else if ( !status.equals("a") ) {
			return;
		}
		while ( active ) {
			if ( !client.occupy && in.ready() ) {
				String inpt = in.readLine();
				if (inpt.equals("\\quit")) { break; }
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