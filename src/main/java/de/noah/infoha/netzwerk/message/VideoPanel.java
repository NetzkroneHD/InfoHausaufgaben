package de.noah.infoha.netzwerk.message;

import java.awt.*;
import java.util.Objects;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class VideoPanel extends JFrame {

    private final JPanel contentPane;
    private final JLabel lblVideo;
    private final String clientIp;
    private final int clientPort;
    private ImageIcon imageIcon;

    public VideoPanel(String clientIp, int clientPort) {
        super(clientIp+":"+clientPort);
        this.clientIp = clientIp;
        this.clientPort = clientPort;
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);

        lblVideo = new JLabel("Video");


        contentPane.add(lblVideo, BorderLayout.CENTER);
        setVisible(true);
        setResizable(false);
    }

    public void updateImage(ImageIcon image) {
        lblVideo.setIcon(image);
        this.imageIcon = image;
        Dimension size = new Dimension(image.getIconWidth(), image.getIconHeight());
        setPreferredSize(size);
        setMinimumSize(size);
        setMaximumSize(size);
        setSize(size);
    }

    public int getClientPort() {
        return clientPort;
    }

    public String getClientIp() {
        return clientIp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VideoPanel that = (VideoPanel) o;
        return clientPort == that.clientPort && Objects.equals(clientIp, that.clientIp);
    }

    @Override
    public void paintComponents(Graphics g) {
        g.drawImage(imageIcon.getImage(), 0, 0, null);
    }
}
