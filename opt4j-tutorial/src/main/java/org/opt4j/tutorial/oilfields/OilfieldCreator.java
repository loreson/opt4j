package org.opt4j.tutorial.oilfields;

import java.util.Random;

import org.opt4j.core.common.random.Rand;
import org.opt4j.core.genotype.BooleanGenotype;
import org.opt4j.core.problem.Creator;
import org.opt4j.core.problem.Decoder;
import org.opt4j.tutorial.oilfields.OilfieldProblem;

import com.google.inject.Inject;

public class OilfieldCreator implements Creator<BooleanGenotype>
{   
    protected final OilfieldProblem problem;
    protected final Random random;
    
    @Inject
    public OilfieldCreator(Random random, OilfieldProblem problem){
        this.problem = problem;
        this.random = random;
    }

    @Override
	public BooleanGenotype create() {
        int size = problem.size();
        int numPicks = problem.numPicks(); 
        boolean[] picks = new boolean[size * size];
        for(int i = 0; i < numPicks; i++){
            int k = random.nextInt(size * size); //should probably take a new random for this
            picks[k] = true;
        }

		BooleanGenotype vector = new BooleanGenotype();
		vector.init(picks);

		return vector;
	}
}




