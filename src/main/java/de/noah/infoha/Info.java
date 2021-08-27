package de.noah.infoha;

import de.noah.infoha.verschluesselung.Caesar;
import de.noah.infoha.verschluesselung.Verschluesselung;

public class Info {

    public static void main(String[] args) {
        final Verschluesselung caesar = new Caesar(2);

        System.out.println(caesar.codiere("INFORMATIK"));
        System.out.println(caesar.decodiere(caesar.codiere("INFORMATIK")));

    }
}
