package mas;

import jade.core.AID;
import static jade.core.AID.ISGUID;
import static jade.core.AID.ISLOCALNAME;
import jade.core.behaviours.Behaviour;
import jade.domain.FIPAAgentManagement.Envelope;
import jade.lang.acl.ACLMessage;
import static jade.lang.acl.ACLMessage.INFORM;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;

/**
 * This is the behaviour of the agent listening for incoming wpMAS messages.
 *
 * @author francesco
 */
public class ReactToMessage extends Behaviour {
	
	private ServerSocket server;
	
	public ReactToMessage(ServerSocket s) {
		super();
		this.server = s;
	}
	
	public void action() {
		
		Socket clientSocket;
		PrintWriter out;
		BufferedReader in;
		
		try {
			clientSocket = this.server.accept();
			out = new PrintWriter(clientSocket.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			
			String inputLine;
			while ((inputLine = in.readLine()) != null) {
				System.out.println(this.getAgent().getAID().getName() + ": Message received.");
				manageLine(inputLine);
			}
			
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	/**
	 * 
	 * @return true when you want this behaviour to stop
	 */
	public boolean done() {
		
		return false;
	}
	
	/**
	 * This is the management function for the single line received from the
	 * socket.
	 * This particular implementation extracts data from that line and sends
	 * an ACLMessage to the right agent.
	 */
	private void manageLine(String line) {
		InputStream is = new ByteArrayInputStream(line.getBytes(StandardCharsets.UTF_8));
		JsonReader jr = Json.createReader(is);
		JsonObject obj = jr.readObject();
		String to = obj.getString("receiver");
		String from = obj.getString("sender");
		String msg = obj.getString("msg");
		String lang = obj.getString("lang");
		
		ACLMessage aclm = new ACLMessage();
		aclm.addReceiver(new AID(to, ISLOCALNAME));
		aclm.setSender(new AID(from, ISGUID));
		aclm.setLanguage(lang);
		aclm.setContent(msg);
		aclm.setPerformative(INFORM);
		this.getAgent().send(aclm);
		
		System.out.println(this.getAgent().getAID().getName() + ": Message sent.");
	}
	
}
