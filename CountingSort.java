import java.util.Arrays;
public class CountingSort {
    public static void countingSort(int[] A, int[] B, int k) {
        int[] C = new int[k + 1];

        for (int i = 0; i <= k; i++) {
            C[i] = 0;
        }

        for (int j = 0; j < A.length; j++) {
            C[A[j]]++;
        }

        for (int i = 1; i <= k; i++) {
            C[i] += C[i - 1];
        }

        for (int j = A.length - 1; j >= 0; j--) {
            B[C[A[j]] - 1] = A[j];
            C[A[j]]--;
        }
    }

    public static void main(String[] args) {
        int[] A = {4, 2, 2, 8, 3, 3, 1, 7, 10, 8, 6, 6, 5, 9, 4, 7, 1, 5, 10, 9};
        int[] B = new int[A.length];
        int k = 10; 

        countingSort(A, B, k);

        System.out.println("Original array: " + Arrays.toString(A));
        System.out.println("Sorted array: " + Arrays.toString(B));
    }
}
