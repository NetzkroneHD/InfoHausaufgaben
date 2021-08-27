package de.noah.infoha.verschluesselung;

import java.util.Map;

public class Caesar extends Verschluesselung {

    private int verschoben;

    public Caesar(int verschoben) {
        this.verschoben = verschoben;
        loadKeys();

    }

    @Override
    public void loadKeys() {
        final String verschobenerText = alphabet.substring(verschoben)+alphabet.substring(0, verschoben);
        for(int i = 0; i < alphabet.length(); i++) {
            keys.put(alphabet.charAt(i), verschobenerText.charAt(i));
        }

        for(Map.Entry<Character, Character> e : keys.entrySet()) {
            values.put(e.getValue(), e.getKey());
        }

    }
}