/*******************************************************************************
 * Copyright (c) 2014 Opt4J
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *******************************************************************************/

package org.opt4j.tutorial.oilfields;

import static org.opt4j.core.Objective.Sign.MIN;

import org.opt4j.core.Objective;
import org.opt4j.core.Objectives;
import org.opt4j.core.problem.Evaluator;
import org.opt4j.core.problem.Priority;
import org.opt4j.tutorial.oilfields.Oilfield;
import org.opt4j.tutorial.oilfields.OilfieldProblem;

import com.google.inject.Inject;

/**
 * The evaluator for the {@link QueensProblem}.
 * 
 * @author lukasiewycz
 * 
 */
@Priority(-10)
public class OilfieldErrorEvaluator implements Evaluator<Oilfield> {

    protected final Objective error = new Objective("error", MIN);
	protected final OilfieldProblem problem;
	
	@Inject
	public OilfieldErrorEvaluator(OilfieldProblem problem) {
		this.problem = problem;
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see org.opt4j.core.problem.Evaluator#evaluate(org.opt4j.core.Phenotype)
	 */
	@Override
	public Objectives evaluate(Oilfield field) {
		int errors = countErrors(field);

		Objectives objectives = new Objectives();
		objectives.add(error, errors);
		field.setFeasible(errors == 0);
		return objectives;
	}


	private int countErrors(Oilfield field) {
		int count = 0;
		int numPicks = problem.numPicks();
		int size = problem.size();
		for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if(field.isPicked(i, j)){
                    count++;
                }
            }
        }
        return Math.abs(count - numPicks);
	}
}
