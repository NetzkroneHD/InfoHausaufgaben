package de.noah.infoha.automatentheorie.binary;

import de.noah.infoha.abiturklassen.BinarySearchTree;
import de.noah.infoha.abiturklassen.ComparableContent;
import de.noah.infoha.abiturklassen.List;

import javax.swing.*;
import java.util.Objects;

public class AkzeptatorSearchTree {


    public static void main(String[] args) {

        final AkzeptatorSearchTree akzeptator = new AkzeptatorSearchTree();
        akzeptator.lade();



    }


    private final AkzeptatorGui gui;
    private final BinarySearchTree<AkzeptatorResponse> alleEingaben;

    public AkzeptatorSearchTree() {
        gui = new AkzeptatorGui();
        alleEingaben = new BinarySearchTree<>();
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

            t.inorder(alleEingaben);
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
                alleEingaben.insert(res);
                return;
            }
            if(z == 0) {
                if(e == 0) {
                    z = 0;
                    path.append(" -> S"+z);
                } else if(e == 1) {
                    z = 1;
                    path.append(" -> S"+z);

                }
            } else if(z == 1) {
                if(e == 0) {
                    z = 2;
                    path.append(" -> S"+z);
                } else if(e == 1) {
                    z = 0;
                    path.append(" -> S"+z);
                }
            } else if(z == 2) {
                if(e == 0) {
                    z = 1;
                    path.append(" -> S"+z);
                } else if(e == 1) {
                    z = 2;
                    path.append(" -> S"+z);
                }
            }
        }

        res.setAccept((z == 0));
        res.setLastState(z);
        alleEingaben.insert(res);

    }

    public BinarySearchTree<AkzeptatorResponse> getAlleEingaben() {
        return alleEingaben;
    }

}
