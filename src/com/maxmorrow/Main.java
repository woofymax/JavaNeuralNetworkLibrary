
package com.maxmorrow;

import com.maxmorrow.lib.Dataset;
import com.maxmorrow.lib.NeuralNetwork;

import java.lang.reflect.Array;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.List;

public class Main {

	static double[][] X = {
			{0, 0},
			{1, 0},
			{0, 1},
			{1, 1},
			{3, 5}
	};
	static int[] Y = {
			0, 1, 1, 0, 2
	};
	static double[][] asdf = {
			{1.0, 0.0, 0.0}
	};

	public static void main(String[] args) {
		int len = 0;
		for (int value : Y) {
			if (len - 1 < value) {
				len = value + 1;

			}
		}
		System.out.println(len);
		double[][] temp = new double[Y.length][len];

		for (int i = 0; i < Y.length; i++) {
			temp[i][Y[i]] = 1;
		}
		for (double[] a : temp
		) {
			for (double b : a
			) {
				System.out.println(b);
			}
		}

		double[][] input = {
				{0, 0},
				{1, 0},
				{0, 1},
				{1, 1},
				{3, 5}
		};
//		Dataset dataset = new Dataset("XOR Gate output", X, Y, X[0].length - 1, 3, asdf[0].length - 1, true);
		Dataset dataset = new Dataset("XOR Gate output", X, Y, X[0].length - 1, 3, 3, true);
		dataset.train();
		List<String> predictions = dataset.predict(input);
		System.out.println(predictions);

	}

}