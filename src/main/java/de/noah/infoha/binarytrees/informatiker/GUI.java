package de.noah.infoha.binarytrees.informatiker;
/**
 * <p>Qualitaets- und UnterstuetzungsAgentur - Landesinstitut fuer Schule, Materialien zum schulinternen Lehrplan Informatik SII</p>
 *
 * @version 2014-03-13
 */

import java.awt.*;
import java.awt.event.*;

/**
 * Beschreibung
 *
 * @author
 * @version 1.0 vom 11.03.2012
 */

public class GUI extends Frame {
    // Anfang Attribute
    private final TextField tfName = new TextField();
    private final TextField tfDatum = new TextField();

    IFBaum baum = new IFBaum();
    BaumZeichner zeichner;

    private final TextArea textArea1 = new TextArea("", 1, 1, TextArea.SCROLLBARS_BOTH);
    // Ende Attribute

    public GUI(String title) {
        // Frame-Initialisierung
        super(title);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent evt) {
                System.exit(0);
            }
        });

        int frameWidth = 299;
        int frameHeight = 497;
        setSize(frameWidth, frameHeight);
        setLocation(10, 20);
        setResizable(true);
        Panel cp = new Panel(null);
        add(cp);
        // Anfang Komponenten

        tfName.setBounds(16, 40, 113, 33);
        cp.add(tfName);
        tfDatum.setBounds(152, 40, 113, 33);
        cp.add(tfDatum);
        Label label1 = new Label();
        label1.setBounds(8, 8, 115, 25);
        label1.setText("Name");
        cp.add(label1);
        Label label2 = new Label();
        label2.setBounds(152, 8, 107, 25);
        label2.setText("Datum");
        cp.add(label2);
        Button button1 = new Button();
        button1.setBounds(152, 88, 113, 33);
        button1.setLabel("Beispiel");
        button1.addActionListener(this::button1_ActionPerformed);
        cp.add(button1);
        Button button2 = new Button();
        button2.setBounds(16, 88, 113, 33);
        button2.setLabel("einf�gen");
        button2.addActionListener(this::button2_ActionPerformed);
        cp.add(button2);
        Button button3 = new Button();
        button3.setBounds(16, 136, 113, 33);
        button3.setLabel("suchen");
        button3.addActionListener(this::button3_ActionPerformed);
        cp.add(button3);
        Button button4 = new Button();
        button4.setBounds(16, 184, 113, 33);
        button4.setLabel("löschen");
        button4.addActionListener(this::button4_ActionPerformed);
        cp.add(button4);
        Button button6 = new Button();
        button6.setBounds(168, 432, 113, 33);
        button6.setLabel("Ende");
        button6.addActionListener(this::button6_ActionPerformed);
        button6.setBackground(new Color(0xEEEEFF));
        cp.add(button6);
        Button button7 = new Button();
        button7.setBounds(152, 136, 113, 33);
        button7.setLabel("leeren");
        button7.addActionListener(this::button7_ActionPerformed);
        cp.add(button7);
        textArea1.setBounds(8, 232, 273, 185);
        cp.add(textArea1);
        Button button8 = new Button();
        button8.setBounds(152, 184, 115, 33);
        button8.setLabel("ausgeben");
        button8.addActionListener(this::button8_ActionPerformed);
        cp.add(button8);
        // Ende Komponenten

        setVisible(true);
        zeichner = new BaumZeichner(600, 400, baum.gibBaum());
    }

    // Anfang Methoden
    public void button1_ActionPerformed(ActionEvent evt) {
        // Beispielbaum erzeugen
        baum = new IFBaum();
        baum.ergaenze("Huffman", "09.08.1925");
        baum.ergaenze("Boole", "08.12.1864");
        baum.ergaenze("Shannon", "30.04.1916");
        baum.ergaenze("Adelson", "08.01.1922");
        baum.ergaenze("Zuse", "22.06.1910");
        baum.ergaenze("Dijkstra", "11.05.1930");
        baum.ergaenze("Wirth", "15.02.1934");
        baum.ergaenze("Jobs", "24.02.1955");
        zeichner.zeigeBaum(baum.gibBaum());
    }

    public void button2_ActionPerformed(ActionEvent evt) {
        // Informatiker einfügen
        baum.ergaenze(tfName.getText(), tfDatum.getText());
        zeichner.markiere(tfName.getText() + tfDatum.getText());
        zeichner.zeigeBaum();
    }

    public void button3_ActionPerformed(ActionEvent evt) {
        // suchen
        String name = tfName.getText();
        textArea1.append(baum.suche(name));
        zeichner.markiere(tfName.getText() + tfDatum.getText());
        zeichner.zeigeBaum();
    }

    public void button4_ActionPerformed(ActionEvent evt) {
        // l�schen
        baum.loesche(tfName.getText());
        zeichner.zeigeBaum();
    }

    public void button6_ActionPerformed(ActionEvent evt) {
        // Ende
        System.exit(0);
    }

    public void button7_ActionPerformed(ActionEvent evt) {
        // Baum leeren
        baum.leereBaum();
        zeichner.zeigeBaum(baum.gibBaum());
    }

    public void button8_ActionPerformed(ActionEvent evt) {
        textArea1.append(baum.toString());
    } // end of button8_ActionPerformed

    // Ende Methoden

    public static void main(String[] args) {
        new GUI("Informatiker-Demo");
    }
}
