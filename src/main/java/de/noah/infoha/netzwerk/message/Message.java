package de.noah.infoha.netzwerk.message;

import com.google.gson.Gson;

public class Message {

    private final String clientIP, message;
    private final int clientPort;

    public Message(String clientIP, int clientPort, String message) {
        this.clientIP = clientIP;
        this.clientPort = clientPort;
        this.message = message;
    }

    public String getClientIP() {
        return clientIP;
    }

    public int getClientPort() {
        return clientPort;
    }

    public String getMessage() {
        return message;
    }

    public String toJson() {
        final Gson gson = new Gson();
        return gson.toJson(this);
    }

    public String toDisplayString() {
        return "Sender: "+clientIP+":"+clientPort+"\nNachricht: "+message;
    }

    public static Message fromJson(String json) {
        final Gson gson = new Gson();
        return gson.fromJson(json, Message.class);
    }
}
