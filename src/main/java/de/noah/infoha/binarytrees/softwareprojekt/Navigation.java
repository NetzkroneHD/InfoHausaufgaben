package de.noah.infoha.binarytrees.softwareprojekt;

import de.noah.infoha.abiturklassen.*;

public class Navigation {

    private final Graph graph;
    private final BinarySearchTree<Stadt> baum;
    private BaumZeichnerSearchTree zeichner;

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

        zeichner = new BaumZeichnerSearchTree(600, 400, baum);
        zeichner.setVisible(true);

        System.out.println(sucheWegToString(hamburg.getName(), peking.getName()));

    }

    public Stadt getStadt(String name) {
        final Stadt stadt = new Stadt(name, 0, "");
        return baum.search(stadt);
    }

    public String sucheWegToString(String start, String ziel) {
        List<Vertex> weg = new List<>();
        graph.setAllVertexMarks(false);
        Vertex startKnoten = graph.getVertex(start);
        Vertex zielKnoten = graph.getVertex(ziel);
        String wegString;
        if (sucheWeg(startKnoten, zielKnoten, weg)) {
            wegString = knotenListeToString(weg);
        } else {
            wegString = "";
        }
        return wegString;
    }

    public boolean sucheWeg(Vertex knoten, Vertex ziel, List<Vertex> weg) {
        boolean wegGefunden = false;
        knoten.setMark(true);
        weg.append(knoten);
        if (knoten != ziel) {
            List<Vertex> nachbarn = graph.getNeighbours(knoten);
            nachbarn.toFirst();
            while (!wegGefunden && nachbarn.hasAccess()) {
                Vertex nachbar = nachbarn.getContent();
                if (!nachbar.isMarked()) {
                    wegGefunden = sucheWeg(nachbar, ziel, weg);
                    if (!wegGefunden) {
                        weg.toLast();
                        Vertex loeschKnoten = weg.getContent();
                        loeschKnoten.setMark(false);
                        weg.remove();
                    }
                }
                nachbarn.next();
            }
        } else {
            wegGefunden = true;
        }
        return wegGefunden;
    }

    public String knotenListeToString(List<Vertex> liste) {
        liste.toFirst();
        StringBuilder listenString = new StringBuilder();
        while (liste.hasAccess()) {
            String id = liste.getContent().getID();
            listenString.append(id).append(" ");
            liste.next();
        }
        return listenString.toString();
    }


}
