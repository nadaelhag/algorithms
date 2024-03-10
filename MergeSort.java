public class MergeSort {
    public static void mergeSort(int[] array) {
        int x = array.length;
        if (x > 1) {
            int mid = x / 2;
            int[] leftArray = new int[mid];
            int[] rightArray = new int[x - mid];

            for (int i = 0; i < mid; i++) {
                leftArray[i] = array[i];
            }
            for (int i = mid; i < x; i++) {
                rightArray[i - mid] = array[i];
            }

            mergeSort(leftArray);
            mergeSort(rightArray);

            merge(array, leftArray, rightArray);
        }
    }

    private static void merge(int[] array, int[] left, int[] right) {
        int i = 0;
        int j = 0;
        int k = 0;
        while (i < left.length && j < right.length) {
            if (left[i] <= right[j]) {
                array[k++] = left[i++];
            } else {
                array[k++] = right[j++];
            }
        }
        while (i < left.length) {
            array[k++] = left[i++];
        }
        while (j < right.length) {
            array[k++] = right[j++];
        }
    }

    public static void main(String[] args) {
        int[] array = {23, 2, 6, 5, 4, 30, 32};
        System.out.println("Original array:");
        display(array);

        mergeSort(array);
        System.out.println("Sorted array:");
        display(array);
    }

    private static void display(int[] array) {
        for (int i : array) {
            System.out.print(i + " ");
        }
        System.out.println();
    }
}
