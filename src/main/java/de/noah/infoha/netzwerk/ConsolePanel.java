package de.noah.infoha.netzwerk;

import de.noah.infoha.extraklassen.AsyncRun;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ConsolePanel extends JFrame {

    private final JPanel contentPane;
    private final JTextField textFieldIp, textFieldReceiver, textFieldServerMessage, textFieldClientMessage;
    private final JSpinner spinner;
    private final JButton btnStartServer, btnStartClient, btnAusgabeLeeren;
    private final TextArea consoleOutPut;
    private final JCheckBox sendToAll;
    private final List clientList;

    private DaytimeServer server;
    private DaytimeClient client;

    //92.209.162.12:25565
    public ConsolePanel() {
        super("Console Panel");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 776, 491);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        btnStartServer = new JButton("Starte Server");
        btnStartServer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(btnStartServer.getText().equalsIgnoreCase("Starte Server")) {
                    System.out.println("[Server] Starte Server...");
                    try {
                        server = new DaytimeServer((Integer)spinner.getValue());
                        btnStartServer.setText("Stoppe Server");
                        System.out.println("[Server] Server gestartet.");

                    } catch (Exception ex) {
                        System.out.println("[Server] Konnte Server nicht starten: "+ex);
                    }
                } else {
                    server.close();
                    btnStartServer.setText("Starte Server");
                }
            }
        });
        btnStartServer.setBounds(10, 172, 119, 23);
        contentPane.add(btnStartServer);

        consoleOutPut = new TextArea();
        consoleOutPut.setBounds(10, 228, 740, 214);
        consoleOutPut.setEditable(false);
        contentPane.add(consoleOutPut);

        btnStartClient = new JButton("Starte Client");
        btnStartClient.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(btnStartClient.getText().equalsIgnoreCase("Starte Client")) {
                    if(server == null || !server.isOpen()) {
                        System.out.println("Der Server muss zuerst gestartet werden.");
                        return;
                    }
                    new AsyncRun(() -> {
                        try {
                            final String ip = textFieldIp.getText().split(":")[0];
                            final int port = Integer.parseInt(textFieldIp.getText().split(":")[1]);
                            client = new DaytimeClient(ip, port);

                            btnStartClient.setText("Stoppe Client");
                        } catch (Exception ex) {
                            System.out.println("[Client] Konnte Client nicht starten: "+ex);
                        }
                    });
                } else {
                    client.close();
                    btnStartClient.setText("Starte Client");
                }
            }
        });
        btnStartClient.setBounds(10, 67, 119, 23);
        contentPane.add(btnStartClient);

        textFieldIp = new JTextField();
        textFieldIp.setBounds(10, 36, 119, 20);
        contentPane.add(textFieldIp);
        textFieldIp.setColumns(10);

        JLabel lblHost = new JLabel("Host:  (IP:Port)");
        lblHost.setBounds(10, 11, 119, 14);
        contentPane.add(lblHost);

        spinner = new JSpinner();
        spinner.setBounds(10, 141, 89, 20);
        contentPane.add(spinner);

        JLabel lblServerPort = new JLabel("Server Port:");
        lblServerPort.setBounds(10, 116, 119, 14);
        contentPane.add(lblServerPort);

        JLabel lblOutput = new JLabel("Ausgabe:");
        lblOutput.setBounds(10, 206, 109, 14);
        contentPane.add(lblOutput);


        textFieldClientMessage = new JTextField();
        textFieldClientMessage.setBounds(198, 36, 141, 20);
        contentPane.add(textFieldClientMessage);
        textFieldClientMessage.setColumns(10);

        JLabel lblNachricht = new JLabel("Client Nachricht:");
        lblNachricht.setBounds(198, 11, 141, 14);
        contentPane.add(lblNachricht);

        JButton btnClientSenden = new JButton("Senden");
        btnClientSenden.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(client != null && client.isConnected()) {
                    try {
                        client.send(textFieldClientMessage.getText());
                    } catch (Exception ex) {
                        System.out.println("[Client] Konnte Nachricht verschicken: "+ex);
                    }
                } else System.out.println("[Client] Der Client ist nicht verbunden.");
            }
        });
        btnClientSenden.setBounds(198, 67, 89, 23);
        contentPane.add(btnClientSenden);

        JLabel lblServerNachricht = new JLabel("Server Nachricht:");
        lblServerNachricht.setBounds(198, 116, 141, 14);
        contentPane.add(lblServerNachricht);

        textFieldServerMessage = new JTextField();
        textFieldServerMessage.setColumns(10);
        textFieldServerMessage.setBounds(198, 141, 141, 20);
        contentPane.add(textFieldServerMessage);

        JButton btnServerMessage = new JButton("Senden");
        btnServerMessage.setBounds(198, 172, 89, 23);
        btnServerMessage.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(server != null && server.isOpen()) {
                    if(sendToAll.isSelected()) {
                        try {
                            server.sendToAll(textFieldServerMessage.getText());
                            System.out.println("[Server] Nachricht wurde an alle Clients verschickt.");
                        } catch (Exception ex) {
                            System.out.println("[Server] Nachricht konnte nicht verschickt werden: "+ex);
                        }
                    } else {
                        try {
                            server.send(textFieldIp.getText().split(":")[0], Integer.parseInt(textFieldReceiver.getText().split(":")[1]), textFieldReceiver.getText());
                            System.out.println("[Server] Nachricht wurde an \""+ textFieldReceiver.getText()+"\" gesendet.");
                        } catch (Exception ex) {
                            System.out.println("[Server] Nachricht konnte nicht verschickt werden: "+ex);
                        }
                    }
                } else System.out.println("[Server] Der Server ist noch nicht gestartet.");
            }
        });
        contentPane.add(btnServerMessage);

        JLabel lblEmpfnger = new JLabel("Empf\u00E4nger (IP:Port)");
        lblEmpfnger.setBounds(359, 116, 141, 14);
        contentPane.add(lblEmpfnger);

        textFieldReceiver = new JTextField();
        textFieldReceiver.setBounds(359, 141, 141, 20);
        contentPane.add(textFieldReceiver);
        textFieldReceiver.setColumns(10);

        sendToAll = new JCheckBox("An alle senden");
        sendToAll.setBounds(359, 172, 141, 23);
        contentPane.add(sendToAll);


        btnAusgabeLeeren = new JButton("Ausgabe leeren");
        btnAusgabeLeeren.addActionListener(e -> consoleOutPut.setText(""));
        btnAusgabeLeeren.setBounds(621, 199, 129, 23);
        contentPane.add(btnAusgabeLeeren);


        clientList = new List();
        clientList.setBounds(581, 40, 169, 137);
        contentPane.add(clientList);

        JLabel lblClients = new JLabel("Clients:");
        lblClients.setBounds(581, 20, 169, 14);
        contentPane.add(lblClients);

        System.setOut(new TextAreaPrintStream(consoleOutPut, System.out));
        System.setErr(new TextAreaPrintStream(consoleOutPut, System.err));

    }

    public List getClientList() {
        return clientList;
    }
}
