package de.noah.infoha.abiturklassen;

public class TraversierungExample implements ComparableContent<TraversierungExample> {

    private int example;

    public TraversierungExample(int example) {
        this.example = example;
    }

    public int getExample() {
        return example;
    }

    public void setExample(int example) {
        this.example = example;
    }

    @Override
    public boolean isGreater(TraversierungExample pContent) {
        return (example < pContent.getExample());
    }

    @Override
    public boolean isEqual(TraversierungExample pContent) {
        return (example == pContent.getExample());
    }

    @Override
    public boolean isLess(TraversierungExample pContent) {
        return (example > pContent.getExample());
    }

    @Override
    public String toString() {
        return example+"";
    }
}