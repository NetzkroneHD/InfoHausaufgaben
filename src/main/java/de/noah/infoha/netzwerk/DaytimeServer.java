package de.noah.infoha.netzwerk;

import de.noah.infoha.abiturklassen.Server;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

public class DaytimeServer extends Server {


    public DaytimeServer(int pPort) {
        super(pPort);

    }

    @Override
    public void processNewConnection(String pClientIP, int pClientPort) {
        System.out.println("[Server] Client '"+pClientIP+":"+pClientPort+"' hat sich verbunden.");
        send(pClientIP, pClientPort, getDisplayTime(TimeZone.getTimeZone("GMT-8:00")));
    }

    @Override
    public void processMessage(String pClientIP, int pClientPort, String pMessage) {
        System.out.println("[Server] Client '"+pClientIP+":"+pClientPort+"' hat eine Nachricht gesendet: \""+pMessage+"\"");
        if(pMessage.startsWith("gettime ")) {
            try {
                send(pClientIP, pClientPort, getDisplayTime(TimeZone.getTimeZone(pMessage.replace("gettime ", ""))));
            } catch (Exception e) {
                send(pClientIP, pClientPort, "Invalid format. Format mus be \"gettime <timezone>\"");
            }

        }
    }

    @Override
    public void processClosingConnection(String pClientIP, int pClientPort) {
        System.out.println("[Server] Client '"+pClientIP+":"+pClientPort+"' hat die Verbindung unterbrochen.");
    }

    private String getDisplayTime(TimeZone timeZone) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss a z");
        final Calendar c = Calendar.getInstance(timeZone);
        formatter.setCalendar(c);
        return formatter.format(c.getTime());
    }

}
