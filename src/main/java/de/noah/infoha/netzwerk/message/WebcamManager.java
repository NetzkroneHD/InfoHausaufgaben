package de.noah.infoha.netzwerk.message;

import com.github.sarxos.webcam.Webcam;

public class WebcamManager {

    private Webcam webcam;

    public WebcamManager() {
        this.webcam = Webcam.getDefault();
    }



    public Webcam getWebcam() {
        return webcam;
    }
}
