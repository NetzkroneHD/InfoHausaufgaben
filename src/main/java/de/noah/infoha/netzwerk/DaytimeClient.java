package de.noah.infoha.netzwerk;

import de.noah.infoha.abiturklassen.Client;

public class DaytimeClient extends Client {

    public DaytimeClient(String pServerIP, int pServerPort) {
        super(pServerIP, pServerPort);
    }

    @Override
    public void processMessage(String pMessage) {

    }
}
