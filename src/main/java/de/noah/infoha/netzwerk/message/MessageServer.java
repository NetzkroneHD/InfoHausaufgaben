package de.noah.infoha.netzwerk.message;

import com.google.gson.Gson;
import de.noah.infoha.abiturklassen.Server;

import java.util.ArrayList;
import java.util.Arrays;
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
        send(pClientIP, pClientPort, "data"+MessageMain.COMMAND_SEPARATOR+pClientIP+":"+pClientPort);
        clients.add(pClientIP+":"+pClientPort);
        sendToAll("clients"+MessageMain.COMMAND_SEPARATOR+(clientListToString()));
        MessageMain.getConsolePanel().getClientList().add(pClientIP+":"+pClientPort);


    }

    @Override
    public void processMessage(String pClientIP, int pClientPort, String pMessage) {
        System.out.println("[Server] Client '"+pClientIP+":"+pClientPort+"' hat eine Nachricht gesendet: \""+pMessage+"\"");
        final String[] args = pMessage.split(MessageMain.COMMAND_SEPARATOR);
        System.out.println("Args: "+ Arrays.toString(args));
        if(args[0].equalsIgnoreCase("message")) {
            final Message target = gson.fromJson(args[1], Message.class);
            final Message src = new Message(pClientIP, pClientPort, target.getMessage());
            if(target.getClientIP().equalsIgnoreCase("all")) {
                sendToAll("message"+MessageMain.COMMAND_SEPARATOR+src.toJson());
            } else send(target.getClientIP(), target.getClientPort(), "message"+MessageMain.COMMAND_SEPARATOR+src.toJson());
        } else if(args[0].equalsIgnoreCase("datatransfer")) {
            final DataTransfer dt = DataTransfer.fromJson(args[1]);
            if(dt.getClientIp().equalsIgnoreCase("all")) {
                sendToAll("datatransfer"+MessageMain.COMMAND_SEPARATOR+new DataTransfer(pClientIP, pClientPort, dt.getValue()).toJson());
            } else send(dt.getClientIp(), dt.getClientPort(), "datatransfer"+MessageMain.COMMAND_SEPARATOR+new DataTransfer(pClientIP, pClientPort, dt.getValue()).toJson());
        }


    }

    @Override
    public void processClosingConnection(String pClientIP, int pClientPort) {
        System.out.println("[Server] Client '"+pClientIP+":"+pClientPort+"' hat die Verbindung unterbrochen.");
        MessageMain.getConsolePanel().getClientList().remove(pClientIP+":"+pClientPort);
        clients.remove(pClientIP+":"+pClientPort);
        sendToAll("clients"+MessageMain.COMMAND_SEPARATOR+(clientListToString()));
    }

    private String clientListToString() {
        final StringBuilder sb = new StringBuilder();
        for(String client : clients) {
            sb.append(client).append(";");
        }
        return sb.toString();
    }

}
