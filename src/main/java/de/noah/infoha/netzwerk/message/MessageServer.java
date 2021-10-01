package de.noah.infoha.netzwerk.message;

import com.google.gson.Gson;
import de.noah.infoha.abiturklassen.Server;

public class MessageServer extends Server {

    private Gson gson;

    public MessageServer(int pPort) {
        super(pPort);
        gson = new Gson();
    }

    @Override
    public void processNewConnection(String pClientIP, int pClientPort) {
        System.out.println("[Server] Client '"+pClientIP+":"+pClientPort+"' hat sich verbunden.");
        send(pClientIP, pClientPort, "data-"+pClientIP+":"+pClientPort);
        MessageMain.getConsolePanel().getClientList().add(pClientIP+":"+pClientPort);

    }

    @Override
    public void processMessage(String pClientIP, int pClientPort, String pMessage) {
        System.out.println("[Server] Client '"+pClientIP+":"+pClientPort+"' hat eine Nachricht gesendet: \""+pMessage+"\"");
        final Message target = gson.fromJson(pMessage, Message.class);
        final Message src = new Message(pClientIP, pClientPort, target.getMessage());
        send(target.getClientIP(), target.getClientPort(), src.toJson());

    }

    @Override
    public void processClosingConnection(String pClientIP, int pClientPort) {
        System.out.println("[Server] Client '"+pClientIP+":"+pClientPort+"' hat die Verbindung unterbrochen.");
        MessageMain.getConsolePanel().getClientList().remove(pClientIP+":"+pClientPort);
    }

}
