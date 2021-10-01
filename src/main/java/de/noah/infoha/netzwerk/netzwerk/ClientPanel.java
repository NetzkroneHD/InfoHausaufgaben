package de.noah.infoha.netzwerk.netzwerk;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClientPanel extends JFrame {

    private final JPanel contentPane;
    private final JTextField textFieldMessage;
    private final JTextField textFieldReceiver;
    private final MessageClient messageClient;
    private final TextArea textArea;

    public ClientPanel(MessageClient mc) {
        setTitle("Client - loading...");
        this.messageClient = mc;
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setBounds(100, 100, 610, 388);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JButton btnSenden = new JButton("Senden");
        btnSenden.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                final String[] splited = textFieldReceiver.getText().split(":");

                messageClient.send(splited[0], Integer.parseInt(splited[1]), textFieldMessage.getText());

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

        JLabel lblEmpfaengeripport = new JLabel("Empfänger (Ip:Port)");
        lblEmpfaengeripport.setBounds(227, 26, 144, 14);
        contentPane.add(lblEmpfaengeripport);

        textArea = new TextArea();
        textArea.setBounds(10, 141, 574, 198);
        contentPane.add(textArea);

    }

    public MessageClient getMessageClient() {
        return messageClient;
    }

    public TextArea getTextArea() {
        return textArea;
    }
}