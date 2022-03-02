package de.noah.infoha.quizspiel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Aufgabenstellung
 */
public class GUI extends JFrame {

    //GUI-Komponenten
    private JPanel anmeldung = new JPanel(null, true);
    private JLabel textfeld1 = new JLabel();
    private JLabel textfeld2 = new JLabel();
    private JLabel textfeld3 = new JLabel();
    private JTextField eingabefeldBenutzername = new JTextField();
    private JTextField eingabefeldPasswort = new JTextField();
    private JButton knopfAnmelden = new JButton();
    private JPanel spielaufgaben = new JPanel(null, true);
    private JTextArea textfeldFrage = new JTextArea("");
    private JScrollPane textfeldFrageScrollPane = new JScrollPane(textfeldFrage);
    private JButton knopfAntwortA = new JButton();
    private JButton knopfAntwortB = new JButton();
    private JButton knopfAntwortC = new JButton();
    private JButton knopfAntwortD = new JButton();
    private JPanel steuerung = new JPanel(null, true);
    private JButton knopfBeenden = new JButton();
    private JButton knopfInfo = new JButton();
    private JLabel textfeldSpieler = new JLabel();
    private JLabel textfeldWertung = new JLabel();
    private JLabel textfeldWertungEinzeln = new JLabel();
    private JLabel textfeldGesamtwertung = new JLabel();
    private JLabel textfeldGesamtwertungEinzeln = new JLabel();
    private JLabel textfeld5 = new JLabel();
    private JLabel textfeld6 = new JLabel();

    //Quizspiel
    private Quizspiel quizspiel;
    private Aufgabe aktuelleAufgabe;
    private Timer ausgabenWechsler;

