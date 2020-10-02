package com.maxmorrow.lib;

import java.util.List;

public class NeuralNetwork {

	NNMatrix weights_ih, weights_ho, bias_h, bias_o;
	double l_rate = 0.01;

	public NeuralNetwork(int i, int h, int o) {
		weights_ih = new NNMatrix(h, i);
		weights_ho = new NNMatrix(o, h);

		bias_h = new NNMatrix(h, 1);
		bias_o = new NNMatrix(o, 1);

	}

	public List<Double> predict(double[] X) {
		NNMatrix input = NNMatrix.fromArray(X);
		NNMatrix hidden = NNMatrix.multiply(weights_ih, input);
		hidden.add(bias_h);
		hidden.sigmoid();

		NNMatrix output = NNMatrix.multiply(weights_ho, hidden);
		output.add(bias_o);
		output.sigmoid();
		return output.toArray();
	}

	public void fit(double[][] X, double[][] Y, int epochs) {
		for (int i = 0; i < epochs; i++) {
			int sampleN = (int) (Math.random() * X.length);
			this.train(X[sampleN], Y[sampleN]);
		}
	}

	public void train(double[] X, double[] Y) {
		NNMatrix input = NNMatrix.fromArray(X);
		NNMatrix hidden = NNMatrix.multiply(weights_ih, input);
		hidden.add(bias_h);
		//System.out.println("after bias_h");
		hidden.sigmoid();

		NNMatrix output = NNMatrix.multiply(weights_ho, hidden);
		output.add(bias_o);
		//System.out.println("after bias_o");
		output.sigmoid();

		NNMatrix target = NNMatrix.fromArray(Y);

	//	System.out.println(target.toArray().toString() + "Target <<<<<<<   " + output.toArray().toString() + "output <<<<<<<<<<<<" + Y[0] + "  y <");
		NNMatrix error = NNMatrix.subtract(target, output);
		NNMatrix gradient = output.derivative();
		gradient.multiply(error);
		gradient.multiply(l_rate);

		NNMatrix hidden_T = NNMatrix.transpose(hidden);
		NNMatrix who_delta = NNMatrix.multiply(gradient, hidden_T);

		weights_ho.add(who_delta);
		bias_o.add(gradient);

		NNMatrix who_T = NNMatrix.transpose(weights_ho);

	//	System.out.println(who_T.toArray().toString() + "\n" +  error.toArray().toString() + "< Who_t and error" + "\n" + hidden_T.toArray().toString());
		NNMatrix hidden_errors = NNMatrix.multiply(who_T, error);

		NNMatrix h_gradient = hidden.derivative();
		h_gradient.multiply(hidden_errors);
		h_gradient.multiply(l_rate);

		NNMatrix i_T = NNMatrix.transpose(input);
		NNMatrix wih_delta = NNMatrix.multiply(h_gradient, i_T);

		weights_ih.add(wih_delta);
		bias_h.add(h_gradient);

	}


}