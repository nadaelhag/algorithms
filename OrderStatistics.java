import java.util.Random;

public class OrderStatistics {
    private static int randomizedPartition(int[] arr, int p, int r) {
        Random rand = new Random();
        int randomIndex = rand.nextInt(r - p + 1) + p;
        swap(arr, randomIndex, r); 
        int pivot = arr[r];
        int i = p - 1;
        for (int j = p; j < r; j++) {
            if (arr[j] <= pivot) {
                i++;
                swap(arr, i, j);
            }
        }
        swap(arr, i + 1, r);
        return i + 1;
    }
    
    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    
    public static int orderStatistics(int[] arr, int p, int r, int i) {
        if (p == r)
            return arr[p];
        
        int q = randomizedPartition(arr, p, r);
        int k = q - p + 1;
        
        if (i == k)
            return arr[q];
        else if (i < k)
            return orderStatistics(arr, p, q - 1, i);
        else
            return orderStatistics(arr, q + 1, r, i - k);
    }

    public static void main(String[] args) {
        int[] array = {9, 4, 7, 2, 5, 1, 8, 3, 6};
        int randomIndex = 3;
        int element = orderStatistics(array, 0, array.length - 1, randomIndex);
        
        System.out.println("Position of the randomly chosen element within the array: " + element);
    }
}
