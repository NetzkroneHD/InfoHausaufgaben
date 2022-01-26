package de.noah.infoha.netzwerk.message;

import de.noah.infoha.extraklassen.AsyncRun;

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
    private final JButton btnSenden, btnVerbindungTrennen, btnVideoAktivieren;
    private WebcamManager webcamManager;

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
                    if(textFieldMessage.getText().equalsIgnoreCase("") || textFieldMessage.getText().replace(" ", "").equalsIgnoreCase("")) {
                        textArea.append("Es muss eine richtige Nachricht angegeben.\n");
                        return;
                    }
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
                    textArea.append("(Du): "+textFieldMessage.getText()+"\n");
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
        clientList.addItemListener(e -> {
            textFieldReceiver.setText(clientList.getSelectedItem());
            if(webcamManager != null) {
                if(clientList.getSelectedItem().equalsIgnoreCase("all")) {
                    webcamManager.setReceiverClientIp("all");
                } else {
                    webcamManager.setReceiverClientIp(clientList.getSelectedItem().split(":")[0]);
                    webcamManager.setReceiverClientPort(Integer.parseInt(clientList.getSelectedItem().split(":")[1]));
                }
            }
        });

        contentPane.add(clientList);

        JLabel lblClients = new JLabel("Clients:");
        lblClients.setBounds(440, 26, 110, 14);
        contentPane.add(lblClients);

        btnVerbindungTrennen = new JButton("Verbindung trennen");
        btnVerbindungTrennen.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(btnVerbindungTrennen.getText().equalsIgnoreCase("Verbindung trennen")) {
                    messageClient.close();
                    clientList.removeAll();
                    textArea.append("Verbindung wurde getrennt.\n");
                    btnVerbindungTrennen.setText("Verbinden");
                } else {
                    messageClient = new MessageClient(messageClient.getServerIp(), messageClient.getServerPort(), messageClient.getClientPanel());
                    btnVerbindungTrennen.setText("Verbindung trennen");
                }

            }
        });
        btnVerbindungTrennen.setBounds(10, 116, 135, 23);
        contentPane.add(btnVerbindungTrennen);

        textArea = new TextArea();
        textArea.setBounds(10, 179, 574, 160);
        textArea.setEditable(false);
        contentPane.add(textArea);

        btnVideoAktivieren = new JButton("Video aktivieren");
        btnVideoAktivieren.addActionListener(e -> {
            if(btnVideoAktivieren.getText().equalsIgnoreCase("Video deaktivieren")) {
                if(webcamManager != null) {
                    webcamManager.stop();
                    webcamManager = null;
                    btnVideoAktivieren.setText("Video aktivieren");
                }
            } else {
                if(messageClient.isConnected()) {
                    if(btnVideoAktivieren.getText().equalsIgnoreCase("Video aktivieren")) {
                        new AsyncRun(() -> {
                            webcamManager = new WebcamManager(messageClient);
                            btnVideoAktivieren.setText("Video deaktivieren");
                        });
                    }
                } else textArea.append("Der Client ist nicht verbunden.\n");
            }
        });
        btnVideoAktivieren.setBounds(227, 116, 144, 23);
        contentPane.add(btnVideoAktivieren);

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

    public WebcamManager getWebcamManager() {
        return webcamManager;
    }
}