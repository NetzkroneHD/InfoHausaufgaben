package de.noah.infoha.netzwerk.message;

import de.noah.infoha.abiturklassen.Client;

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
        if(pMessage.startsWith("data-")) {
            final String[] splitted = pMessage.split("-");
            clientPanel.setTitle("Client - "+splitted[1]);
            return;
        } else if(pMessage.startsWith("clients-")) {
            final String[] splitted = pMessage.replace("clients-", "").split(";");
            clientPanel.getClientList().removeAll();
            clientPanel.getClientList().add("all");
            for(String client : splitted) {
                if(!client.equalsIgnoreCase(clientPanel.getTitle().replace("Client - ", ""))) {
                    clientPanel.getClientList().add(client);
                }
            }

            return;
        }
        final Message msg = Message.fromJson(pMessage);
        if((msg.getClientIP()+":"+msg.getClientPort()).equalsIgnoreCase(clientPanel.getTitle().replace("Client - ", ""))) return;
        clientPanel.getTextArea().append(msg+"\n");
        clientPanel.toFront();
    }

    public void send(String clientIp, int clientPort, String msg) {
        send(new Message(clientIp, clientPort, msg));
    }

    public void send(Message message) {
        send(message.toJson());
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
}
