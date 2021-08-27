package de.noah.infoha.verschluesselung;

import java.util.HashMap;
import java.util.Map;

public abstract class Verschluesselung {

    protected static String alphabet = "ABCDEFGHIJKLMNOPQESTUVWXYZ";
    protected HashMap<Character, Character> keys = new HashMap<>();
    protected HashMap<Character, Character> values = new HashMap<>();

    public abstract void loadKeys();

    public String codiere(String klartext) {
        final StringBuilder sb = new StringBuilder();
        for(int i = 0; i < klartext.length(); i++) {
            if(keys.containsKey(klartext.charAt(i))) {
                sb.append(keys.get(klartext.charAt(i)));
            } else sb.append(klartext.charAt(i));
        }
        return sb.toString();
    }

    public String decodiere(String geheimtext) {
        final StringBuilder sb = new StringBuilder();
        for(int i = 0; i < geheimtext.length(); i++) {
            if(values.containsKey(geheimtext.charAt(i))) {
                sb.append(values.get(geheimtext.charAt(i)));
            } else sb.append(geheimtext.charAt(i));
        }
        return sb.toString();
    }

}