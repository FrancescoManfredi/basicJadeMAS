package mas;

import jade.core.Agent;

/**
 * This agent only exists to receive messages from ListenerAgent and print them.
 * @author francesco
 */
public class PrinterAgent extends Agent {
	protected void setup() {
		this.addBehaviour(new PrintMessages());
	}
}
