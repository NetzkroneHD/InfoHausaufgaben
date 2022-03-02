package de.noah.infoha.quizspiel;

import de.noah.infoha.abiturklassen.*;

import javax.swing.*;

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

    public Quizspiel() {
        //Aktuellen Nutzer initialisieren.
        aktuelleSpielerID = null;
        korrekteBearbeitungenSpiel = 0;
        bearbeitungenSpiel = 0;
        korrekteBearbeitungenGesamt = 0;
        bearbeitungenGesamt = 0;

        //Verbindung zur Datenbank aufbauen.
        anzahlOffeneAufgaben = 0;
        connector = new DatabaseConnector("localhost", 3306, "Quizdatenbank", "root", "");
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
        if(connector.getCurrentQueryResult().getData().length > 0) {
            //SpielerID wird eingetragen
            String spielerId = connector.getCurrentQueryResult().getData()[0][0];

            connector.executeStatement("SELECT Passwort FROM Spieler WHERE SpielerID='"+spielerId+"'");
            //Passwort wird überprüft
            String password = connector.getCurrentQueryResult().getData()[0][0];
            if(pPasswort.equals(password)) {
                aktuelleSpielerID = spielerId;

                connector.executeStatement("SELECT AnzahlBearbeitungen, AnzahlKorrekteBearbeitungen FROM hatBearbeitet WHERE SpielerID='"+spielerId+"'");

                korrekteBearbeitungenSpiel = Integer.parseInt(connector.getCurrentQueryResult().getData()[1][0]);
                bearbeitungenSpiel = Integer.parseInt(connector.getCurrentQueryResult().getData()[0][0]);


            }

        }




    }

    public boolean istAngemeldet() {
        return aktuelleSpielerID != null;
    }

    public Aufgabe gibAufgabe() {
        //Ueberpruefen, ob noch offene Aufgaben in der Pufferliste sind.

        //SQL-Anweisung: Aufgaben nach Anzahl der Bearbeitungen durch den aktuellen Spieler aufsteigend sortiert abfragen.


        //Die zwanzig am wenigsten durch den aktuellen Spieler bearbeiteten Aufgaben auslesen.


        //Zufallsaufgabe ermitteln.

        return offeneAufgaben.getContent();
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

        if(pAufgabe.korrektBeantwortet()) korrekteBearbeitungenGesamt++;



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

