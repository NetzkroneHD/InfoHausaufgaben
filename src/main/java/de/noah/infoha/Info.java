package de.noah.infoha;

import de.noah.infoha.netzwerk.ConsolePanel;

public class Info {

    private static ConsolePanel consolePanel;

    public static void main(String[] args) {
        //92.209.162.12:25565
        consolePanel = new ConsolePanel();
        consolePanel.setVisible(true);


    }

    public static ConsolePanel getConsolePanel() {
        return consolePanel;
    }
}