    public GUI() {
        // Frame-Initialisierung
        super("Quizspiel");

        //Spielaufgaben verstecken
        spielaufgaben.setVisible(false);

        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        int frameWidth = 600;
        int frameHeight = 400;
        this.setSize(frameWidth, frameHeight);
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (d.width - getSize().width) / 2;
        int y = (d.height - getSize().height) / 2;
        this.setLocation(x, y);
        this.setResizable(false);
        Container container = getContentPane();
        container.setLayout(null);

        // Anfang Komponenten
        anmeldung.setBounds(24, 16, 433, 320);
        anmeldung.setOpaque(false);
        anmeldung.setBorder(BorderFactory.createEtchedBorder(0, Color.BLACK, Color.GRAY));
        container.add(anmeldung);

        spielaufgaben.setBounds(24, 16, 433, 320);
        spielaufgaben.setOpaque(false);
        spielaufgaben.setBorder(BorderFactory.createEtchedBorder(0, Color.BLACK, Color.GRAY));
        container.add(spielaufgaben);

        textfeld1.setBounds(104, 40, 234, 41);
        textfeld1.setText("Anmeldung");
        textfeld1.setFont(new Font("Dialog", Font.BOLD, 30));
        textfeld1.setVerticalTextPosition(SwingConstants.CENTER);
        textfeld1.setVerticalAlignment(SwingConstants.CENTER);
        textfeld1.setHorizontalAlignment(SwingConstants.CENTER);
        anmeldung.add(textfeld1);

        textfeld2.setBounds(64, 128, 110, 20);
        textfeld2.setText("Benutzername:");
        anmeldung.add(textfeld2);

        textfeld3.setBounds(64, 176, 110, 20);
        textfeld3.setText("Passwort:");
        anmeldung.add(textfeld3);

        eingabefeldBenutzername.setBounds(200, 128, 150, 20);
        anmeldung.add(eingabefeldBenutzername);

        eingabefeldPasswort.setBounds(200, 176, 150, 20);
        anmeldung.add(eingabefeldPasswort);

        knopfAnmelden.setBounds(144, 240, 131, 25);
        knopfAnmelden.setText("Anmelden");
        knopfAnmelden.setMargin(new Insets(2, 2, 2, 2));
        knopfAnmelden.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                knopfAnmelden_ActionPerformed(evt);
            }
        });
        anmeldung.add(knopfAnmelden);

        steuerung.setBounds(472, 16, 105, 320);
        steuerung.setOpaque(false);
        steuerung.setBorder(BorderFactory.createEtchedBorder(0, Color.BLACK, new Color(0xC0C0C0)));
        container.add(steuerung);

        knopfBeenden.setBounds(5, 270, 95, 25);
        knopfBeenden.setText("Beenden");
        knopfBeenden.setMargin(new Insets(2, 2, 2, 2));
        knopfBeenden.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                knopfBeenden_ActionPerformed(evt);
            }
        });
        steuerung.add(knopfBeenden);

        knopfInfo.setBounds(5, 25, 95, 25);
        knopfInfo.setText("Info");
        knopfInfo.setMargin(new Insets(2, 2, 2, 2));
        knopfInfo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                knopfInfo_ActionPerformed(evt);
            }
        });
        steuerung.add(knopfInfo);

        textfeldSpieler.setBounds(3, 150, 99, 20);
        textfeldSpieler.setText("-");
        textfeldSpieler.setHorizontalAlignment(SwingConstants.CENTER);
        textfeldSpieler.setFont(new Font("Dialog", Font.BOLD, 14));
        textfeldSpieler.setBorder(BorderFactory.createEtchedBorder(0, Color.BLACK, Color.GRAY));
        steuerung.add(textfeldSpieler);

        textfeldWertung.setBounds(8, 60, 86, 41);
        textfeldWertung.setText("0 %");
        textfeldWertung.setHorizontalAlignment(SwingConstants.CENTER);
        textfeldWertung.setFont(new Font("Dialog", Font.BOLD, 28));
        steuerung.add(textfeldWertung);

        textfeldWertungEinzeln.setBounds(8, 85, 86, 41);
        textfeldWertungEinzeln.setText(" 0 / 0");
        textfeldWertungEinzeln.setHorizontalAlignment(SwingConstants.CENTER);
        textfeldWertungEinzeln.setFont(new Font("Dialog", Font.BOLD, 12));
        steuerung.add(textfeldWertungEinzeln);

        textfeldFrageScrollPane.setBounds(8, 24, 417, 153);
        textfeldFrage.setLineWrap(true);
        textfeldFrage.setWrapStyleWord(true);
        textfeldFrage.setEditable(false);
        textfeldFrage.setText("");
        textfeldFrage.setFont(new Font("Dialog", Font.BOLD, 24));
        textfeldFrage.setCaretPosition(0);
        spielaufgaben.add(textfeldFrageScrollPane);

        textfeldGesamtwertung.setBounds(8, 185, 86, 33);
        textfeldGesamtwertung.setText("0 %");
        textfeldGesamtwertung.setFont(new Font("Dialog", Font.BOLD, 28));
        textfeldGesamtwertung.setHorizontalAlignment(SwingConstants.CENTER);
        steuerung.add(textfeldGesamtwertung);

        textfeldGesamtwertungEinzeln.setBounds(8, 210, 86, 33);
        textfeldGesamtwertungEinzeln.setText("0 / 0");
        textfeldGesamtwertungEinzeln.setHorizontalAlignment(SwingConstants.CENTER);
        textfeldGesamtwertungEinzeln.setFont(new Font("Dialog", Font.BOLD, 12));
        steuerung.add(textfeldGesamtwertungEinzeln);

        textfeld5.setBounds(0, 110, 102, 20);
        textfeld5.setText("(Spielwertung)");
        textfeld5.setHorizontalAlignment(SwingConstants.CENTER);
        steuerung.add(textfeld5);

        textfeld6.setBounds(0, 235, 102, 20);
        textfeld6.setText("(Ges.wertung)");
        textfeld6.setHorizontalAlignment(SwingConstants.CENTER);
        steuerung.add(textfeld6);

        knopfAntwortA.setBounds(8, 192, 419, 25);
        knopfAntwortA.setText("-");
        knopfAntwortA.setMargin(new Insets(2, 2, 2, 2));
        knopfAntwortA.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                knopfAntwortA_ActionPerformed(evt);
            }
        });
        knopfAntwortA.setOpaque(true);
        spielaufgaben.add(knopfAntwortA);

        knopfAntwortB.setBounds(8, 224, 419, 25);
        knopfAntwortB.setText("-");
        knopfAntwortB.setMargin(new Insets(2, 2, 2, 2));
        knopfAntwortB.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                knopfAntwortB_ActionPerformed(evt);
            }
        });
        knopfAntwortB.setOpaque(true);
        spielaufgaben.add(knopfAntwortB);

        knopfAntwortC.setBounds(8, 256, 419, 25);
        knopfAntwortC.setText("-");
        knopfAntwortC.setMargin(new Insets(2, 2, 2, 2));
        knopfAntwortC.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                knopfAntwortC_ActionPerformed(evt);
            }
        });
        knopfAntwortC.setOpaque(true);
        spielaufgaben.add(knopfAntwortC);

        knopfAntwortD.setBounds(8, 288, 419, 25);
        knopfAntwortD.setText("-");
        knopfAntwortD.setMargin(new Insets(2, 2, 2, 2));
        knopfAntwortD.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                knopfAntwortD_ActionPerformed(evt);
            }
        });
        knopfAntwortD.setOpaque(true);
        spielaufgaben.add(knopfAntwortD);
        // Ende Komponenten

        //Timer zum Wechseln zu einer neuen Aufgabe.
        ausgabenWechsler = new javax.swing.Timer(2000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //Welchsler bis zur naechsten Verwendung stoppen.
                ausgabenWechsler.stop();

                //Knoepfe zurueck auf die Hintergrundfarbe setzen.
                knopfAntwortA.setBackground(new Color(0xEEEEEE));
                knopfAntwortB.setBackground(new Color(0xEEEEEE));
                knopfAntwortC.setBackground(new Color(0xEEEEEE));
                knopfAntwortD.setBackground(new Color(0xEEEEEE));

                //Statistikdaten aktualisieren
                if (quizspiel.gibBearbeitungenSpiel() != 0)
                    textfeldWertung.setText(quizspiel.gibKorrekteBearbeitungenSpiel() * 100 / quizspiel.gibBearbeitungenSpiel() + " %");
                textfeldWertungEinzeln.setText(quizspiel.gibKorrekteBearbeitungenSpiel() + " / " + quizspiel.gibBearbeitungenSpiel());

                if (quizspiel.gibBearbeitungenGesamt() != 0)
                    textfeldGesamtwertung.setText(quizspiel.gibKorrekteBearbeitungenGesamt() * 100 / quizspiel.gibBearbeitungenGesamt() + " %");
                textfeldGesamtwertungEinzeln.setText(quizspiel.gibKorrekteBearbeitungenGesamt() + " / " + quizspiel.gibBearbeitungenGesamt());

                //Neue Aufgabe laden.
                ladeAufgabe();

                //Antwortknoepfe wieder freigeben.
                knopfAntwortA.setEnabled(true);
                knopfAntwortB.setEnabled(true);
                knopfAntwortC.setEnabled(true);
                knopfAntwortD.setEnabled(true);
            }
        }
        );

        //Quizspiel initialisieren
        quizspiel = new Quizspiel();
        aktuelleAufgabe = null;

        this.setVisible(true);
    }

    public void knopfAnmelden_ActionPerformed(ActionEvent evt) {
        //Spieler in der Datenbank anmelden.
        quizspiel.anmelden(eingabefeldBenutzername.getText(), eingabefeldPasswort.getText());

        if (!quizspiel.istAngemeldet()) { //Fehlermeldung, wenn nicht angemeldet wurde.
            JOptionPane.showMessageDialog(null, "Benutzername oder Passwort falsch.", "Fehlermeldung", JOptionPane.ERROR_MESSAGE, null);
        } else {
            //Spieler anzeigen.
            textfeldSpieler.setText(eingabefeldBenutzername.getText());

            //Erste Aufgabe laden.
            this.ladeAufgabe();

            //Statistikdaten aktualisieren
            if (quizspiel.gibBearbeitungenSpiel() != 0)
                textfeldWertung.setText(quizspiel.gibKorrekteBearbeitungenSpiel() * 100 / quizspiel.gibBearbeitungenSpiel() + " %");
            textfeldWertungEinzeln.setText(quizspiel.gibKorrekteBearbeitungenSpiel() + " / " + quizspiel.gibBearbeitungenSpiel());

            if (quizspiel.gibBearbeitungenGesamt() != 0)
                textfeldGesamtwertung.setText(quizspiel.gibKorrekteBearbeitungenGesamt() * 100 / quizspiel.gibBearbeitungenGesamt() + " %");
            textfeldGesamtwertungEinzeln.setText(quizspiel.gibKorrekteBearbeitungenGesamt() + " / " + quizspiel.gibBearbeitungenGesamt());

            //Vom Anmeldebildschirm zum Spielbildschirm wechseln.
            anmeldung.setVisible(false);
            spielaufgaben.setVisible(true);
        }
    }

    public void knopfBeenden_ActionPerformed(ActionEvent evt) {
        System.exit(0);
    }

    public void knopfInfo_ActionPerformed(ActionEvent evt) {
        String nachricht =
                "QUIZSPIEL Version 1.0 \n" +
                        "---------------------- \n" +
                        "Beispielprojekt zum Unterrichtsvorhaben Q1 - V (Leistungskurs Informatik) \n" +
                        "Lehrplannavigator S II - Informatik (NRW)\n \n" +
                        "Qualitäts- und UnterstützungsAgentur - Landesinstitut für Schule (QUA-LiS) \n" +
                        "Autor: Volker Quade (Juni 2015)";

        JOptionPane.showMessageDialog(null, nachricht, "Information", JOptionPane.INFORMATION_MESSAGE, null);
    }

    public void knopfAntwortA_ActionPerformed(ActionEvent evt) {
        //Antwort setzen und Aufgabe abgeben,
        aktuelleAufgabe.setzeGegebeneAntwort(knopfAntwortA.getText());
        quizspiel.abgebenAufgabe(aktuelleAufgabe);

        //Antwortknoepfe abschalten.
        knopfAntwortA.setEnabled(false);
        knopfAntwortB.setEnabled(false);
        knopfAntwortC.setEnabled(false);
        knopfAntwortD.setEnabled(false);

        //Knoepfe entsprechen der Antwort einfaerben.
        if (aktuelleAufgabe.korrektBeantwortet()) {
            knopfAntwortA.setBackground(Color.GREEN);
        } else {
            knopfAntwortA.setBackground(Color.RED);
            if (knopfAntwortB.getText() == aktuelleAufgabe.gibKorrekteAntwort())
                knopfAntwortB.setBackground(Color.GREEN);
            if (knopfAntwortC.getText() == aktuelleAufgabe.gibKorrekteAntwort())
                knopfAntwortC.setBackground(Color.GREEN);
            if (knopfAntwortD.getText() == aktuelleAufgabe.gibKorrekteAntwort())
                knopfAntwortD.setBackground(Color.GREEN);
        }

        //Wechsler starten, damit erst nach einer Wartezeit die naechst Aufgabe geladen wird.
        ausgabenWechsler.start();
    }

    public void knopfAntwortB_ActionPerformed(ActionEvent evt) {
        //Antwort setzen und Aufgabe abgeben,
        aktuelleAufgabe.setzeGegebeneAntwort(knopfAntwortB.getText());
        quizspiel.abgebenAufgabe(aktuelleAufgabe);

        //Antwortknoepfe abschalten.
        knopfAntwortA.setEnabled(false);
        knopfAntwortB.setEnabled(false);
        knopfAntwortC.setEnabled(false);
        knopfAntwortD.setEnabled(false);

        //Knoepfe entsprechen der Antwort einfaerben.
        if (aktuelleAufgabe.korrektBeantwortet()) {
            knopfAntwortB.setBackground(Color.GREEN);
        } else {
            knopfAntwortB.setBackground(Color.RED);
            if (knopfAntwortA.getText() == aktuelleAufgabe.gibKorrekteAntwort())
                knopfAntwortA.setBackground(Color.GREEN);
            if (knopfAntwortC.getText() == aktuelleAufgabe.gibKorrekteAntwort())
                knopfAntwortC.setBackground(Color.GREEN);
            if (knopfAntwortD.getText() == aktuelleAufgabe.gibKorrekteAntwort())
                knopfAntwortD.setBackground(Color.GREEN);
        }

        //Wechsler starten, damit erst nach einer Wartezeit die naechst Aufgabe geladen wird.
        ausgabenWechsler.start();
    }

    public void knopfAntwortC_ActionPerformed(ActionEvent evt) {
        //Antwort setzen und Aufgabe abgeben,
        aktuelleAufgabe.setzeGegebeneAntwort(knopfAntwortC.getText());
        quizspiel.abgebenAufgabe(aktuelleAufgabe);

        //Antwortknoepfe abschalten.
        knopfAntwortA.setEnabled(false);
        knopfAntwortB.setEnabled(false);
        knopfAntwortC.setEnabled(false);
        knopfAntwortD.setEnabled(false);

        //Knoepfe entsprechen der Antwort einfaerben.
        if (aktuelleAufgabe.korrektBeantwortet()) {
            knopfAntwortC.setBackground(Color.GREEN);
        } else {
            knopfAntwortC.setBackground(Color.RED);
            if (knopfAntwortA.getText() == aktuelleAufgabe.gibKorrekteAntwort())
                knopfAntwortA.setBackground(Color.GREEN);
            if (knopfAntwortB.getText() == aktuelleAufgabe.gibKorrekteAntwort())
                knopfAntwortB.setBackground(Color.GREEN);
            if (knopfAntwortD.getText() == aktuelleAufgabe.gibKorrekteAntwort())
                knopfAntwortD.setBackground(Color.GREEN);
        }

        //Wechsler starten, damit erst nach einer Wartezeit die naechst Aufgabe geladen wird.
        ausgabenWechsler.start();
    }

    public void knopfAntwortD_ActionPerformed(ActionEvent evt) {
        //Antwort setzen und Aufgabe abgeben,
        aktuelleAufgabe.setzeGegebeneAntwort(knopfAntwortD.getText());
        quizspiel.abgebenAufgabe(aktuelleAufgabe);

        //Antwortknoepfe abschalten.
        knopfAntwortA.setEnabled(false);
        knopfAntwortB.setEnabled(false);
        knopfAntwortC.setEnabled(false);
        knopfAntwortD.setEnabled(false);

        //Knoepfe entsprechen der Antwort einfaerben.
        if (aktuelleAufgabe.korrektBeantwortet()) {
            knopfAntwortD.setBackground(Color.GREEN);
        } else {
            knopfAntwortD.setBackground(Color.RED);
            if (knopfAntwortA.getText() == aktuelleAufgabe.gibKorrekteAntwort())
                knopfAntwortA.setBackground(Color.GREEN);
            if (knopfAntwortB.getText() == aktuelleAufgabe.gibKorrekteAntwort())
                knopfAntwortB.setBackground(Color.GREEN);
            if (knopfAntwortC.getText() == aktuelleAufgabe.gibKorrekteAntwort())
                knopfAntwortC.setBackground(Color.GREEN);
        }

        //Wechsler starten, damit erst nach einer Wartezeit die naechst Aufgabe geladen wird.
        ausgabenWechsler.start();
    }

    private void ladeAufgabe() {
        //Neue Aufgabe laden und Frage auf dem Bildschirm anzeigen.
        aktuelleAufgabe = quizspiel.gibAufgabe();
        textfeldFrage.setText(aktuelleAufgabe.gibFrage());

        //Antworten auf den Knoepfen anzeigen.
        String[] tmp = aktuelleAufgabe.gibAntworten();
        knopfAntwortA.setText(tmp[0]);
        knopfAntwortB.setText(tmp[1]);
        knopfAntwortC.setText(tmp[2]);
        knopfAntwortD.setText(tmp[3]);
    }

}
