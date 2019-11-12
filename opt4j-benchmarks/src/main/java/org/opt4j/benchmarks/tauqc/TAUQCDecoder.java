package org.opt4j.benchmarks.tauqc;

import org.opt4j.core.genotype.DoubleGenotype;
import org.opt4j.core.problem.Decoder;

public class TAUQCDecoder implements Decoder<DoubleGenotype, Double> {

	public Double decode(DoubleGenotype genotype) {
		double phenotype = genotype.get(0);
		return phenotype;
	}

}
