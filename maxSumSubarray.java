import java.util.Random;

public class maxSumSubarray {
		public static int bruteforce(int[] arr) {
		    int maxSum = Integer.MIN_VALUE;
		    int start = 0;
		    int end = 0;

		    for (int i = 0; i < arr.length; i++) {
		        int sum = 0;
		        for (int j = i; j < arr.length; j++) {
		            sum += arr[j];
		            if (sum > maxSum) {
		                maxSum = sum;
		                start = i;
		                end = j;
		            }
		        }
		    }

		    return maxSum;
		}


    public static int[] findMaxCrossingSubarray(int[] arr, int low, int mid, int high) {
        int leftSum = Integer.MIN_VALUE;
        int sum = 0;
        int maxLeft = 0;
        for (int i = mid; i >= low; i--) {
            sum += arr[i];
            if (sum > leftSum) {
                leftSum = sum;
                maxLeft = i;
            }
        }

        int rightSum = Integer.MIN_VALUE;
        sum = 0;
        int maxRight = 0;
        for (int j = mid + 1; j <= high; j++) {
            sum += arr[j];
            if (sum > rightSum) {
                rightSum = sum;
                maxRight = j;
            }
        }

        return new int[]{maxLeft, maxRight, leftSum + rightSum};
    }

    public static int[] findMaximumSubarray(int[] arr, int low, int high) {
    	//int n0 = 35;
    	if (low == high) {
            return new int[]{low, high, arr[low]};
        } else {
            int mid = (low + high) / 2;

            int[] leftInfo = findMaximumSubarray(arr, low, mid);
            int[] rightInfo = findMaximumSubarray(arr, mid + 1, high);
            int[] crossInfo = findMaxCrossingSubarray(arr, low, mid, high);

            if (leftInfo[2] >= rightInfo[2] && leftInfo[2] >= crossInfo[2]) {
                return leftInfo;
            } else if (rightInfo[2] >= leftInfo[2] && rightInfo[2] >= crossInfo[2]) {
                return rightInfo;
            } else {
                return crossInfo;
            }
        }
    }
    public static void main(String[] args) {
    	int NUM_ITERATIONS = 400000;
        Random rand = new Random();

        for (int inputSize = 1; inputSize < 100; inputSize++) {
            int[] randomTestArray = new int[inputSize];
            for (int i = 0; i < inputSize; i++) {
                randomTestArray[i] = rand.nextInt(201) - 100; 
            }
            
            long bruteForceTime = 0;
            long bfStartTime = System.nanoTime();
            for (int i = 0; i < NUM_ITERATIONS; i++) {
                bruteforce(randomTestArray);
            }
            long bfEndTime = System.nanoTime();
            bruteForceTime = (bfEndTime - bfStartTime) / NUM_ITERATIONS;
            System.out.println("brute force time taken for "+inputSize+" is "+ bruteForceTime+" ns");

            long recursiveTime = 0;
            long recStartTime = System.nanoTime();
            for (int i = 0; i < NUM_ITERATIONS; i++) {
                findMaximumSubarray(randomTestArray, 0, randomTestArray.length - 1);
            }
            long recEndTime = System.nanoTime();
            recursiveTime = (recEndTime - recStartTime) / NUM_ITERATIONS;
            System.out.println("recursive time taken for "+inputSize+" is "+ recursiveTime+" ns");
            System.out.println();
            
            if (bruteForceTime > recursiveTime) {
                System.out.println("Cross over point occurs at input size n0 = " + inputSize);
                break;
            }
        }
    }
}
