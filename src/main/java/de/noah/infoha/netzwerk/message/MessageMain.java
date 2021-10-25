package de.noah.infoha.netzwerk.message;

public class MessageMain {

    private static ConsolePanel consolePanel;
    public static final String COMMAND_SEPARATOR = "absdsakjoghfuiarshfdbgihjfdbighdswebfo_sdf";

    public static void main(String[] args) {
        consolePanel = new ConsolePanel();
        consolePanel.setVisible(true);



    }

    public static ConsolePanel getConsolePanel() {
        return consolePanel;
    }
}
