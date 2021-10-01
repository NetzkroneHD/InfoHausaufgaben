package de.noah.infoha.netzwerk.netzwerk;

import de.noah.infoha.abiturklassen.Client;

import java.awt.*;

public class MessageClient extends Client {

    private TextArea textArea;
    private ClientPanel clientPanel;

    public MessageClient(String pServerIP, int pServerPort) {
        super(pServerIP, pServerPort);
    }

    public void setClientPanel(ClientPanel clientPanel) {
        this.clientPanel = clientPanel;
        this.textArea = clientPanel.getTextArea();
    }

    @Override
    public void processMessage(String pMessage) {
        if(pMessage.startsWith("data-")) {
            final String[] splitted = pMessage.split("-");
            clientPanel.setTitle("Client - "+splitted[1]);
            return;
        }
        final Message msg = Message.fromJson(pMessage);

        textArea.append("Nachricht empfangen:\n"+msg.toDisplayString());
    }

    public void send(String clientIp, int clientPort, String msg) {
        send(new Message(clientIp, clientPort, msg));
    }

    public void send(Message message) {
        send(message.toJson());
    }

}
