package de.noah.infoha.netzwerk.message;

import com.google.gson.Gson;

public class DataTransfer {

    private final String clientIp;
    private final int clientPort;
    private final Object value;

    public DataTransfer(String clientIp, int clientPort, Object value) {
        this.clientIp = clientIp;
        this.clientPort = clientPort;
        this.value = value;
    }

    public String getClientIp() {
        return clientIp;
    }

    public int getClientPort() {
        return clientPort;
    }

    public Object getValue() {
        return value;
    }

    public String toJson() {
        return new Gson().toJson(this);
    }

    public static DataTransfer fromJson(String json) {
        return new Gson().fromJson(json, DataTransfer.class);
    }

}
