package de.noah.infoha.automatentheorie;

public class ParserReturn {

    private boolean isValid;
    private StringBuilder weg;
    private int zustand;

    public ParserReturn(boolean isValid, StringBuilder weg, int zustand) {
        this.isValid = isValid;
        this.weg = weg;
        this.zustand = zustand;
    }

    public boolean isValid() {
        return isValid;
    }

    public void setValid(boolean valid) {
        isValid = valid;
    }

    public StringBuilder getWeg() {
        return weg;
    }

    public int getZustand() {
        return zustand;
    }

    public void setZustand(int zustand) {
        this.zustand = zustand;
    }
}
