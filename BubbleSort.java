public class BubbleSort {
	public static void bubble(int array[]) {
		for(int i = 0; i < array.length-1 ; i++) {
			for(int j= 0; j < array.length-i-1; j++) {
				if(array [j] > array [j+1]) {
					//for descending order switch to < 
					int temp = array[j];
					array[j] = array[j+1];
					array[j+1] = temp;
				}
			}
		}
	}
	
	public static void main(String[] args) {
		int[] array = {8, 7, 9, 2, 3, 1, 5, 4, 6};
		bubble(array);
		for(int i:array) {
			System.out.print(i);
		}
	}
	//Big Oh Notation of O(n^2)
	//bad for large data set
}
