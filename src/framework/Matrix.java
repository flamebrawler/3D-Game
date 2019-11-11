package framework;

public class Matrix {
	public int[][] value;
	public int rows;
	public int collumns;

	public Matrix(int[][] value) {
		this.value = value;
		rows = value.length;
		collumns = value[0].length;
	}

	public Matrix multiply(Matrix other) {
		if (collumns == other.rows) {
			Matrix m = new Matrix(new int[rows][other.collumns]);
			for (int c = 0; c < rows; c++) {
				for (int b = 0; b < other.collumns; b++) {
					int sum = 0;
					for (int a = 0; a < collumns; a++) {
						sum += value[c][a] * other.value[a][b];
					}
					m.value[c][b] = sum;
				}
			}
			return m;
		}
		System.out.println("matrices are incompatible");
		return this;
	}

}
