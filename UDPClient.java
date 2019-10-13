import java.net.DatagramPacket; 
import java.net.DatagramSocket; 
import java.net.InetAddress; 
import java.net.SocketException;

public class UDPClient {
	public static void main(String[] args) {
		// Args give message(your name) and server name 
		DatagramSocket aSocket = null;
		try{
			// print the localhost address
			System.out.println(InetAddress.getLocalHost());
			// set up a socket
			aSocket = new DatagramSocket();
			// create a message
			byte[] msg = args[0].getBytes();
			// create the packet with the message
			InetAddress serverAddress = InetAddress.getByName(args[1]); 
			int serverPort = 6788;
			DatagramPacket request = new DatagramPacket(msg, msg.length, serverAddress,  						serverPort);
			//send the packet
			aSocket.send(request);
			// create space and receive reply packet
			byte[] buffer = new byte[1000];
			DatagramPacket reply = new DatagramPacket(buffer, buffer.length);
			aSocket.receive(reply);
			// extract and print reply message
			System.out.println("Reply: "+ new String(reply.getData()));
		// deal with exceptions
		} catch (SocketException es){
			System.out.println("Exception: "+ es.getMessage());
		} catch (java.io.IOException ex){ 
			System.out.println("Exception: "+ ex.getMessage());
		} finally {
			if (aSocket != null) aSocket.close();
		}
	}
}
