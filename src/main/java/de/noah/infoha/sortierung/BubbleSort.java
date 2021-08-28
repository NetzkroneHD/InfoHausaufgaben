package de.noah.infoha.sortierung;

public class BubbleSort extends Sort {

	@Override
	public int[] sort(int[] intArray) {
		int temp;
		for (int i = 1; i < intArray.length; i++) {
			for (int j = 0; j < intArray.length - i; j++) {
				if (intArray[j] > intArray[j+1]) {
					temp = intArray[j];
					intArray[j] = intArray[j + 1];
					intArray[j + 1] = temp;
				}
			}
		}
		return intArray;
	}
}
