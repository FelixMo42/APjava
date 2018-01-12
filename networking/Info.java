package networking;

import java.io.*;
import java.net.*;

public class Info {
	private static String webip = "";
	
	public static String getLocalIp() {
		try {
			InetAddress iAddress;
			iAddress = InetAddress.getLocalHost();
			return iAddress.getHostAddress();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		return "";
	}
	
	public static InetAddress getIPv4LocalNetMask(InetAddress ip, int netPrefix) {
	    try {
	        int shiftby = (1<<31);
	        for (int i=netPrefix-1; i>0; i--) {
	            shiftby = (shiftby >> 1);
	        }
	        String maskString = Integer.toString((shiftby >> 24) & 255) + "." + Integer.toString((shiftby >> 16) & 255) + "." + Integer.toString((shiftby >> 8) & 255) + "." + Integer.toString(shiftby & 255);
	        return InetAddress.getByName(maskString);
	    }
	        catch(Exception e){e.printStackTrace();
	    }
	    return null;
	}
	
	public static String getWebIp() {
		if (webip.length() == 0) {
			try {
				URL whatismyip;
				whatismyip = new URL("http://checkip.amazonaws.com");
				BufferedReader in = new BufferedReader(new InputStreamReader( whatismyip.openStream()));
				InetAddress web_ip = InetAddress.getByName( in.readLine() );
				NetworkInterface networkInterface = NetworkInterface.getByInetAddress( Inet4Address.getLocalHost() );
				web_ip = getIPv4LocalNetMask( web_ip , networkInterface.getInterfaceAddresses().get(1).getNetworkPrefixLength() );
				webip = web_ip.getHostAddress();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return webip;
	}
	
	public static boolean portOpen(String host, int port) {
		Socket s = null;
	    try {
	        s = new Socket();
	        s.setReuseAddress(true);
	        SocketAddress sa = new InetSocketAddress(host, port);
	        s.connect(sa, 3000);
	        s.close();
	        return true;
	    } catch (IOException e) {
	        return false;
	    }
	}
	
	public static String request(String targetURL, String method, String urlParameters) {
		HttpURLConnection connection = null;
		
		try {
			//Create connection
			URL url = new URL(targetURL);
			connection = (HttpURLConnection) url.openConnection();
			
			connection.setRequestMethod(method);
			connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			
			connection.setRequestProperty("Content-Length", Integer.toString(urlParameters.getBytes().length));
			connection.setRequestProperty("Content-Language", "en-US");
			
			connection.setUseCaches(false);
			connection.setDoOutput(true);
			
			//Send request
			DataOutputStream wr = new DataOutputStream (connection.getOutputStream());
			wr.writeBytes(urlParameters);
			wr.close();
			
			//Get Response
			InputStream is = connection.getInputStream();
			BufferedReader rd = new BufferedReader(new InputStreamReader(is));
			StringBuilder response = new StringBuilder(); // or StringBuffer if Java version 5+
			String line;
			while ((line = rd.readLine()) != null) {
				response.append(line);
				response.append('\r');
			}
			rd.close();
			return response.toString();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			if (connection != null) {
				connection.disconnect();
			}
		}
	}
}