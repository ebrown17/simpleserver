import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class ServerThread  extends Thread {
	
	
	private  Socket clientSocket = null;
	
	
	
	public ServerThread(Socket socket){
		
		super("ServerThread");
		this.clientSocket = socket;
		
	}
	
	public void run() {
		
		try {			
				System.out.println("Connection from: " + clientSocket.getRemoteSocketAddress() +" made.");
				
				String user = new String(readBytes());
				
				//System.out.println(user + " sent bytes!");	
				
				while(true){
					
					String read = new String(readBytes());
					String combined = new String(user + ": " +read);
					byte[] sendData = new byte[combined.length()];
					sendData=combined.getBytes();						// to change from byte to string value String convert = new String(sendData);
					sendBytes(sendData, clientSocket);		
					sendData=null;
					//System.out.println(user + ": " + read);	
					if(read.equals("bye"))	break;	
					
					
				}
		
		
			} catch (Exception e){
			
				System.out.println("Error: " + e.getMessage());
			
			} finally {
			
				try {
					System.out.println("Connection from: " +clientSocket.getRemoteSocketAddress() + " closed." );
					clientSocket.close();
				} catch (IOException e) {
			
					e.printStackTrace();
				}
			}
		
		
		
	}
	
	public byte[] readBytes() throws IOException {
	 
	    InputStream in = clientSocket.getInputStream();
	    DataInputStream dis = new DataInputStream(in);

	    int len = dis.readInt();
	    byte[] data = new byte[len];
	    if (len > 0) {
	        dis.readFully(data);
	    }
	    return data;
	}
	
	public static void sendBytes(byte[] myByteArray, Socket socket) throws IOException {
	    sendBytes(myByteArray, 0, myByteArray.length, socket);
	}
	
	public static void sendBytes(byte[] myByteArray, int start, int len, Socket socket) throws IOException {
	    if (len < 0)
	        throw new IllegalArgumentException("Negative length not allowed");
	    
	    if (start < 0 || start >= myByteArray.length)
	        throw new IndexOutOfBoundsException("Out of bounds: " + start);
	 
	    OutputStream out = socket.getOutputStream(); 
	    DataOutputStream dos = new DataOutputStream(out);

	    dos.writeInt(len);
	    if (len > 0) {
	        dos.write(myByteArray, start, len);
	    }
	}
	

}
