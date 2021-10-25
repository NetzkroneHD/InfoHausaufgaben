package de.noah.infoha.netzwerk.message;

import java.util.Timer;
import java.util.TimerTask;

public class MessageMain {

    private static ConsolePanel consolePanel;

    public static void main(String[] args) {
        consolePanel = new ConsolePanel();
        consolePanel.setVisible(true);

        WebcamManager webcamManager = new WebcamManager();
        webcamManager.getWebcam().open(true);
        Timer timer = new Timer();

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                consolePanel.setIconImage(webcamManager.getWebcam().getImage());
            }
        }, 0, 100);


    }

    public static ConsolePanel getConsolePanel() {
        return consolePanel;
    }
}
