package mas;

import jade.core.Agent;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This agent listens to a socket server for messages from wpMAS.
 * See ReactToMessage behaviour class for more details.
 * 
 * @author francesco
 */
public class ListenerAgent extends Agent {
	
	private ServerSocket wpMasSocket;
	private DataInputStream input;
	
	protected void setup() {
		System.out.println("Agent " + this.getAID().getName() + " started.");
		
		try {
			this.wpMasSocket = new ServerSocket(5000);
			System.out.println("Socket created.");
		} catch (IOException ex) {
			System.out.println("Socket creation failed...");
			System.out.println(ex);
			return;
		}
		
		this.addBehaviour(new ReactToMessage(this.wpMasSocket));
	}
	
}
