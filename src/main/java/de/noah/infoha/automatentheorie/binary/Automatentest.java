package de.noah.infoha.automatentheorie.binary;

import de.noah.infoha.abiturklassen.List;

import javax.swing.*;

public class Automatentest {

    public static void main(String[] args) {

        Automatentest a = new Automatentest();
        a.lade();

    }

    private final List<AkzeptatorResponse> alleEingaben;

    public Automatentest() {
        alleEingaben = new List<>();
    }


    public void lade() {
        final String input = JOptionPane.showInputDialog("Bitte gib eine Binärzahl an.");
        if(input != null && !input.equalsIgnoreCase("")) {
            try {
                alleEingaben.append(teste(input));
            } catch (NumberFormatException e) {
                System.out.println("Fehlerhafte Eingabe: '"+input+"'. Bitte gib eine Binärzahl ein.");
            }
            lade();
        } else {
            final StringBuilder sb = new StringBuilder();

            alleEingaben.toFirst();
            while(alleEingaben.hasAccess()) {
                sb.append(alleEingaben.getContent().toString());
                alleEingaben.next();
            }
            System.out.println(sb);
        }
    }

    public AkzeptatorResponse teste(String eingabe) {

        int z = 0;
        final StringBuilder path = new StringBuilder();
        final AkzeptatorResponse res = new AkzeptatorResponse(eingabe, path, false, z);
        path.append("Start");


        for(int i = 0; i < eingabe.length(); i++) {
            final int e = Integer.parseInt(eingabe.charAt(i)+"");

            if(e > 1) {
                path.append("Ungültige eingabe.");
                res.setAccept(false);
                return res;
            }

            switch (z) {
                case 0:
                    if(e == 0) {
                        z = 1;
                        path.append(" -> q"+z);
                    } else if(e == 1) {
                        z = 3;
                        path.append(" -> q"+z);
                    }
                    break;
                case 1:
                    if(e == 0) {
                        z = 1;
                        path.append(" -> q"+z);
                    } else if(e == 1) {
                        z = 2;
                        path.append(" -> q"+z);
                    }
                    break;
                case 2:
                    if(e == 0) {
                        z = 5;
                        path.append(" -> q"+z);
                    } else if(e == 1) {
                        z = 5;
                        path.append(" -> q"+z);
                    }
                    break;
                case 3:
                    if(e == 0) {
                        z = 1;
                        path.append(" -> q"+z);
                    } else if(e == 1) {
                        z = 6;
                        path.append(" -> q"+z);
                    }
                    break;
                case 4:
                    if(e == 0) {
                        z = 2;
                        path.append(" -> q"+z);
                    } else if(e == 1) {
                        z = 4;
                        path.append(" -> q"+z);
                    }
                    break;
                case 5:
                    if(e == 0) {
                        z = 4;
                        path.append(" -> q"+z);
                    } else if(e == 1) {
                        z = 4;
                        path.append(" -> q"+z);
                    }
                    break;
                case 6:
                    if(e == 0) {
                        z = 5;
                        path.append(" -> q"+z);
                    } else if(e == 1) {
                        z = 7;
                        path.append(" -> q"+z);
                    }
                    break;
                case 7:
                    if(e == 0) {
                        z = 4;
                        path.append(" -> q7");
                    } else if(e == 1) {
                        z = 4;
                        path.append(" -> q7");
                    }
                    break;

            }

        }
        res.setAccept((z == 4));
        res.setLastState(z);

        return res;

    }

}
