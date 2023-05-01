import java.util.Scanner;
class matrixchain {
	static int matrixchainmult(int ar[], int n)
	{
		int m[][] = new int[n][n];
        int i, j, k, L, q;
		for (i = 1; i < n; i++){
			m[i][i] = 0;
        }
		for (L = 2; L < n; L++) {
			for (i = 1; i < n - L + 1; i++) {
				j = i + L - 1;
				if (j == n){
					continue;
                }
				m[i][j] = Integer.MAX_VALUE;
				for (k = i; k <= j - 1; k++) {
					q = m[i][k] + m[k + 1][j] + ar[i - 1] * ar[k] * ar[j];
					if (q < m[i][j])
						m[i][j] = q;
				}
			}
		}
		return m[1][n - 1];
	}
    public static void main(String args[])
	{
        Scanner s = new Scanner(System.in);
        System.out.println("Enter the no. of matrices: ");
        int sij = s.nextInt();
		int arr[] = new int[sij];
        System.out.println("Enter the dimension of the matrices: ");
		for (int i = 0; i < sij; i++) {
            arr[i] = s.nextInt();
        }
		System.out.println("Minimum number of multiplications is " + matrixchainmult(arr, sij));
        s.close();
	}
}
