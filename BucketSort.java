import java.util.*;
public class BucketSort {

    public static void bucketSort(float[] array) {
        int n = array.length;
        
		@SuppressWarnings("unchecked")
		ArrayList<Float>[] buckets = new ArrayList[n]; 
        for (int i = 0; i < n; i++) {
            buckets[i] = new ArrayList<Float>(); //create each bucket
        }
        
        //move elements into buckets based on their value
        for (int i = 0; i < n; i++) {
        	//index of bucket for current element
            int bucketIndex = Math.min((int) (n * array[i]), n - 1);
            //add element to bucket
            buckets[bucketIndex].add(array[i]);
        }
        
        //sort each bucket using insertion sort
        for (int i = 0; i < n; i++) {
            InsertionSort(buckets[i]);
        }
        
        //combine buckets to form sorted array
        int index = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < buckets[i].size(); j++) {
                array[index++] = buckets[i].get(j);
            }
        }
    }
    
    public static void InsertionSort(ArrayList<Float> arr) {
        for (int i = 1; i < arr.size(); i++) {
            Float key = arr.get(i); //current element to be sorted
            int j = i - 1; //index of previous element
            //move elements that are > key to the right 
            while (j >= 0 && arr.get(j) > key) {
                arr.set(j + 1, arr.get(j)); //move element 1 position forward
                j--;
            }
            //insert key to its right position
            arr.set(j + 1, key);
        }
    }


    private static float[] generateRandomArray(int size) {
        Random rand = new Random();
        float[] array = new float[size];
        for (int i = 0; i < size; i++) {
            array[i] = rand.nextFloat() * 2;
        }
        return array;
    }
    
    public static void printArray(float[] array) {
        for (float num : array) {
            System.out.print(num + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
    	int arraySize = 10;
    	float[] array = generateRandomArray(arraySize);
        
        System.out.println("Array before sorting:");
        printArray(array);
        
        bucketSort(array);
        
        System.out.println("Array after sorting:");
        printArray(array);
        
        System.out.println();
        float[] best = {0.1f, 0.2f, 0.3f, 0.4f, 0.5f, 0.6f, 0.7f, 0.8f, 0.9f, 1.0f};
        System.out.println("Array before sorting:");
        printArray(best);
        
        bucketSort(best);
        
        System.out.println("Array after sorting (Best Case):");
        printArray(best);
        
        System.out.println();
        float[] worst = {1.9f, 1.8f, 1.7f, 1.6f, 1.5f, 1.4f, 1.3f, 1.2f, 1.1f};
        System.out.println("Array before sorting:");
        printArray(worst);
        
        bucketSort(worst);
        
        System.out.println("Array after sorting (Worst Case):");
        printArray(worst);
        
    }

}
