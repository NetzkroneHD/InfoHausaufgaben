package de.noah.infoha.binarytrees.softwareprojekt;

import de.noah.infoha.abiturklassen.BaumZeichnerSearchTree;
import de.noah.infoha.abiturklassen.BinarySearchTree;
import de.noah.infoha.abiturklassen.Edge;
import de.noah.infoha.abiturklassen.Graph;

public class Navigation {

    private final Graph staedteGraph;
    private final BinarySearchTree<Stadt> staedteBaum;
    private BaumZeichnerSearchTree zeichner;

    public Navigation() {
        staedteGraph = new Graph();
        staedteBaum = new BinarySearchTree<>();

    }

    public void ladeStaedte() {
        Stadt witten = new Stadt("Witten", 100000, "DE");
        Stadt berlin = new Stadt("Berlin", 3000000, "DE");
        Stadt aachen = new Stadt("Aachen", 500000, "DE");
        Stadt hamburg = new Stadt("Hamburg", 1000000, "DE");
        Stadt paris = new Stadt("Paris", 4000000, "FR");
        Stadt peking = new Stadt("Peking", 100000000, "CH");
        Stadt madrid = new Stadt("Madrid", 30000000, "SP");
        Stadt mallorca = new Stadt("Mallorca", 520000, "SP");
        Stadt dortmund = new Stadt("Dortmund", 500000, "DE");
        Stadt bochum = new Stadt("Bochum", 300000, "DE");
        Stadt lissabon = new Stadt("Lissabon", 1000000, "PT");
        Stadt duisburg = new Stadt("Duisburg", 800000, "DE");

        staedteGraph.addVertex(witten.getVertex());
        staedteGraph.addVertex(berlin.getVertex());
        staedteGraph.addVertex(aachen.getVertex());
        staedteGraph.addVertex(hamburg.getVertex());
        staedteGraph.addVertex(paris.getVertex());
        staedteGraph.addVertex(peking.getVertex());
        staedteGraph.addVertex(madrid.getVertex());
        staedteGraph.addVertex(mallorca.getVertex());
        staedteGraph.addVertex(dortmund.getVertex());
        staedteGraph.addVertex(bochum.getVertex());
        staedteGraph.addVertex(lissabon.getVertex());
        staedteGraph.addVertex(duisburg.getVertex());

        staedteGraph.addEdge(new Edge(lissabon.getVertex(), madrid.getVertex(), 1460));
        staedteGraph.addEdge(new Edge(lissabon.getVertex(), mallorca.getVertex(), 1200));
        staedteGraph.addEdge(new Edge(mallorca.getVertex(), madrid.getVertex(), 670));
        staedteGraph.addEdge(new Edge(paris.getVertex(), madrid.getVertex(), 400));
        staedteGraph.addEdge(new Edge(paris.getVertex(), aachen.getVertex(), 120));
        staedteGraph.addEdge(new Edge(aachen.getVertex(), dortmund.getVertex(), 20));
        staedteGraph.addEdge(new Edge(paris.getVertex(), hamburg.getVertex(), 480));
        staedteGraph.addEdge(new Edge(hamburg.getVertex(), dortmund.getVertex(), 300));
        staedteGraph.addEdge(new Edge(dortmund.getVertex(), witten.getVertex(), 10));
        staedteGraph.addEdge(new Edge(dortmund.getVertex(), bochum.getVertex(), 20));
        staedteGraph.addEdge(new Edge(witten.getVertex(), bochum.getVertex(), 5));
        staedteGraph.addEdge(new Edge(witten.getVertex(), duisburg.getVertex(), 40));
        staedteGraph.addEdge(new Edge(duisburg.getVertex(), bochum.getVertex(), 32));
        staedteGraph.addEdge(new Edge(bochum.getVertex(), berlin.getVertex(), 260));
        staedteGraph.addEdge(new Edge(hamburg.getVertex(), berlin.getVertex(), 320));
        staedteGraph.addEdge(new Edge(berlin.getVertex(), peking.getVertex(), 3600));



        staedteBaum.insert(witten);
        staedteBaum.insert(berlin);
        staedteBaum.insert(aachen);
        staedteBaum.insert(hamburg);
        staedteBaum.insert(paris);
        staedteBaum.insert(peking);
        staedteBaum.insert(madrid);
        staedteBaum.insert(mallorca);
        staedteBaum.insert(dortmund);
        staedteBaum.insert(bochum);
        staedteBaum.insert(lissabon);
        staedteBaum.insert(duisburg);

        zeichner = new BaumZeichnerSearchTree(600, 400, staedteBaum);
        zeichner.setVisible(true);

    }

    public Stadt getStadt(String name) {
        final Stadt stadt = new Stadt(name, 0, "");
        return staedteBaum.search(stadt);
    }

}
