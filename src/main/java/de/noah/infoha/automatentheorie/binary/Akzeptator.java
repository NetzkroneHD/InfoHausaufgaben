package de.noah.infoha.automatentheorie.binary;

import de.noah.infoha.abiturklassen.List;

import javax.swing.*;

public class Akzeptator {


    public static void main(String[] args) {

        final Akzeptator akzeptator = new Akzeptator();
        akzeptator.lade();



    }


    private final List<AkzeptatorResponse> alleEingaben;

    public Akzeptator() {
        AkzeptatorGui gui = new AkzeptatorGui();
        alleEingaben = new List<>();
        gui.setVisible(true);
    }

    public void lade() {
        final String input = JOptionPane.showInputDialog("Bitte gib eine Binärzahl an.");
        if(input != null && !input.equalsIgnoreCase("")) {
            try {
                check(input);
            } catch (NumberFormatException e) {
                System.out.println("Fehlerhafte Eingabe: '"+input+"'. Bitte gib eine Binärzahl ein.");
            }
            lade();
        } else {
            final StringBuilder sb = new StringBuilder();
            alleEingaben.toFirst();
            while(alleEingaben.hasAccess()) {
                sb.append("-----------\n");
                sb.append(alleEingaben.getContent().toString());
                sb.append("\n");
                alleEingaben.next();
            }
            System.out.println(sb);
        }
    }

    public void check(String binary) throws NumberFormatException {


        final StringBuilder path = new StringBuilder();
        int z = 0;

        final AkzeptatorResponse res = new AkzeptatorResponse(binary, path, false, z);

        path.append("Start");

        for(int i = 0; i < binary.length(); i++) {
            final int e = Integer.parseInt(binary.charAt(i)+"");
            if(e > 1) {
                path.append("Ungültige eingabe.");
                res.setAccept(false);
                alleEingaben.append(res);
                return;
            }

            switch (z) {
                case 0:
                    if(e == 0) {
                        z = 0;
                        path.append(" -> S").append(z);
                    } else if(e == 1) {
                        z = 1;
                        path.append(" -> S").append(z);
                    }
                    break;
                case 1:
                    if(e == 0) {
                        z = 2;
                        path.append(" -> S").append(z);
                    } else if(e == 1) {
                        z = 0;
                        path.append(" -> S").append(z);
                    }
                    break;
                case 2:
                    if(e == 0) {
                        z = 1;
                        path.append(" -> S").append(z);
                    } else if(e == 1) {
                        z = 2;
                        path.append(" -> S"+z);
                    }
                    break;
            }

        }

        res.setAccept((z == 0));
        res.setLastState(z);
        alleEingaben.append(res);

    }

    public List<AkzeptatorResponse> getAlleEingaben() {
        return alleEingaben;
    }

}
