package de.noah.infoha.binarytrees.softwareprojekt;

import de.noah.infoha.abiturklassen.ComparableContent;
import de.noah.infoha.abiturklassen.Vertex;

public class Stadt implements ComparableContent<Stadt> {

    private final String name;
    private final int einwohner;
    private final String landkuerzel;
    private final Vertex vertex;

    public Stadt(String name, int einwohner, String landkuerzel) {
        this.name = name;
        this.einwohner = einwohner;
        this.landkuerzel = landkuerzel;
        vertex = new Vertex(name);
    }

    public String getName() {
        return name;
    }

    public int getEinwohner() {
        return einwohner;
    }

    public String getLandkuerzel() {
        return landkuerzel;
    }

    public Vertex getVertex() {
        return vertex;
    }

    @Override
    public boolean isGreater(Stadt pContent) {
        return (name.compareToIgnoreCase(pContent.getName()) < 0);
    }

    @Override
    public boolean isEqual(Stadt pContent) {
        return name.equalsIgnoreCase(pContent.getName());
    }

    @Override
    public boolean isLess(Stadt pContent) {
        return (name.compareToIgnoreCase(pContent.getName()) > 0);
    }

    @Override
    public String toString() {
        return name;
    }
}
