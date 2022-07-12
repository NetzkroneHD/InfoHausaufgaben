package de.noah.infoha.verschluesselung;

import java.util.HashMap;
import java.util.Map;

public class Caesar extends Verschluesselung {

    protected static final String alphabet = "ABCDEFGHIJKLMNOPQESTUVWXYZ";

    protected final HashMap<Character, Character> keys, values;
    private final int verschoben;

    public Caesar(int verschoben) {
        this.verschoben = verschoben;
        keys = new HashMap<>();
        values = new HashMap<>();
        loadKeys();
    }

    private void loadKeys() {
        final String verschobenerText = alphabet.substring(verschoben)+alphabet.substring(0, verschoben);
        for(int i = 0; i < alphabet.length(); i++) {
            keys.put(alphabet.charAt(i), verschobenerText.charAt(i));
        }

        for(Map.Entry<Character, Character> e : keys.entrySet()) {
            values.put(e.getValue(), e.getKey());
        }
    }

    @Override
    public String codiere(String klartext) {
        final StringBuilder sb = new StringBuilder();
        for(int i = 0; i < klartext.length(); i++) {
            if(keys.containsKey(klartext.charAt(i))) {
                sb.append(keys.get(klartext.charAt(i)));
            } else sb.append(klartext.charAt(i));
        }
        return sb.toString();
    }

    @Override
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