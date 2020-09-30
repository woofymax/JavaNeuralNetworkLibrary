package com.maxmorrow;

import com.maxmorrow.lib.NeuralNetwork;

import java.io.File;
import java.util.List;

public class ImageRecognition {
	File img = new File("");
	static double[][] X = {
			{0, 0},
			{1, 0},
			{0, 1},
			{1, 1},
			{3, 5}
	};
	static double[][] Y = {
			{1.0, 0.0, 0.0 },
			{0.0, 1.0, 0.0},
			{0.0, 1.0, 0.0},
			{1.0, 0.0, 0.0},
			{0.0, 0.0, 1.0}
};

	public static void main(String[] args) {

		NeuralNetwork nn = new NeuralNetwork(2, 10, 3);


		nn.fit(X, Y, 50000);
		double[][] input = {
				{0, 0}, {0, 1}, {1, 0}, {1, 1}, {3, 5}
		};
		List<Double> output;

		for (double d[] : input) {
			output = nn.predict(d);
			for (int i = 0; i < output.size(); i++) {
				output.set(i, (double) Math.round(output.get(i)));
			}
			System.out.println(output);
		}
	}
}
