package com.maxmorrow.lib;

import java.util.ArrayList;
import java.util.List;

public class Matrix {
	double[][] data;
	public int rows, cols;
	public Matrix (int rows, int cols, double[][] data){
		this.data = data;
		this.rows = rows;
		this.cols = cols;
	}
	public void print() {
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				System.out.print(this.data[i][j] + " ");
			}
			System.out.println();
		}
	}

	public static NNMatrix add(NNMatrix a, NNMatrix b) {

		if (a.cols != b.cols || a.rows != b.rows){
			System.out.println("Shape Mismatch");
			return null;
		}
		NNMatrix temp = new NNMatrix(a.cols, a.rows);
		for (int i = 0; i < a.rows; i++) {
			for (int j = 0; j < a.cols; j++) {
				temp.data[i][j] = a.data[i][j] + b.data[i][j];
			}
		}
		return temp;
	}

	public void add(int scaler) {
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				this.data[i][j] += scaler;
			}

		}
	}

	public void add(NNMatrix m) {
		if (cols != m.cols || rows != m.rows) {
			System.out.println("Shape Mismatch");
			return;
		}

		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				this.data[i][j] += m.data[i][j];
			}
		}
	}

	public static NNMatrix fromArray(double[] x) {
		NNMatrix temp = new NNMatrix(x.length, 1);
		for (int i = 0; i < x.length; i++)
			temp.data[i][0] = x[i];
		return temp;

	}

	public List<Double> toArray() {
		List<Double> temp = new ArrayList<Double>();

		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				temp.add(data[i][j]);
			}
		}
		return temp;
	}

	public List<List<Double>> toFormattedArray() {
		List<List<Double>> temp = new ArrayList<>();

		for (int i = 0; i < rows; i++) {
			temp.add(new ArrayList<>());
			for (int j = 0; j < cols; j++) {
				temp.get(i).add(data[i][j]);
			}
		}
		return temp;
	}

	public static NNMatrix subtract(NNMatrix a, NNMatrix b) {
		NNMatrix temp = new NNMatrix(a.rows, a.cols);
		for (int i = 0; i < a.rows; i++) {
			for (int j = 0; j < a.cols; j++) {
				temp.data[i][j] = a.data[i][j] - b.data[i][j];
			}
		}
		return temp;

	}

	public static NNMatrix transpose(NNMatrix a) {
		NNMatrix temp = new NNMatrix(a.cols, a.rows);
		for (int i = 0; i < a.rows; i++) {
			for (int j = 0; j < a.cols; j++) {
				temp.data[j][i] = a.data[i][j];
			}
		}
		return temp;
	}

	public void transpose() {
		for (int i = 0; i < this.rows; i++) {
			for (int j = 0; j < this.cols; j++) {
				this.data[j][i] = this.data[i][j];
			}
		}
	}
	public static NNMatrix multiply(NNMatrix a, NNMatrix b) {
		NNMatrix temp = new NNMatrix(a.rows, b.cols);
		for (int i = 0; i < temp.rows; i++) {
			for (int j = 0; j < temp.cols; j++) {
				double sum = 0;
				for (int k = 0; k < a.cols; k++) {
					sum += a.data[i][k] * b.data[k][j];

				}

				temp.data[i][j] = sum;
			}
		}
		return temp;
	}

	public void multiply(NNMatrix a) {
		for (int i = 0; i < a.rows; i++) {
			for (int j = 0; j < a.cols; j++) {
				this.data[i][j] *= a.data[i][j];
			}
		}

	}

	public void multiply(double a) {
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				this.data[i][j] *= a;
			}
		}

	}
}
