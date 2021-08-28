package de.noah.infoha.sortierung;

public class InsertionSort extends Sort {

    @Override
    public int[] sort(int[] intArray) {
        int temp;
        for (int i = 1; i < intArray.length; i++) {
            temp = intArray[i];
            int j = i;
            while (j > 0 && intArray[j - 1] > temp) {
                intArray[j] = intArray[j - 1];
                j--;
            }
            intArray[j] = temp;
        }
        return intArray;
    }
}
