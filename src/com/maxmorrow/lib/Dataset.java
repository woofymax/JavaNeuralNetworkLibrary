package com.maxmorrow.lib;

import com.maxmorrow.lib.NeuralNetwork;

import java.util.ArrayList;
import java.util.List;

public final class Dataset {
	private double[][] X, Y, input;
	private String type;
	private int inputs, hidden, output;
	public NeuralNetwork model;
	public boolean round;

	public Dataset(String type, double[][] X, double[][] Y, int inputs, int hidden, int output, boolean round) {
		this.X = X;
//		this.Y = serializeOutputs(Y);
		this.Y = Y;
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


		double[][] y = {
				{1.0, 0.0, 0.0 },
				{0.0, 1.0, 0.0},
				{0.0, 1.0, 0.0},
				{1.0, 0.0, 0.0},
				{0.0, 0.0, 1.0}
		};
		model.fit(X, y, 500000);

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

	public double[] deserializeOutputs(double[][] input) {
		double[] temp = new double[input.length];
		for (double[] value : input
		) {
			for (int i = 0; i < value.length; i++) {
				if (value[i] == 1) {
					temp[i] = i;
				}
			}
		}

		return temp;
	}
	public double[][] roundOutputs(double[][] outs) {
		double[][] temp = new double[outs.length][outs[0].length];
		for (int i = 0; i < outs.length; i++) {
			for (int j = 0; j < outs[0].length; j++) {
				temp[i][j] = Math.round(outs[i][j]);
			}
		}
		return temp;
	}
}
