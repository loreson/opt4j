package org.opt4j.benchmarks.tauqc;

import java.util.Random;

import org.opt4j.core.genotype.DoubleGenotype;
import org.opt4j.core.problem.Creator;
import org.opt4j.core.start.Constant;

import com.google.inject.Inject;

public class TAUQCCreator implements Creator<DoubleGenotype> {

	protected double lowerX, upperX;

	protected Random random = new Random();

	@Override
	public DoubleGenotype create() {

		int[] bounds = getBounds();

		lowerX = bounds[0];
		upperX = bounds[1];

		DoubleGenotype genotype = new DoubleGenotype(lowerX, upperX);
		genotype.init(random, 1);
		return genotype;
	}

	@Inject
	public int[] getBounds(){
		//TODO: This does not work yet. How to get injected constants in method???
		@Constant(value = "xMin") int xMin;
		@Constant(value = "xMax") int xMax;

		int min = 0;
		int max = 5;

		return new int[]{ min, max };
	}
}
