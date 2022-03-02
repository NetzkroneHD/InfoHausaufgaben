package de.noah.infoha;

import de.noah.infoha.extraklassen.Vector3D;

public class Main {

    public static void main(String[] args) {
        for(Vector3D vector : Vector3D.getCube(new Vector3D(-256, 51, -219), new Vector3D(-255, 51, -213))) {
            System.out.println(vector.toString());
        }
    }














    /**
    public static void main(String[] args) {
        System.out.println("Calculating...");
        final RandomPossibility rp = new RandomPossibility(3D/4D);
        final List<Boolean> results = new LinkedList<>();

        for(int i = 0; i < 10000000; i++) {
            results.add(rp.apply());
        }

        long trueResults = 0;
        long falseResults = 0;

        for(boolean result : results) {
            if(result) trueResults++;
            else falseResults++;
        }


        System.out.println("trueResults: "+trueResults);
        System.out.println("falseResults: "+falseResults);
        System.out.println("average: "+(((double)trueResults/(double)results.size()))*100+"%");
    }
     **/
}
