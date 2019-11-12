package org.opt4j.benchmarks.tauqc;

import org.opt4j.core.Objective.Sign;
import org.opt4j.core.Objectives;
import org.opt4j.core.problem.Evaluator;
import org.opt4j.core.start.Constant;

import com.google.inject.Inject;

public class TAUQCEvaluator implements Evaluator<Double> {

	@Override
	public Objectives evaluate(Double phenotype) {
		double area = 0;

		//TODO: This does not work yet. How to get injected constants in method???
		// Parameters of getParam are fake at the moment
		int[] result = getParams(-1, 4);
		int a = result[0];
		int b = result[1];

		area = 0.5 * phenotype * ((a * Math.pow(phenotype, 2)) + (b * phenotype));

		Objectives objectives = new Objectives();
		objectives.add("objective", Sign.MAX, area);
		return objectives;
	}

	@Inject
	public int[] getParams(@Constant(value = "a") int a, @Constant(value = "b") int b){
		return new int[]{ a, b };
	}
}
