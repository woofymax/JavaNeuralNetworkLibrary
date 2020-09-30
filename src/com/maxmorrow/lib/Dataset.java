package com.maxmorrow.lib;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public final class Dataset {
	private double[][] X, Y, input;
	private String type;
	private int inputs, hidden, output;
	public NeuralNetwork model;
	public boolean round;

	public Dataset(String type, double[][] X, int[] Y, int inputs, int hidden, int output, boolean round) {
		this.X = X;
		this.Y = serializeOutputs(Y);
		this.type = type;
		this.inputs = inputs;
		this.hidden = hidden;
		this.output = output;
		this.round = round;
	}

	public double[][] getX() {
		return X;
	}

	public double[][] input() {
		return input;
	}

	public String getType() {
		return type;
	}

	public double[][] getY() {
		return Y;
	}

	public double[][] serializeOutputs(int[] Y) {
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

		return temp;
	}

	public void train() {
		model = new NeuralNetwork(inputs, hidden, output);


		System.out.println(deserializeOutputs(Y) + "deserialized Y");
		model.fit(X, Y, 500000);

	}

	public List<String> predict(double[][] input) {
		List<Double> output;
		ArrayList<String> out = new ArrayList<String>();
		for (double d[] : input) {
			output = model.predict(d);
			if (round) {
				for (int i = 0; i < output.size(); i++) {
					output.set(i, (double) Math.round(output.get(i)));
				}
			}
			out.add(output.toString());
		}
		return out;
	}

	public ArrayList<Integer> deserializeOutputs(double[][] input) {
		ArrayList<ArrayList<Double>> asdf = new ArrayList<>();
		for (int i = 0; i < input.length; i++) {
			asdf.add(new ArrayList<>());
			for (int j = 0; j < input[0].length; j++) {
				asdf.get(i).add(input[i][j]);
			}
		}
		System.out.println(asdf.toString() + "asdf tostring");

		ArrayList<ArrayList<Double>> roundedOutput = new ArrayList<>();



		for (int i = 0; i < asdf.size(); i++) {
			roundedOutput.add(new ArrayList<>());
			for (int j = 0; j < asdf.get(0).size(); j++) {

				roundedOutput.get(i).add((double) Math.round(asdf.get(i).get(j)));

			}
		}
		ArrayList<Integer> fixedOutputs = new ArrayList<>();
		for (ArrayList<Double> a: roundedOutput
		) {
			fixedOutputs.add(a.indexOf(1.0));
		}
		return fixedOutputs;
	}
}
