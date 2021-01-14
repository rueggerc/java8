package com.rueggerllc.qsort;

public class QuickSort {

	public QuickSort() {
	}
	
	public void sort(int[] data) {
		quickSort(data,0,data.length-1);
	}
	
	private void quickSort(int[] array, int low, int high) {
		dump(array,low,high);
		if (low < high) {
			int partitionIndex = partitionHigh(array,low,high);
			quickSort(array,low,partitionIndex-1);
			quickSort(array,partitionIndex+1,high);
		}
	}
		
	private int partitionHigh(int[] array, int low, int high) {
		int pivot = array[high];
		int i = low - 1;
		
		for (int j=low; j < high; j++) {
			if (array[j] <= pivot) {
				i++;
				int swapTemp = array[i];
				array[i] = array[j];
				array[j] = swapTemp;
			}
		}
		
		int swapTemp = array[i+1];
		array[i+1] = pivot;
		array[high] = swapTemp;
		
		return i+1;
	}
	
	private int partitionMid(int[] array, int low, int high) {
		int pivot = array[high];
		int i = low - 1;
		
		for (int j=low; j < high; j++) {
			if (array[j] <= pivot) {
				i++;
				int swapTemp = array[i];
				array[i] = array[j];
				array[j] = swapTemp;
			}
		}
		
		int swapTemp = array[i+1];
		array[i+1] = pivot;
		array[high] = swapTemp;
		
		return i+1;
	}
	
	private void dump(int[] array, int low, int high) {
		StringBuilder buffer = new StringBuilder();
		for (int i = low; i <= high; i++) {
			buffer.append(array[i] + " ");
		}
		System.out.println(buffer.toString());
	}
		
	
}
