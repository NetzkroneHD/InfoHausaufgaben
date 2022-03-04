package de.noah.infoha.quizspiel;

import de.noah.infoha.abiturklassen.DatabaseConnector;
import de.noah.infoha.abiturklassen.DatabaseConnectorSqlite;
import de.noah.infoha.abiturklassen.List;
import de.noah.infoha.abiturklassen.QueryResult;

import javax.swing.*;
import java.io.File;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Quizspiel {

    //Anbindung an die Datenbank
    private DatabaseConnector connector;

    //ID-Nummer des aktuellen Spielers
    private String aktuelleSpielerID;

    //Aufgaben, die aus der Datenbank ausgelesen wurden und in Zufallsreihenfolge gespielt werden.
    private List<Aufgabe> offeneAufgaben;
    private int anzahlOffeneAufgaben;

    //Statistikdaten
    private int korrekteBearbeitungenSpiel, bearbeitungenSpiel;
    private int korrekteBearbeitungenGesamt, bearbeitungenGesamt;

    /**
     * Spieler(SpielerID, Benutzername, Passwort)
     * Aufgabe(AufgabeID, Frage, KorrekteAntwort, FalscheAntwortA, FalscheAntwortB, FalscheAntwortC)
     * hatBearbeitet(SpielerID, AufgabeID, AnzahlBearbeitungen, AnzahlKorrekteBearbeitungen)
     */

    public Quizspiel() {
        //Aktuellen Nutzer initialisieren.
        aktuelleSpielerID = null;
        korrekteBearbeitungenSpiel = 0;
        bearbeitungenSpiel = 0;
        korrekteBearbeitungenGesamt = 0;
        bearbeitungenGesamt = 0;

        //Verbindung zur Datenbank aufbauen.
        anzahlOffeneAufgaben = 0;

        connector = new DatabaseConnectorSqlite("", 3306, new File("Quiz", "Quizdatenbank.db").getAbsolutePath(), "", "");
        if (connector.getErrorMessage() != null) {
            JOptionPane.showMessageDialog(null, "Verbindung zur Datenbank nicht möglich. \n" + connector.getErrorMessage(), "Fehlermeldung", JOptionPane.ERROR_MESSAGE, null);
            System.exit(0);
        }

        //Feld fuer Aufgaben erstellen.
        offeneAufgaben = new List<>();
    }

    public void anmelden(String pBenutzer, String pPasswort) {
        //ID fuer den Benutzer ermitteln.
        //Ueberpruefen, ob Anmeldung erfolgreich war.

        //Statement wird ausgeführt
        connector.executeStatement("SELECT SpielerID FROM Spieler WHERE Benutzername='"+pBenutzer+"'");
        //Überprüfen vom Ergebnis
        if(hasData(connector.getCurrentQueryResult())) {
            //SpielerID wird eingetragen
            String spielerId = connector.getCurrentQueryResult().getData()[0][0];

            connector.executeStatement("SELECT Passwort FROM Spieler WHERE SpielerID='"+spielerId+"'");
            //Passwort wird überprüft
            String password = connector.getCurrentQueryResult().getData()[0][0];
            if(pPasswort.equals(password)) {
                aktuelleSpielerID = spielerId;

                connector.executeStatement("SELECT AnzahlBearbeitungen, AnzahlKorrekteBearbeitungen FROM hatBearbeitet WHERE SpielerID='"+spielerId+"'");

                if(hasData(connector.getCurrentQueryResult())) {
                    for(int i = 0; i < connector.getCurrentQueryResult().getData().length; i++) {
                        korrekteBearbeitungenGesamt = korrekteBearbeitungenGesamt+Integer.parseInt(connector.getCurrentQueryResult().getData()[i][1]);
                        bearbeitungenGesamt = bearbeitungenGesamt+Integer.parseInt(connector.getCurrentQueryResult().getData()[i][0]);

                    }

                } else {
                    korrekteBearbeitungenGesamt = 0;
                    bearbeitungenGesamt = 0;
                }


            }

        }

    }

    private boolean hasData(QueryResult result) {
        return connector.getCurrentQueryResult().getData().length > 0 && connector.getCurrentQueryResult().getData()[0].length > 0;
    }

    public boolean istAngemeldet() {
        return aktuelleSpielerID != null;
    }

    public Aufgabe gibAufgabe() {
        //Ueberpruefen, ob noch offene Aufgaben in der Pufferliste sind.

        if(!offeneAufgaben.isEmpty()) {
            offeneAufgaben.toFirst();
            final Aufgabe aufgabe = offeneAufgaben.getContent();
            offeneAufgaben.remove();
            anzahlOffeneAufgaben--;
            return aufgabe;
        } else {
            //SQL-Anweisung: Aufgaben nach Anzahl der Bearbeitungen durch den aktuellen Spieler aufsteigend sortiert abfragen.
            //Die zwanzig am wenigsten durch den aktuellen Spieler bearbeiteten Aufgaben auslesen.
            //Zufallsaufgabe ermitteln.

            /**
             * Spieler(SpielerID, Benutzername, Passwort)
             * Aufgabe(AufgabeID, Frage, KorrekteAntwort, FalscheAntwortA, FalscheAntwortB, FalscheAntwortC)
             * hatBearbeitet(SpielerID, AufgabeID, AnzahlBearbeitungen, AnzahlKorrekteBearbeitungen)
             */

            connector.executeStatement("SELECT * FROM hatBearbeitet WHERE SpielerID='"+aktuelleSpielerID+"'");
            int berarbeiteteAufgaben = connector.getCurrentQueryResult().getData().length;

            connector.executeStatement("SELECT * FROM Aufgabe");
            int anzahlAufgaben = connector.getCurrentQueryResult().getData().length;

            if(berarbeiteteAufgaben < anzahlAufgaben) {
                ladeRandomAufgaben(20);
            } else {
                connector.executeStatement("SELECT Aufgabe.AufgabeID, hatBearbeitet.SpielerID, hatBearbeitet.AnzahlBearbeitungen " +
                        "FROM Aufgabe " +
                        "INNER JOIN hatBearbeitet " +
                        "ON Aufgabe.AufgabeID=hatBearbeitet.AufgabeID " +
                        "WHERE SpielerID='"+aktuelleSpielerID+"' " +
                        "ORDER BY hatBearbeitet.AnzahlBearbeitungen ASC");

                if(hasData(connector.getCurrentQueryResult())) {
                    if(connector.getCurrentQueryResult().getData().length < 20) {
                        ladeRandomAufgaben(20);
                    } else {
                        final String[][] data = connector.getCurrentQueryResult().getData();
                        for(int i = 0; i < 20; i++) {
                            connector.executeStatement("SELECT * FROM Aufgabe WHERE AufgabeID='"+data[i][0]+"'");
                            offeneAufgaben.append(gibAufgabe(connector.getCurrentQueryResult()));
                            anzahlOffeneAufgaben++;
                        }

                    }
                }
            }

        }

        offeneAufgaben.toFirst();

        return offeneAufgaben.getContent();
    }

    private void ladeRandomAufgaben(int limit) {
        connector.executeStatement("SELECT * FROM Aufgabe");

        final String[][] data = connector.getCurrentQueryResult().getData();
        final Random random = new Random();
        final Set<Integer> done = new HashSet<>();

        for(int i = 0; i < limit; i++) {
            int r = random.nextInt(data.length);
            while(done.contains(r)) {
                r = random.nextInt(data.length);
            }
            done.add(r);
            offeneAufgaben.append(new Aufgabe(data[r][0], data[r][1], data[r][2], data[r][3], data[r][4], data[r][5]));
            anzahlOffeneAufgaben++;
        }
    }

    private Aufgabe gibAufgabe(QueryResult result) {
        return new Aufgabe(result.getData()[0][0], result.getData()[0][1], result.getData()[0][2], result.getData()[0][3], result.getData()[0][4], result.getData()[0][5]);
    }

    private void print(QueryResult result) {
        if(hasData(result)) {
            System.out.println(Arrays.toString(result.getColumnNames()));

            for(int i = 0; i<  result.getData().length; i++) {
                System.out.println(i+". "+ Arrays.toString(result.getData()[i]));
            }
        } else System.out.println("Keine Daten vorhanden.");
    }

    public void abgebenAufgabe(Aufgabe pAufgabe) {
        //Abgegebene Aufgabe aus der Liste der offenen Aufgaben entfernen.
        //Anzahl der noch offenen Fragen reduzieren.
        //SQL-Anweisung: Anzahl der Eintraege zum aktuellen Spieler und der aktuellen Aufgabe aus der Verknuepfungstabelle ermitteln.
        //Bestehenden Eintrag in Verknüpfungstabelle aktualisieren.
        //SQL-Anweisung: Anzahl der bisherigen Bearbeitungen generell und der korrekten Bearbeitungen abfragen.
        //Werte entsprechen der aktuellen Bearbeitung aktualisieren.
        //Neue Werte in die Datenbank uebertragen.
        offeneAufgaben.toFirst();
        while (offeneAufgaben.hasAccess()) {
            if(offeneAufgaben.getContent().gibAufgabeID().equals(pAufgabe.gibAufgabeID())) {
                offeneAufgaben.remove();
                break;
            }
            offeneAufgaben.next();
        }
        anzahlOffeneAufgaben--;

        connector.executeStatement("SELECT * FROM hatBearbeitet WHERE SpielerID='"+aktuelleSpielerID+"' AND AufgabeID='"+pAufgabe.gibAufgabeID()+"'");
        if(hasData(connector.getCurrentQueryResult())) {
            int bearbeitet = Integer.parseInt(connector.getCurrentQueryResult().getData()[0][2]);
            int korrekt = Integer.parseInt(connector.getCurrentQueryResult().getData()[0][3]);
            bearbeitet++;
            if(pAufgabe.korrektBeantwortet()) korrekt++;
            connector.executeStatement("UPDATE hatBearbeitet SET AnzahlBearbeitungen="+bearbeitet+", AnzahlKorrekteBearbeitungen="+korrekt+" WHERE SpielerID='"+aktuelleSpielerID+"' AND AufgabeID='"+pAufgabe.gibAufgabeID()+"'");
        } else {
            connector.executeStatement("INSERT INTO hatBearbeitet(SpielerID, AufgabeID, AnzahlBearbeitungen, AnzahlKorrekteBearbeitungen) VALUES " +
                    "('"+aktuelleSpielerID+"', '"+pAufgabe.gibAufgabeID()+"', 1, "+(pAufgabe.korrektBeantwortet() ? 1:0)+")");
        }


        if(pAufgabe.korrektBeantwortet()) {
            korrekteBearbeitungenSpiel++;
            korrekteBearbeitungenGesamt++;
        }
        bearbeitungenSpiel++;
        bearbeitungenGesamt++;




    }

    public int gibKorrekteBearbeitungenGesamt() {
        return korrekteBearbeitungenGesamt;
    }

    public int gibBearbeitungenGesamt() {
        return bearbeitungenGesamt;
    }

    public int gibKorrekteBearbeitungenSpiel() {
        return korrekteBearbeitungenSpiel;
    }

    public int gibBearbeitungenSpiel() {
        return bearbeitungenSpiel;
    }

}

