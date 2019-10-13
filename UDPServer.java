import java.net.DatagramPacket;
import java.net.DatagramSocket; 
import java.net.SocketException;

public class UDPServer {

	public static void main(String[] args) {

		DatagramSocket aSocket = null; 
		while (true){
			try{
				// set up the Socket
				aSocket = new DatagramSocket(6788);
				// create the container for the received packet
				byte[] buffer = new byte[1000];
				DatagramPacket request = new DatagramPacket(buffer, buffer.length);
				// recieve packet into container
				aSocket.receive(request);
				// extract and print the message 
				String msgReceive = new String(request.getData());
				System.out.println("Message from "+ msgReceive);

				// construct and send a reply
				String replyMsg = "Hello Your Message has been Received "+ msgReceive;
				byte[] msg = replyMsg.getBytes();
				DatagramPacket reply = new DatagramPacket(msg, msg.length,request.getAddress(),					request.getPort());
				aSocket.send(reply);


			// deal with all exceptions
			} catch (SocketException es){
				System.out.println("Exception: "+ es.getMessage()); 
			} catch (java.io.IOException ex){
				System.out.println("Exception: "+ ex.getMessage()); 
			} finally {
				if (aSocket != null) aSocket.close(); 
			}
		}
	} 
}
