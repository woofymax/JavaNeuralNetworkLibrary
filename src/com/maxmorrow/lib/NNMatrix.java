package com.maxmorrow.lib;

import java.util.ArrayList;
import java.util.List;

public class NNMatrix extends Matrix {


	public NNMatrix(int rows, int cols) {
		super(rows, cols, null);

		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				super.data[i][j] = Math.random() * 2 - 1;
			}
		}
	}



	public void sigmoid() {
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++)
				this.data[i][j] = 1 / (1 + Math.exp(-this.data[i][j]));
		}

	}

	public NNMatrix derivative() {
		NNMatrix temp = new NNMatrix(rows, cols);
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++)
				temp.data[i][j] = this.data[i][j] * (1 - this.data[i][j]);
		}
		return temp;

	}

	public void relu() {
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				data[i][j] = Math.max(this.data[i][j], 0);
			}
		}
	}
}