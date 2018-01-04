package networking;

import java.io.*;
import java.net.*;

public class UDPsender {
	public static void main(String args[]) throws Exception {
		DatagramSocket serverSocket = new DatagramSocket(9876);
		BufferedReader user = new BufferedReader( new InputStreamReader(System.in) );
		byte[] sendData = new byte[1024];
		
		while ( true ) {
			if ( user.ready() ) {
				String inpt = user.readLine();
				if ( inpt.equals("close") ) {
					break;
				}
			}
		}
		
		serverSocket.close();
	}
}