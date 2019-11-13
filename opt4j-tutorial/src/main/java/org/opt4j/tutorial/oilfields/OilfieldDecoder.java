
package org.opt4j.tutorial.oilfields;

import java.util.Random;

import org.opt4j.core.common.random.Rand;
import org.opt4j.core.genotype.BooleanGenotype;
import org.opt4j.core.problem.Creator;
import org.opt4j.core.problem.Decoder;

import com.google.inject.Inject;

public class OilfieldDecoder implements Decoder<BooleanGenotype, Oilfield> {
	protected final OilfieldProblem problem;
    protected final Random random;
	
	@Inject
    public OilfieldDecoder(Random random, OilfieldProblem problem){
        this.problem = problem;
        this.random = random;
    }


	@Override
	public Oilfield decode(BooleanGenotype vector) {
		int size = problem.size();
		Oilfield field = new Oilfield(size, problem.field());
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				field.select(i, j, vector.get(i + j * size));
			}
		}

		return field;
	}
}
