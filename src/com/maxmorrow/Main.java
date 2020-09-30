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
	static double[][] Y = {
			{1.0, 0.0, 0.0},
			{0.0, 1.0, 0.0},
			{0.0, 1.0, 0.0},
			{1.0, 0.0, 0.0},
			{0.0, 0.0, 1.0}
	};

	public static void main(String[] args) {
//		int len = 0;
//		for (int value : Y) {
//			if (len - 1 < value) {
//				len = value + 1;
//
//			}
//		}
//		System.out.println(len);
//		double[][] temp = new double[Y.length][len];
//
//		for (int i = 0; i < Y.length; i++) {
//			temp[i][Y[i]] = 1;
//		}
//		for (double[]a:temp
//			 ) {
//			for (double b: a
//				 ) {
//				System.out.println(b);
//			}
//		}

		double[][] input = {
				{0, 0},
				{1, 0},
				{0, 1},
				{1, 1},
				{3, 5}
		};
//		Dataset dataset = new Dataset("XOR Gate output", X, Y, X[0].length - 1, 10, Y.length - 1, true);
//		dataset.train();
//		List<String> predictions = dataset.predict(input);
//		System.out.println(predictions);
		NeuralNetwork model = new NeuralNetwork(2, 10, 3);


		double[][] y = {
				{1.0, 0.0, 0.0},
				{0.0, 1.0, 0.0},
				{0.0, 1.0, 0.0},
				{1.0, 0.0, 0.0},
				{0.0, 0.0, 1.0}
		};
		model.fit(X, y, 500000);
		ArrayList<ArrayList<Double>> out = new ArrayList<>();
		ArrayList<Double> output = new ArrayList<>();
		ArrayList<ArrayList<Double>> roundedOutput = new ArrayList<>();
		for (double d[] : input) {
			output = (ArrayList<Double>) model.predict(d);

			out.add(output);
		}
		DecimalFormat format = new DecimalFormat("##.######");

		for (int i = 0; i < out.size(); i++) {
			for (int j = 0; j < out.get(1).size(); j++) {
				roundedOutput.add(new ArrayList<>());
				roundedOutput.get(i).add(null);
				roundedOutput.get(i).set(j, (double) Math.round(out.get(i).get(j)));
			}
		}
		ArrayList<Integer> fixedOutputs = new ArrayList<>();
		for (ArrayList<Double> a: roundedOutput
			 ) {
			fixedOutputs.add(a.indexOf(1.0));
		}
		System.out.println(fixedOutputs.toString() +"              "+ roundedOutput.toString());
	}

}