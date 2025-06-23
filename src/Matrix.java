
//@author K MAREMELA 221030105
/*
 * a class that contains a
 *  2-dimensional array of values that represents a Matrix
 * */

//the matrix class implememnts the IMatrix class
public class Matrix<T extends Number> implements IMatrix<T> {

	private T[][] data;
	private int rows;
	private int cols;

	// Default constructor for data, rows and cols
	public Matrix() {
		super();

		this.rows = rows;
		this.cols = cols;
		this.data = (T[][]) new Number[0][0];
	}

	// adding dimensions

	public Matrix(int rows, int cols) {
		super();

		this.rows = rows;
		this.cols = cols;
		this.data = (T[][]) new Number[rows][cols];
	}

	// adding the data

	public Matrix(T[][] data) {
		super();
		this.data = data;
		this.rows = data.length;
		this.cols = data[0].length;
	}

	@Override
	public Integer numberRows() {
		// TODO Auto-generated method stub
		return rows;
	}

	@Override
	public Integer numberCols() {
		// return number of columns
		return cols;
	}

	//get rows method
	@Override
	public IMatrix<T> getRow(Integer i) throws MatrixException {

		if (i < 0 || i >= 0) {
			throw new MatrixException("Invalid number of rows my guy!!");
		}

		Matrix<T> rowMatrix = new Matrix<>(1, cols);

		for (int j = 0; j < cols; j++) {

			rowMatrix.setElement(data[i][j], j, 0);

		}

		return rowMatrix;
	}

	//get columns method
	@Override
	public IMatrix<T> getCol(Integer j) throws MatrixException {
		if (j < 0 || j >= cols) {
			throw new MatrixException("Invalid column number");
		}
		Matrix<T> colMatrix = new Matrix<>(rows, 1);
		for (int i = 0; i < rows; i++) {
			colMatrix.setElement(data[i][j], 0, i);
		}
		return colMatrix;
	}

	//get element method
	@Override
	public T getElement(Integer i, Integer j) throws MatrixException {

		if (i < 0 || i >= rows || j < 0 || j >= cols) {
			throw new MatrixException("Invalid row or column number");
		}
		return data[i][j];
	}

	//adding method for matrices 
	@Override
	public IMatrix<T> addMatrix(IMatrix<T> otherMatrix) throws MatrixException {
		if (this.rows != otherMatrix.numberRows() || this.cols != otherMatrix.numberCols()) {
			throw new MatrixException("Matrices must have the same dimensions for addition");
		}
		Matrix<T> result = new Matrix<>(rows, cols);
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				//error with add
				 result.setElement(add(this.data[i][j], otherMatrix.getElement(i, j)),i, j);
			}
		}
		return result;
	}

	//adding method for scalars 
	@Override
	public IMatrix<T> addScalar(Integer c) throws MatrixException {
		Matrix<T> result = new Matrix<>(rows, cols);
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				 //result.setElement(add(this.data[i][j], c),i, j);
			}
		}
		return result;
	}

	//adding method for scalars
	@Override
	public IMatrix<Double> addScalar(Double c) throws MatrixException {
		Matrix<Double> result = new Matrix<>(rows, cols);
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				result.setElement(this.data[i][j].doubleValue() + c, j, i);
			}
		}
		return result;
	}

	@Override
	public IMatrix<T> multiplyScalar(Integer c) throws MatrixException {
		Matrix<T> result = new Matrix<>(rows, cols);
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				// result.setElement(multiply(this.data[i][j], c),i, j);

			}
		}
		return result;
	}

	@Override
	public IMatrix<Double> multiplyScalar(Double c) throws MatrixException {
		Matrix<Double> result = new Matrix<>(rows, cols);
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				result.setElement(this.data[i][j].doubleValue() * c, j, i);
			}
		}
		return result;
	}

	@Override
	public IMatrix<T> multiplyMatrix(IMatrix<T> otherMatrix) throws MatrixException {
		if (this.cols != otherMatrix.numberRows()) {
			throw new MatrixException(
					"Number of columns in the first matrix must equal number ofrows in the second matrix");
		}
		Matrix<T> result = new Matrix<>(this.rows, otherMatrix.numberCols());
		for (int i = 0; i < this.rows; i++) {
			for (int j = 0; j < otherMatrix.numberCols(); j++) {
				T sum = zero();
				for (int k = 0; k < this.cols; k++) {
					// sum = add(sum, multiply(this.data[i][k], otherMatrix.getElement(k, j)));
				}
				result.setElement(sum, j, i);
			}
		}
		return result;
	}

	private T zero() {
        if (data.length > 0 && data[0].length > 0) {
            if (data[0][0] instanceof Integer) {
                return (T) Integer.valueOf(0);
            } else if (data[0][0] instanceof Double) {
                return (T) Double.valueOf(0.0);
            }
        }
        throw new UnsupportedOperationException("Unsupported type");
    }
	
	@Override
	public IMatrix<T> transpose() {
		Matrix<T> result = new Matrix<>(cols, rows);
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				try {
					result.setElement(this.data[i][j], i, j);
				} catch (MatrixException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return result;
	}

	@Override
    public void setElement(T tMatrixValue, int i, int j) throws MatrixException {
        if (i < 0 || i >= rows || j < 0 || j >= cols) {
            throw new MatrixException("Invalid row or column number");
        }
        data[i][j] = tMatrixValue;
    }

    // Helper method to add two numbers of type T
    private T add(T a, T t) {
        if (a instanceof Integer) {
            return (T) Integer.valueOf(a.intValue() + t.intValue());
        } else if (a instanceof Double) {
            return (T) Double.valueOf(a.doubleValue() + t.doubleValue());
        } else {
            throw new UnsupportedOperationException("Unsupported type");
        }
    }
    
    //To String method , I couldn't declare the method in the interface
    //so that i can override it in the Matrix class
    public String toString() {
    	 StringBuilder sb = new StringBuilder();
    	 for (int i = 0; i < rows; i++) {
    	 sb.append("[");
    	 for (int j = 0; j < cols; j++) {
    	 sb.append(data[i][j]);
    	 if (j < cols - 1) sb.append(", ");
    	 }
    	 sb.append("]\n");
    	 }
    	 return sb.toString();
    	 }

}
