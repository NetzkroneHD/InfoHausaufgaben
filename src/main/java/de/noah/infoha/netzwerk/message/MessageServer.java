package de.noah.infoha.netzwerk.message;

import com.google.gson.Gson;
import de.noah.infoha.abiturklassen.Server;

import java.util.ArrayList;
import java.util.List;

public class MessageServer extends Server {


    private final Gson gson;
    private final List<String> clients;

    public MessageServer(int pPort) {
        super(pPort);
        gson = new Gson();
        clients = new ArrayList<>();
    }

    @Override
    public void processNewConnection(String pClientIP, int pClientPort) {
        System.out.println("[Server] Client '"+pClientIP+":"+pClientPort+"' hat sich verbunden.");
        send(pClientIP, pClientPort, "data-"+pClientIP+":"+pClientPort);
        clients.add(pClientIP+":"+pClientPort);
        sendToAll("clients-"+(clientListToString()));
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
        clients.remove(pClientIP+":"+pClientPort);
    }

    private String clientListToString() {
        final StringBuilder sb = new StringBuilder();
        for(String client : clients) {
            sb.append(client).append(";");
        }
        return sb.toString();
    }

}
