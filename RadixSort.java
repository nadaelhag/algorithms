import java.util.*;

public class RadixSort {
    static void countingSort(int arr[], int d) {
        int n = arr.length;
        int output[] = new int[n]; //stores sorted elements
        int count[] = new int[11]; //store number of occurrences of each digit

        //count number of occurrences of each digit at position d
        for (int i = 0; i < n; i++)
            count[(arr[i] / d) % 10]++;
        
        //modify count to store actual position of each digit
        for (int i = 1; i <= 10; i++)
            count[i] += count[i - 1];

        //place elements in their positions based on d in output array
        for (int i = n - 1; i >= 0; i--) {
            output[count[(arr[i] / d) % 10] - 1] = arr[i];
            count[(arr[i] / d) % 10]--;
        }
        
        //copy elements to original arr
        for (int i = 0; i < n; i++)
            arr[i] = output[i];
    }
    
    //sort numbers from least to most significant digit
    static void radixSort(int arr[]) {
        int max = getMax(arr);
        for (int d = 1; max / d > 0; d *= 10)
            countingSort(arr, d); //count sort for each digit
    }
    
    static int getMax(int arr[]) {
        int max = arr[0];
        for (int i = 1; i < arr.length; i++)
            if (arr[i] > max)
                max = arr[i];
        return max;
    }
    
    static void printArray(int arr[]) {
        for (int i : arr) System.out.print(i + " ");
        System.out.println();
    }
    
    static int[] generateRandomArray(int size, int min, int max) {
        Random rand = new Random();
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = rand.nextInt((max - min) + 1) + min;
        }
        return array;
    }

    public static void main(String args[]) {
    	int arr[] = generateRandomArray(20, 10, 9999);
        System.out.println("Original array:");
        printArray(arr);

        radixSort(arr);

        System.out.println("Sorted array:");
        printArray(arr);
    }
}
