package de.noah.infoha.sortierung;

public class QuickSort extends Sort {

    private int start, end;

    public QuickSort(int start, int end) {
        this.start = start;
        this.end = end;
    }


    @Override
    public int[] sort(int[] intArray) {

        quickSort(intArray, start, end);

        return intArray;
    }


    private void quickSort(int[] pZahlen, int start, int end) {

        final int par = par(pZahlen, start, end);
        if (par - 1 > start) {
            quickSort(pZahlen, start, par - 1);
        }
        if (par + 1 < end) {
            quickSort(pZahlen, par + 1, end);
        }
    }

    private int par(int[] pZahlen, int start, int end) {
        int letzteZahl = pZahlen[end];
        for (int i = start; i < end; i++) {
            if (pZahlen[i] < letzteZahl) {
                int temp = pZahlen[start];
                pZahlen[start] = pZahlen[i];
                pZahlen[i] = temp;
                start++;
            }
        }
        int temp = pZahlen[start];
        pZahlen[start] = letzteZahl;
        pZahlen[end] = temp;

        return start;
    }
    public void setEnd(int end) {
        this.end = end;
    }
    public void setStart(int start) {
        this.start = start;
    }
    public int getEnd() {
        return end;
    }
    public int getStart() {
        return start;
    }

}
