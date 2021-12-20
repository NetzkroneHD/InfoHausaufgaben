package de.noah.infoha.automatentheorie.binary;

import de.noah.infoha.abiturklassen.BinaryTree;

import javax.swing.*;

public class AkzeptatorBinaryTree {


    public static void main(String[] args) {

        final AkzeptatorBinaryTree akzeptator = new AkzeptatorBinaryTree();
        akzeptator.lade();



    }


    private final BinaryTree<AkzeptatorResponse> alleEingaben;

    public AkzeptatorBinaryTree() {
        AkzeptatorGui gui = new AkzeptatorGui();
        alleEingaben = new BinaryTree<>();
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
            final Traversierungen t = new Traversierungen();

            t.traversiereBaum(Traversierungen.Type.INORDER, alleEingaben);
            System.out.println(t.getBaumString());
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
                new Traversierungen().fuegeEin(alleEingaben, res);

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
                        path.append(" -> S").append(z);
                    }
                    break;
            }
        }

        res.setAccept((z == 0));
        res.setLastState(z);
        new Traversierungen().fuegeEin(alleEingaben, res);

    }

    public BinaryTree<AkzeptatorResponse> getAlleEingaben() {
        return alleEingaben;
    }


}
