package de.noah.infoha.binarytrees.softwareprojekt;

import de.noah.infoha.abiturklassen.*;

import java.util.Scanner;

public class Navigation {

    private final Graph graph;
    private final BinarySearchTree<Stadt> baum;

    public Navigation() {
        graph = new Graph();
        baum = new BinarySearchTree<>();

    }

    public void ladeStaedte() {

        Stadt hamburg = new Stadt("Hamburg", 1000000, "DE");
        Stadt witten = new Stadt("Witten", 100000, "DE");
        Stadt berlin = new Stadt("Berlin", 3000000, "DE");
        Stadt aachen = new Stadt("Aachen", 500000, "DE");
        Stadt paris = new Stadt("Paris", 4000000, "FR");
        Stadt peking = new Stadt("Peking", 100000000, "CH");
        Stadt madrid = new Stadt("Madrid", 30000000, "SP");
        Stadt mallorca = new Stadt("Mallorca", 520000, "SP");
        Stadt dortmund = new Stadt("Dortmund", 500000, "DE");
        Stadt bochum = new Stadt("Bochum", 300000, "DE");
        Stadt lissabon = new Stadt("Lissabon", 1000000, "PT");
        Stadt duisburg = new Stadt("Duisburg", 800000, "DE");

        graph.addVertex(hamburg.getVertex());
        graph.addVertex(witten.getVertex());
        graph.addVertex(berlin.getVertex());
        graph.addVertex(aachen.getVertex());
        graph.addVertex(paris.getVertex());
        graph.addVertex(peking.getVertex());
        graph.addVertex(madrid.getVertex());
        graph.addVertex(mallorca.getVertex());
        graph.addVertex(dortmund.getVertex());
        graph.addVertex(bochum.getVertex());
        graph.addVertex(lissabon.getVertex());
        graph.addVertex(duisburg.getVertex());

        graph.addEdge(new Edge(lissabon.getVertex(), madrid.getVertex(), 1460));
        graph.addEdge(new Edge(lissabon.getVertex(), mallorca.getVertex(), 1200));
        graph.addEdge(new Edge(mallorca.getVertex(), madrid.getVertex(), 670));
        graph.addEdge(new Edge(paris.getVertex(), madrid.getVertex(), 400));
        graph.addEdge(new Edge(paris.getVertex(), aachen.getVertex(), 120));
        graph.addEdge(new Edge(aachen.getVertex(), dortmund.getVertex(), 20));
        graph.addEdge(new Edge(paris.getVertex(), hamburg.getVertex(), 480));
        graph.addEdge(new Edge(hamburg.getVertex(), dortmund.getVertex(), 300));
        graph.addEdge(new Edge(dortmund.getVertex(), witten.getVertex(), 10));
        graph.addEdge(new Edge(dortmund.getVertex(), bochum.getVertex(), 20));
        graph.addEdge(new Edge(witten.getVertex(), bochum.getVertex(), 5));
        graph.addEdge(new Edge(witten.getVertex(), duisburg.getVertex(), 40));
        graph.addEdge(new Edge(duisburg.getVertex(), bochum.getVertex(), 32));
        graph.addEdge(new Edge(bochum.getVertex(), berlin.getVertex(), 260));
        graph.addEdge(new Edge(hamburg.getVertex(), berlin.getVertex(), 320));
        graph.addEdge(new Edge(berlin.getVertex(), peking.getVertex(), 3600));


        baum.insert(hamburg);
        baum.insert(witten);
        baum.insert(berlin);
        baum.insert(aachen);
        baum.insert(paris);
        baum.insert(peking);
        baum.insert(madrid);
        baum.insert(mallorca);
        baum.insert(dortmund);
        baum.insert(bochum);
        baum.insert(lissabon);
        baum.insert(duisburg);

    }

    public void auswahl() {
        System.out.println("Städte: "+new Traversierung().traversiereBaum(baum, Traversierung.Type.INORDER, ", "));
        Scanner sc = new Scanner(System.in);

        System.out.print("Bitte wähle eine Stadt als Startpunkt: ");
        final Stadt start = gibStadt(sc.nextLine());

        System.out.print("Bitte wähle eine Stadt als Ziel: ");
        final Stadt ziel = gibStadt(sc.nextLine());

        System.out.println("Arten: Direkt/ Kurz");
        System.out.print("Bitte wähle eine Art um ans Ziel zu kommen: ");

        final String art = sc.nextLine();
        if(art.toLowerCase().startsWith("k")) {
            final List<Vertex> kurz = gibKuerzesterWeg(start.getVertex(), ziel.getVertex());
            System.out.println("Kürzester Weg");
            System.out.println("Länge: "+ gibLaenge(kurz));
            System.out.println("Weg: "+toString(kurz));

        } else {
            final List<Vertex> direkter = gibDirekten(start.getVertex(), ziel.getVertex());
            System.out.println("Direkter Weg");
            System.out.println("Länge: "+gibLaenge(direkter));
            System.out.println("Weg: "+toString(direkter));

        }
        System.exit(0);
    }

