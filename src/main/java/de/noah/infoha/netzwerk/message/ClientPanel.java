package de.noah.infoha.netzwerk.message;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClientPanel extends JFrame {

    private final JPanel contentPane;
    private final JTextField textFieldMessage;
    private final JTextField textFieldReceiver;
    private MessageClient messageClient;
    private final TextArea textArea;
    private final List clientList;
    private final JButton btnSenden, btnVerbindungTrennen;

    public ClientPanel() {
        setTitle("Client - loading...");
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setBounds(100, 100, 610, 388);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        btnSenden = new JButton("Senden");
        btnSenden.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(messageClient.isConnected()) {
                    String ip;
                    int port = 0;
                    if(textFieldReceiver.getText().equalsIgnoreCase("all")) {
                        ip = "all";
                    } else {
                        final String[] splited = textFieldReceiver.getText().split(":");
                        ip = splited[0];
                        port = Integer.parseInt(splited[1]);
                    }

                    messageClient.send(ip, port, textFieldMessage.getText());
                } else textArea.append("Der Client ist nicht verbunden.\n");

            }
        });
        btnSenden.setBounds(10, 82, 89, 23);
        contentPane.add(btnSenden);

        textFieldMessage = new JTextField();
        textFieldMessage.setBounds(10, 51, 207, 20);
        contentPane.add(textFieldMessage);
        textFieldMessage.setColumns(10);

        JLabel lblNachricht = new JLabel("Nachricht");
        lblNachricht.setBounds(10, 26, 179, 14);
        contentPane.add(lblNachricht);

        textFieldReceiver = new JTextField();
        textFieldReceiver.setBounds(227, 51, 144, 20);
        contentPane.add(textFieldReceiver);
        textFieldReceiver.setColumns(10);

        JLabel lblEmpfaenger = new JLabel("Empfänger (Ip:Port)");
        lblEmpfaenger.setBounds(227, 26, 144, 14);
        contentPane.add(lblEmpfaenger);

        clientList = new List();
        clientList.setBounds(440, 51, 144, 103);
        clientList.addItemListener(e -> textFieldReceiver.setText(clientList.getSelectedItem()));
        contentPane.add(clientList);

        JLabel lblClients = new JLabel("Clients:");
        lblClients.setBounds(440, 26, 110, 14);
        contentPane.add(lblClients);

        btnVerbindungTrennen = new JButton("Verbindung trennen");
        btnVerbindungTrennen.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                messageClient.close();
                clientList.removeAll();
                textArea.append("Verbindung wurde getrennt.\n");

            }
        });
        btnVerbindungTrennen.setBounds(10, 116, 135, 23);
        contentPane.add(btnVerbindungTrennen);

        textArea = new TextArea();
        textArea.setBounds(10, 179, 574, 160);
        textArea.setEditable(false);
        contentPane.add(textArea);

    }

    public void setMessageClient(MessageClient messageClient) {
        this.messageClient = messageClient;
    }

    public MessageClient getMessageClient() {
        return messageClient;
    }

    public List getClientList() {
        return clientList;
    }

    public TextArea getTextArea() {
        return textArea;
    }
}