package de.noah.infoha.sortierung;

public class SelectionSort extends Sort {

	@Override
	public int[] sort(int[] intArray) {
		for (int i = 0; i < intArray.length - 1; i++) {
			int index = i;
			for (int j = i + 1; j < intArray.length; j++) {
				if (intArray[j] < intArray[index]) {
					index = j;
				}
			}
			int kleine = intArray[index];
			intArray[index] = intArray[i];
			intArray[i] = kleine;
		}
		return intArray;
	}
}
