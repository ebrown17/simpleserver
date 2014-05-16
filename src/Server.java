import java.net.*;
import java.io.*;

public class Server {
	
	
	
	public static void main (String[] args) {
		
		
		
		int portNum = 4444;
		String server_ip = "localhost";
		ServerSocket tcpSocket = null;
		boolean listening = true;
		
		try {
		
			tcpSocket = new ServerSocket(portNum,0,InetAddress.getByName(server_ip));
			
			while(listening){
				
				new ServerThread(tcpSocket.accept()).start();
				
			}
		
		} catch (IOException e){
			
			e.printStackTrace();
			
		}
		
		
		
		
	}
	

	
	
	

}
