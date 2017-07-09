package mas;

import jade.core.Agent;

/**
 *
 * @author francesco
 */
public class ListenerAgent extends Agent {
	
	protected void setup() {
		System.out.println("Agent " + this.getAID().getName() + " started.");
	}
	
}
