/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mas;

import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;

/**
 *
 * @author francesco
 */
public class PrintMessages extends Behaviour {
	
	/**
	 * I just print a message every time a get one.
	 */
	public void action() {
		
		ACLMessage m = this.getAgent().blockingReceive();
		System.out.println(this.getAgent().getAID().getName() + ": just received the following message ===");
		System.out.println(m.toString());
		System.out.println("=== end of message.");
		
	}
	
	/**
	 * I'm never done
	 * @return 
	 */
	public boolean done() {
		return false;
	}
	
}
