package de.noah.infoha.binarytrees.informatiker;

import de.noah.infoha.abiturklassen.ComparableContent;

public class Informatiker implements ComparableContent<Informatiker> {

  private final String name;
  private final String gebDatum;

  public Informatiker(String pName, String pDatum) {
    super();
    name = pName;
    gebDatum = pDatum;
  }

  public String gibName() {
    return name;
  }

  public String gibGebDatum() {
    return gebDatum;
  }

  public boolean isLess(Informatiker pContent) {
    return this.gibName().compareTo(pContent.gibName())<0;
  }

  public boolean isEqual(Informatiker pContent) {
    return this.gibName().compareTo(pContent.gibName())==0;
  }

  public boolean isGreater(Informatiker pContent) {
    return this.gibName().compareTo(pContent.gibName())>0;
  }
  
  public String toString() {
    return name + " *" + gebDatum;
  }

}
