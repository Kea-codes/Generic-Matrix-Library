
//@author K MAREMELA 221030105

public class Main {
	public static void main(String[] args) {
		try {
			// Example matrices
			Integer[][] dataA = { { 1, 2 }, { 3, 4 } };
			Integer[][] dataB = { { 5, 6 }, { 7, 8 } };
			Matrix<Integer> matrixA = new Matrix<>(dataA);
			Matrix<Integer> matrixB = new Matrix<>(dataB);

			System.out.println("Matrix A:");
			System.out.println(matrixA);

			System.out.println("Matrix B:");
			System.out.println(matrixB);

			System.out.println("Matrix A + Matrix B:");
			System.out.println(matrixA.addMatrix(matrixB));

			System.out.println("Matrix A * 2:");
			System.out.println(matrixA.multiplyScalar(2));

			System.out.println("Matrix A * Matrix B:");
			System.out.println(matrixA.multiplyMatrix(matrixB));

			System.out.println("Transpose of Matrix A:");
			System.out.println(matrixA.transpose());
		} catch (MatrixException e) {
			e.printStackTrace();
		}
	}
}