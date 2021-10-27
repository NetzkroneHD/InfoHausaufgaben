package de.noah.infoha.netzwerk.message;

import com.github.sarxos.webcam.Webcam;

import javax.swing.*;
import java.util.*;
import java.util.Timer;

public class WebcamManager {

    private String receiverClientIp;
    private int receiverClientPort;
    private final MessageClient client;
    private final Webcam webcam;
    private final VideoPanel ownVideo;
    private final Timer timer;
    private final HashMap<String, VideoPanel> videos;

    public WebcamManager(MessageClient client) {
        this.client = client;
        this.webcam = Webcam.getDefault();
        webcam.open(true);
        timer = new Timer();
        ownVideo = new VideoPanel(client.getClientIp(), client.getClientPort());
        ownVideo.setTitle("Deine Webcam");
        videos = new HashMap<>();
        startRendering();
        receiverClientIp = "all";
        receiverClientPort = 0;

    }

    public void startRendering() {
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                try {
                    ownVideo.updateImage(new ImageIcon(webcam.getImage()));
                    client.send(new DataTransfer(receiverClientIp, receiverClientPort, new ImageIcon(webcam.getImage())));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        },0, 300);
    }

    public void stop() {
        timer.cancel();
        ownVideo.setVisible(false);
        for(VideoPanel panel : videos.values()) {
            panel.setVisible(false);
        }
    }

    public void receiveImage(String clientIp, int clientPort, ImageIcon image) {
        //System.out.println("[Client] Erhalte Video Bild: "+clientIp+":"+clientPort);
        client.getClientPanel().getTextArea().append("[Client] Erhalte Video Bild: "+clientIp+":"+clientPort+"\n");
        if(!videos.containsKey(clientIp+":"+clientPort)) {
            videos.put(clientIp+":"+clientPort, new VideoPanel(clientIp, clientPort));
        }
        videos.get(clientIp+":"+clientPort).updateImage(image);
    }

    public Webcam getWebcam() {
        return webcam;
    }

    public void setReceiverClientIp(String receiverClientIp) {
        this.receiverClientIp = receiverClientIp;
    }

    public void setReceiverClientPort(int receiverClientPort) {
        this.receiverClientPort = receiverClientPort;
    }

    public String getReceiverClientIp() {
        return receiverClientIp;
    }

    public int getReceiverClientPort() {
        return receiverClientPort;
    }
}
