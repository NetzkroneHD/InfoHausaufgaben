package de.noah.infoha.netzwerk.message;

import com.github.sarxos.webcam.Webcam;

import java.awt.*;
import java.util.*;

public class WebcamManager {

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
        videos = new HashMap<>();
        startRendering();
    }

    public void startRendering() {
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                try {
                    ownVideo.updateImage(webcam.getImage());
                    client.send(new DataTransfer("all", 0, webcam.getImage()));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        },0, 150);
    }

    public void stop() {
        timer.cancel();
        ownVideo.setVisible(false);
        for(VideoPanel panel : videos.values()) {
            panel.setVisible(false);
        }
    }

    public void receiveImage(String clientIp, int clientPort, Image image) {
        System.out.println("[Client] Erhalte Video Bild: "+clientIp+":"+clientPort);
        if(!videos.containsKey(clientIp+":"+clientPort)) {
            videos.put(clientIp+":"+clientPort, new VideoPanel(clientIp, clientPort));
        }
        videos.get(clientIp+":"+clientPort).updateImage(image);
    }

    public Webcam getWebcam() {
        return webcam;
    }
}
