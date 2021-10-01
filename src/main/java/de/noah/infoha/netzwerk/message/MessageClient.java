package de.noah.infoha.netzwerk.message;

import de.noah.infoha.abiturklassen.Client;

import java.awt.*;

public class MessageClient extends Client {

    private final ClientPanel clientPanel;

    public MessageClient(String pServerIP, int pServerPort, ClientPanel clientPanel) {
        super(pServerIP, pServerPort);
        this.clientPanel = clientPanel;
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
            for(String client : splitted) {
                if(!client.equalsIgnoreCase(clientPanel.getTitle().replace("Client - ", ""))) {
                    clientPanel.getClientList().add(client);
                }
            }

            return;
        }
        final Message msg = Message.fromJson(pMessage);

        clientPanel.getTextArea().append("Nachricht empfangen:\n"+msg.toDisplayString());
    }

    public void send(String clientIp, int clientPort, String msg) {
        send(new Message(clientIp, clientPort, msg));
    }

    public void send(Message message) {
        send(message.toJson());
    }

}
