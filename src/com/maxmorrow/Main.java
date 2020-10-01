
package com.maxmorrow;

import com.maxmorrow.lib.Interactor;

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

	public static void main(String[] args) {
		double[][] input = {
				{0, 0},
				{1, 0},
				{0, 1},
				{1, 1},
				{3, 5}
		};

		// Instance of Interactor class to simplify the use of the NN library
		Interactor simplifier = new Interactor("XOR Gate output", X, Y, 2, 10, 3, true);
		simplifier.train();

		//storage and output of NN predictions
		List<String> predictions = simplifier.predict(input);
		System.out.println(predictions);

	}

}