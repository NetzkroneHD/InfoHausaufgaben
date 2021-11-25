package de.noah.infoha.automatentheorie;

import de.noah.infoha.abiturklassen.ComparableContent;

public class Person implements ComparableContent<Person> {

    private String name;
    private int alter;

    public Person(String name, int alter) {
        this.name = name;
        this.alter = alter;
    }



    @Override
    public boolean isGreater(Person pContent) {
        return (alter > pContent.getAlter());
    }

    @Override
    public boolean isEqual(Person pContent) {
        return (alter == pContent.getAlter());
    }

    @Override
    public boolean isLess(Person pContent) {
        return (alter < pContent.getAlter());
    }


    public String getName() {
        return name;
    }

    public int getAlter() {
        return alter;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", alter=" + alter +
                '}';
    }
}
