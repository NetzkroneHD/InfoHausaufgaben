package de.noah.infoha.automatentheorie;

import de.noah.infoha.abiturklassen.BinarySearchTree;
import de.noah.infoha.abiturklassen.Traversierung;

import java.util.Random;

public class Automat {



    private final BinarySearchTree<Person> alleEingaben;

    public Automat() {
        alleEingaben = new BinarySearchTree<>();

        for(int i = 0; i < 20; i++) {
            String eingabe = "";
            for(int j = 0; j < 10; j++) {
                eingabe = new Random().nextInt(2)+"";
            }

            automat("Per"+new Random().nextInt(10), eingabe);
        }

        gebeAus();

    }

    public static void main(String[] args) {
        new Automat();
    }

    public void gebeAus() {
        String ausgabe = new Traversierung().traversiereBaum(alleEingaben, Traversierung.Type.INORDER);

        System.out.println(ausgabe);
    }

    public void automat(String nameDerPerson, String eingabeAlter) {

        int z = 0;
        for(int i = 0; i < eingabeAlter.length(); i++) {
            final int e = Integer.parseInt(eingabeAlter.charAt(i)+"");
            if(z == 0) {
                if(e == 0) {
                    z = 1;
                } else if(e == 1) {
                    z = 3;
                }
            } else if(z == 1) {
                if(e == 0) {
                    z = 1;
                } else if(e == 1) {
                    z = 2;
                }
            } else if(z == 2) {
                if(e == 0) {
                    z = 5;
                } else if(e == 1) {
                    z = 5;
                }
            } else if(z == 3) {
                if(e == 0) {
                    z = 1;
                } else if(e == 1) {
                    z = 6;
                }
            } else if(z == 4) {
                if(e == 0) {
                    z = 2;
                } else if(e == 1) {
                    z = 4;
                }
            } else if(z == 5) {
                if(e == 0) {
                    z = 4;
                } else if(e == 1) {
                    z = 4;
                }
            } else if(z == 6) {
                if(e == 0) {
                    z = 5;
                } else if(e == 1) {
                    z = 7;
                }
            } else if(z == 7) {
                if(e == 0) {
                    z = 0;
                } else if(e == 1) {
                    z = 1;
                }
            }


        }

        if(z == 4) alleEingaben.insert(new Person(nameDerPerson, Integer.parseInt(eingabeAlter)));


    }

}
