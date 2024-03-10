public class SelectionSort {
	public static void selection(int array[]) {
		for(int i = 0; i < array.length-1 ; i++) {
			int min = i;
			for(int j= i + 1; j < array.length ; j++) {
				if(array [min] > array [j]) min = j; 
				// for descending order switch to < 
			}
			if(i!=min) {
				int temp = array[i];
				array[i] = array[min];
				array[min] = temp;
			}
		}
	}
	
	public static void main(String[] args) {
		int[] array = {8, 7, 9, 2, 3, 1, 5, 4, 6};
		selection(array);
		for(int i:array) {
			System.out.print(i);
		}
	}
	//Big Oh Notation of O(n^2)
	//bad for large data set
}
