public class strassensalgorithm {    

	public static int[][] strassen(int[][] A, int[][] B) {
		int n = A.length;

        // Base case: If the matrices are of size 1x1
        if (n == 1) {
            int[][] C = new int[1][1];
            C[0][0] = A[0][0] * B[0][0];
            return C;
        } 
        else {
        	// Pad matrices A and B to the nearest power of 2
            int m = nearestPowerOfTwo(n);
            int[][] newA = padMatrix(A, m);
            int[][] newB = padMatrix(B, m);
            
            // Divide matrices A and B into submatrices Initialization
            int[][] A11 = new int[m / 2][m / 2];
            int[][] A12 = new int[m / 2][m / 2];
            int[][] A21 = new int[m / 2][m / 2];
            int[][] A22 = new int[m / 2][m / 2];

            int[][] B11 = new int[m / 2][m / 2];
            int[][] B12 = new int[m / 2][m / 2];
            int[][] B21 = new int[m / 2][m / 2];
            int[][] B22 = new int[m / 2][m / 2];

            // Splitting matrix newA into four submatrices
            split(newA, A11, 0, 0);            
            split(newA, A12, 0, m / 2);        
            split(newA, A21, m / 2, 0);        
            split(newA, A22, m / 2, m / 2);    

            // Splitting matrix newB into four submatrices B11, B12, B21, and B22
            split(newB, B11, 0, 0);            
            split(newB, B12, 0, m / 2);       
            split(newB, B21, m / 2, 0);        
            split(newB, B22, m / 2, m / 2);    
           
            // Compute the 10 intermediate matrices 
            int[][] S1 = subtract(B12, B22);
            int[][] S2 = add(A11, A12);
            int[][] S3 = add(A21, A22);
            int[][] S4 = subtract(B21, B11);
            int[][] S5 = add(A11, A22);
            int[][] S6 = add(B11, B22);
            int[][] S7 = subtract(A12, A22);
            int[][] S8 = add(B21, B22);
            int[][] S9 = subtract(A11, A21);
            int[][] S10 = add(B11, B12);

            // Compute the seven recursive matrix products
            int[][] P1 = strassen(A11, S1);
            int[][] P2 = strassen(S2, B22);
            int[][] P3 = strassen(S3, B11);
            int[][] P4 = strassen(A22, S4);
            int[][] P5 = strassen(S5, S6);
            int[][] P6 = strassen(S7, S8);
            int[][] P7 = strassen(S9, S10);

            // Compute the submatrices C11, C12, C21, C22
            int[][] C11 = add(subtract(add(P5, P4), P2), P6);
            int[][] C12 = add(P1, P2);
            int[][] C21 = add(P3, P4);
            int[][] C22 = subtract(subtract(add(P5, P1), P3), P7);

            // Merge submatrices into the result matrix C
            int[][] C = new int[m][m];
            merge(C11, C, 0, 0);
            merge(C12, C, 0, m / 2);
            merge(C21, C, m / 2, 0);
            merge(C22, C, m / 2, m / 2);

            // Trim the result matrix to the original size
            int[][] trimmedC = new int[n][n];
            for (int i = 0; i < n; i++) {
                System.arraycopy(C[i], 0, trimmedC[i], 0, n);
            }

            return trimmedC;
        }
    }
	
	private static int nearestPowerOfTwo(int n) {
        int m = 1;
        while (m < n) {
            m *= 2;
        }
        return m;
    }

    private static int[][] padMatrix(int[][] matrix, int size) {
        int[][] paddedMatrix = new int[size][size];
        for (int i = 0; i < matrix.length; i++) {
            System.arraycopy(matrix[i], 0, paddedMatrix[i], 0, matrix[i].length);
        }
        return paddedMatrix;
    }
	
    public static int[][] add(int[][] A, int[][] B) {
        int n = A.length;
        int[][] C = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                C[i][j] = A[i][j] + B[i][j];
            }
        }
        return C;
    }
    
    public static int[][] subtract(int[][] A, int[][] B) {
        int n = A.length;
        int[][] C = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                C[i][j] = A[i][j] - B[i][j];
            }
        }
        return C;
    }
    
    // Function to split a matrix into submatrices
    public static void split(int[][] P, int[][] C, int startrowindex, int startcolumnindex) {
        for (int i = 0, i2 = startrowindex; i < C.length; i++, i2++) {
            for (int j = 0, j2 = startcolumnindex; j < C.length; j++, j2++) {
                C[i][j] = P[i2][j2];
            }
        }
    }
    
    // Function to merge a submatrix into a matrix
    public static void merge(int[][] C, int[][] P, int iB, int jB) {
        for (int i1 = 0, i2 = iB; i1 < C.length; i1++, i2++) {
            for (int j1 = 0, j2 = jB; j1 < C.length; j1++, j2++) {
                P[i2][j2] = C[i1][j1];
            }
        }
    }
    
    // Function to print a matrix
    public static void printMatrix(int[][] matrix) {
        int rows = matrix.length;
        int column = matrix[0].length;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < column; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }
    
    public static void main(String[] args) {
        int[][] A = {{1, 2, 3}, 
        		{4, 5, 6}, 
        		{7, 8, 9}};
        int[][] B = {{9, 8, 7}, 
        		{6, 5, 4}, 
        		{3, 2, 1}};
        
        int[][] C = strassen(A, B);
        
        System.out.println("Matrix A:");
        printMatrix(A);
        System.out.println();
        System.out.println("Matrix B:");
        printMatrix(B);
        System.out.println();
        System.out.println("Matrix C from AxB:");
        printMatrix(C);
    }
}

