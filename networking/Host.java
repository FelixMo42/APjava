package networking;

import java.io.*;
import java.net.*;
import java.util.*;

public class Host {
	private static class Client implements Runnable {
		public Thread thread = new Thread(this, "Client " + connections.size());
		public Socket client;
		
		public Client(Socket socket) {
			client = socket;
			thread.start();
		}
		
		public void run() {
			try {
				BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
				PrintStream out = new PrintStream(client.getOutputStream());
				connect(client, in, out);
				in.close();
				out.println( "quiting server" );
				System.out.println( thread.getName() + " out!" );
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	private static class Main implements Runnable {
		public void run() {
			try {
				while ( true ) {
					connections.add( new Client( socket.accept() ) );
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static String ip;
	public static int port;
	
	private volatile static boolean active = false;
	private volatile static ArrayList<String> log;
	private static Thread mainLoop;
	private static ServerSocket socket;
	private static ArrayList<Client> connections = new ArrayList<Client>();
	
	public static void main(String[] args) throws IOException {
		//open every thing up
		active = true;
		log = new ArrayList<String>();
		socket = new ServerSocket(0);
		//set up connection info
		ip = Info.getLocalIp();
		port = socket.getLocalPort();
        Info.request("http://www.mosegames.com/school.php", "PUT", Info.getWebIp() + "\n" + ip + "\n" + port);
        //main loop
        Main main = new Main();
        mainLoop = new Thread(main, "MainLoop");
        mainLoop.start();
	}
	
	public static void close() {
		if (!active) { return; }
		active = false;
		mainLoop.interrupt();
		System.out.println("main loop quit!");
		for (Client client : connections) {
			try {
				client.thread.join();
				System.out.println("connect closed!");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("all connections closed!");
		try {
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("scoket closed!");
	}
	
	public static void run() {
		try {
			main( new String[0] );
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static void connect(Socket socket, BufferedReader in, PrintStream out) throws IOException {
		while ( !in.ready() ) {}
		String status = in.readLine();
		int pos = 0;
		if ( status.equals("n") ) {
			pos = Math.max(0, log.size() - 10);
			out.println( "==~ Welcome to ChatterBox!" );
		} else if ( status.equals("o") ) {
			pos = log.size();
		} else if ( !status.equals("a") ) {
			return;
		}
		while ( active ) {
			if ( in.ready() ) {
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