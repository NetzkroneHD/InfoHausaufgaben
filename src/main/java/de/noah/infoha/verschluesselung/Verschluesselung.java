package de.noah.infoha.verschluesselung;

import java.security.NoSuchAlgorithmException;
import java.util.HashMap;

public abstract class Verschluesselung {

    public abstract String codiere(String klartext);

    public abstract String decodiere(String geheimtext);

}