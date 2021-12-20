package de.noah.infoha.automatentheorie.binary;

import de.noah.infoha.abiturklassen.ComparableContent;

public class AkzeptatorResponse implements ComparableContent<AkzeptatorResponse> {

    private final String eingabe;
    private final StringBuilder path;
    private boolean accept;
    private int lastState;

    public AkzeptatorResponse(String eingabe, StringBuilder path, boolean accept, int lastState) {
        this.eingabe = eingabe;
        this.path = path;
        this.accept = accept;
        this.lastState = lastState;
    }

    @Override
    public boolean isEqual(AkzeptatorResponse pContent) {
        return (equals(pContent));
    }

    @Override
    public boolean isGreater(AkzeptatorResponse pContent) {
        //if(lehrer.compareTo(tree.getContent()+"") > 0) { // s1 > s2 = 1 | lehrer ist größer

        return (pContent.getEingabe().compareTo(eingabe) < 0);
        //return (pContent.getLastState() > lastState && pContent.isAccept() && !accept);

    }

    @Override
    public boolean isLess(AkzeptatorResponse pContent) {
        return (pContent.getEingabe().compareTo(eingabe) > 0);
        //return (pContent.getLastState() < lastState && pContent.isAccept() && accept);
    }

    public String getEingabe() {
        return eingabe;
    }

    public StringBuilder getPath() {
        return path;
    }

    public boolean isAccept() {
        return accept;
    }

    public void setAccept(boolean accept) {
        this.accept = accept;
    }

    public int getLastState() {
        return lastState;
    }

    public void setLastState(int lastState) {
        this.lastState = lastState;
    }

    @Override
    public String toString() {
        return "----------------\n" +
                "Eingabe: "+eingabe+"\n" +
                "Dezimal: "+Integer.parseInt(eingabe, 2)+"\n"+
                "Weg: "+path+"\n" +
                "Akzeptiert: "+accept+"\n" +
                "Letzter Zustand: "+lastState+"\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AkzeptatorResponse that = (AkzeptatorResponse) o;
        return (that.getEingabe().compareTo(eingabe) == 0);
    }
}