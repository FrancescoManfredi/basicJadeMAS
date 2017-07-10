# Test Multiagent System for wpMAS

This is a [JADE](http://jade.tilab.com/) multiagent system capable of receiving messages from the
[wpMAS WordPress Plugin](https://github.com/FrancescoManfredi/WPMAS).

The communication happens via sockets. You won't need to set up a web service with
the WSIG Add-on for JADE.

Take a look at ListenerAgent and PrinterAgent to understand how to set up an agent
with the specific purpose of listening to and forwarding wpMAS messages.

To start this MAS the main class is jade.Boot. You'll need the following options:  
-gui -local-host 127.0.0.1 -platform-id testJadeMas -agents "bob:mas.ListenerAgent;john:mas.PrinterAgent"
