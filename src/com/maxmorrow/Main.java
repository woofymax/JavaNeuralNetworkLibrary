
package com.maxmorrow;

import com.maxmorrow.lib.Interactor;

import java.util.List;

public class Main {
	//region ANSI colors definition
	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_BLACK = "\u001B[30m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_BLUE = "\u001B[34m";
	public static final String ANSI_PURPLE = "\u001B[35m";
	public static final String ANSI_CYAN = "\u001B[36m";
	public static final String ANSI_WHITE = "\u001B[37m";
	//endregion
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
		System.out.println(ANSI_GREEN +  predictions + ANSI_RESET);

	}

}