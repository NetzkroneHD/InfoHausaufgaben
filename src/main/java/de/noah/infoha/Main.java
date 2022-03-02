package de.noah.infoha;

import de.noah.infoha.extraklassen.Vector3D;

public class Main {

    public static void main(String[] args) {
        for(Vector3D vector : Vector3D.getCube(new Vector3D(-256, 51, -219), new Vector3D(-255, 51, -213))) {
            System.out.println(vector.toString());
        }

    }
}
