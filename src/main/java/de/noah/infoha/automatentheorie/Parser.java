package de.noah.infoha.automatentheorie;

public class Parser {


    public static void main(String[] args) {
        final ParserReturn pr = new Parser().isValid("abccc");

        System.out.println("Weg: "+pr.getWeg());
        System.out.println("Letzter Zustand: "+pr.getZustand());
        System.out.println("Ist Wahr: "+pr.isValid());
    }


    public Parser() {

    }


    public ParserReturn isValid(String word) {

        final StringBuilder weg = new StringBuilder();
        weg.append("Start -> S1");
        int zustand = 1;
        final ParserReturn parserReturn = new ParserReturn(false, weg, zustand);


        for(int i = 0; i < word.length(); i++) {
            final char e = word.charAt(i);
            if(zustand == 1) {
                if(e == 'a') {
                    zustand = 2;
                    weg.append(" -> S"+zustand);
                }
            } else if(zustand == 2) {
                if (e == 'a') {
                    zustand = 3;
                    weg.append(" -> S" + zustand);
                } else if (e == 'b') {
                    zustand = 4;
                    weg.append(" -> S" + zustand);
                }
            } else if(zustand == 3) {
                weg.append(" -> Ende (Zu viele Buchstaben)");
                parserReturn.setValid(false);
                parserReturn.setZustand(zustand);
                return parserReturn;
            } else if(zustand == 4) {
                if(e == 'c') {
                    zustand = 5;
                    weg.append(" -> S"+zustand);
                } else if(e == 'b') {
                    zustand = 6;
                    weg.append(" -> S"+zustand);
                }
            } else if(zustand == 5) {
                if(e == 'c') {
                    zustand = 3;
                    weg.append(" -> S"+zustand);
                }
            } else if(zustand == 6) {
                if(e == 'c') {
                    zustand = 7;
                    weg.append(" -> S"+zustand);
                }
            } else if(zustand == 7) {
                if(e == 'c') {
                    zustand = 3;
                    weg.append(" -> S"+zustand);
                }
            }
        }
        parserReturn.setZustand(zustand);
        if(zustand == 1 || zustand == 3 || zustand == 5) parserReturn.setValid(true);

        return (parserReturn);
    }

}
