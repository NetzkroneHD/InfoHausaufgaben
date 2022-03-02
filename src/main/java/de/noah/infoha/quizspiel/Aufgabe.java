package de.noah.infoha.quizspiel;

public class Aufgabe {

    private String frage;
    private String[] antworten;
    private String gegebeneAntwort;
    private String aufgabeID;

    public Aufgabe(String pAufgabeID, String pFrage, String pAntwortKorrekt, String pAntwortFalsch1, String pAntwortFalsch2, String pAntwortFalsch3) {
        antworten = new String[4];
        aufgabeID = pAufgabeID;
        frage = pFrage;
        antworten[0] = pAntwortKorrekt;
        antworten[1] = pAntwortFalsch1;
        antworten[2] = pAntwortFalsch2;
        antworten[3] = pAntwortFalsch3;
    }

    public void setzeGegebeneAntwort(String pAntwort) {
        gegebeneAntwort = pAntwort;
    }

    public boolean korrektBeantwortet() {
        return gegebeneAntwort != null && gegebeneAntwort == antworten[0];
    }

    public String gibFrage() {
        return frage;
    }

    public String[] gibAntworten() {
        //Antworten in neuen String uebertragen.
        String[] ergebnis = new String[4];
        ergebnis[0] = antworten[0];
        ergebnis[1] = antworten[1];
        ergebnis[2] = antworten[2];
        ergebnis[3] = antworten[3];

        //Antworten mischen.
        for (int i = 0; i < 100; i++) {
            int a = (int) (Math.random() * 4);
            int b = (int) (Math.random() * 4);
            String tmp = ergebnis[a];
            ergebnis[a] = ergebnis[b];
            ergebnis[b] = tmp;
        }

        return ergebnis;
    }

    public String gibKorrekteAntwort() {
        return antworten[0];
    }

    public String gibAufgabeID() {
        return aufgabeID;
    }

    //---------------------------
    public String toString() {
        return aufgabeID + ", ";
    }

}