    private Stadt gibStadt(String name) {
        return baum.search(new Stadt(name, 0, ""));
    }

    public String toString(List<?> list) {
        final StringBuilder sb = new StringBuilder();

        list.toFirst();
        while(list.hasAccess()) {
            sb.append(list.getContent().toString()).append("-");
            list.next();
        }

        return sb.toString();
    }

    public String toString2(List<List<Vertex>> list) {
        final StringBuilder sb = new StringBuilder();

        list.toFirst();
        while(list.hasAccess()) {
            list.getContent().toFirst();
            while(list.getContent().hasAccess()) {
                sb.append(list.getContent().getContent().toString()).append("-");
                list.getContent().next();
            }
            sb.append(gibLaenge(list.getContent())).append("\n");
            list.next();
        }

        return sb.toString();
    }

    public List<Vertex> gibDirekten(Vertex start, Vertex ziel) {
        final List<Vertex> weg = new List<>();
        final List<List<Vertex>> alleWege = new List<>();
        graph.setAllVertexMarks(false);
        gibAlleWege(start, ziel, weg, alleWege);

        List<Vertex> direktenWeg = null;

        alleWege.toFirst();
        while (alleWege.hasAccess()) {
            if(direktenWeg == null) {
                direktenWeg = alleWege.getContent();
            } else {
                if(gibSize(alleWege.getContent()) < gibSize(direktenWeg)) {
                    direktenWeg = alleWege.getContent();
                }
            }
            alleWege.next();
        }

        return direktenWeg;
    }

    public List<Vertex> gibKuerzesterWeg(Vertex start, Vertex ziel) {
        final List<Vertex> weg = new List<>();
        final List<List<Vertex>> alleWege = new List<>();
        graph.setAllVertexMarks(false);
        gibAlleWege(start, ziel, weg, alleWege);

        List<Vertex> kuerzesterWeg = null;

        alleWege.toFirst();
        while (alleWege.hasAccess()) {
            if(kuerzesterWeg == null) {
                kuerzesterWeg = alleWege.getContent();
            } else {
                if(gibLaenge(alleWege.getContent()) < gibLaenge(kuerzesterWeg)) {
                    kuerzesterWeg = alleWege.getContent();
                }
            }
            alleWege.next();
        }

        return kuerzesterWeg;
    }

    private int gibSize(List<Vertex> weg) {
        int laenge = 0;
        weg.toFirst();
        while (weg.hasAccess()) {
            laenge++;
            weg.next();
        }

        return laenge;
    }

    private double gibLaenge(List<Vertex> weg) {
        double laenge = 0;
        weg.toFirst();
        while (weg.hasAccess()) {
            final Vertex current = weg.getContent();
            weg.next();
            if(weg.hasAccess()) {
                laenge = laenge+graph.getEdge(current, weg.getContent()).getWeight();
            } else break;
            weg.next();
        }

        return laenge;
    }

    public void gibAlleWege(Vertex knoten, Vertex ziel, List<Vertex> weg, List<List<Vertex>> alleWege){
        knoten.setMark(true);
        weg.append(knoten);
        if (knoten != ziel) {
            final List<Vertex> nachbarn = graph.getNeighbours(knoten);
            nachbarn.toFirst();

            while (nachbarn.hasAccess()) {
                final Vertex nachbar = nachbarn.getContent();
                if (!nachbar.isMarked()){
                    gibAlleWege(nachbar, ziel, weg, alleWege);
                    //Schritt rückgängig machen
                    weg.toLast();

                    final Vertex rk = weg.getContent();
                    rk.setMark(false);
                    weg.remove();
                }
                nachbarn.next();
            }
        } else {
            final List<Vertex> einWeg = new List<>();

            copy(weg, einWeg);
            alleWege.append(einWeg);
        }
    }

    private void copy(List<Vertex> liste1, List<Vertex> liste2) {
        liste2.toFirst();
        while (!liste2.isEmpty()) {
            liste2.remove();
        }
        liste1.toFirst();
        while (liste1.hasAccess()) {
            liste2.append(liste1.getContent());
            liste1.next();
        }

    }


    public static void main(String[] args) {
        final Navigation navigation = new Navigation();
        navigation.ladeStaedte();
        navigation.auswahl();

    }

}
