package de.noah.infoha.netzwerk.message;

import de.noah.infoha.abiturklassen.Client;

import java.awt.*;

public class MessageClient extends Client {

    private final ClientPanel clientPanel;
    private final String serverIp;
    private final int serverPort;

    public MessageClient(String pServerIP, int pServerPort, ClientPanel clientPanel) {
        super(pServerIP, pServerPort);
        this.clientPanel = clientPanel;
        this.serverIp = pServerIP;
        this.serverPort = pServerPort;
    }

    @Override
    public void processMessage(String pMessage) {
        final String[] args = pMessage.split("-");
        if(args[0].equalsIgnoreCase("data")) {
            clientPanel.setTitle("Client - "+args[1]);
            return;
        } else if(args[0].equalsIgnoreCase("clients")) {
            final String[] split = args[1].split(";");
            clientPanel.getClientList().removeAll();
            clientPanel.getClientList().add("all");
            for(String client : split) {
                if(!client.equalsIgnoreCase(clientPanel.getTitle().replace("Client - ", ""))) {
                    clientPanel.getClientList().add(client);
                }
            }

            return;
        } else if(args[0].equalsIgnoreCase("message-")) {
            final Message msg = Message.fromJson(args[1]);
            if((msg.getClientIP()+":"+msg.getClientPort()).equalsIgnoreCase(clientPanel.getTitle().replace("Client - ", ""))) return;
            clientPanel.getTextArea().append(msg+"\n");
            clientPanel.toFront();
        }
    }

    @Override
    public void processMessage(Object pObject) {
        if(pObject instanceof DataTransfer) {
            final DataTransfer dataTransfer = (DataTransfer) pObject;
            if(dataTransfer.getValue() instanceof Image) {
                if(clientPanel.getWebcamManager() != null) {
                    clientPanel.getWebcamManager().receiveImage(dataTransfer.getClientIp(), dataTransfer.getClientPort(), (Image) dataTransfer.getValue());
                }
            }
        }
    }

    public void send(String clientIp, int clientPort, String msg) {
        send(new Message(clientIp, clientPort, msg));
    }

    public void send(String clientIp, int clientPort, Object value) {
        send(new DataTransfer(clientIp, clientPort, value));
    }

    public void send(DataTransfer dataTransfer) {
        send((Object) dataTransfer);
    }


    public void send(Message message) {
        send("message-"+message.toJson());
    }

    public int getServerPort() {
        return serverPort;
    }

    public String getServerIp() {
        return serverIp;
    }

    public ClientPanel getClientPanel() {
        return clientPanel;
    }

    public String getClientIp() {
        return clientPanel.getTitle().replace("Client - ", "").split(":")[0];
    }
    public int getClientPort() {
        return Integer.parseInt(clientPanel.getTitle().replace("Client - ", "").split(":")[1]);
    }
}
