
package org.opt4j.tutorial.oilfields;

import static org.opt4j.core.Objective.INFEASIBLE;
import org.opt4j.core.Objective.Sign;
import org.opt4j.core.Objectives;
import org.opt4j.core.problem.Evaluator;
import org.opt4j.tutorial.oilfields.Oilfield;
import com.google.inject.Inject;



public class OilfieldEvaluator implements Evaluator<Oilfield> {

	protected final OilfieldProblem problem;

	@Inject
	public OilfieldEvaluator(OilfieldProblem problem) {
		this.problem = problem;
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see org.opt4j.core.problem.Evaluator#evaluate(org.opt4j.core.Phenotype)
	 */
	@Override
	public Objectives evaluate(Oilfield field) {
		Objectives obj = new Objectives();
		
		if (field.isFeasible()) {
			int sum = sum(field);
			obj.add("objective", Sign.MAX, sum);
		} else {
			obj.add("objective", Sign.MAX, INFEASIBLE);
		}
		return obj;
	}

	/**
	 * Sums the costs of a {@link QueensBoard} in a specific dimension {@code d}
	 * .
	 * 
	 * @param board
	 *            the board
	 * @param d
	 *            the dimension
	 * @return the sum of the costs
	 */
	private int sum(Oilfield field) {
		int sum = 0;
		for (int i = 0; i < problem.size(); i++) {
			for (int j = 0; j < problem.size(); j++) {
				if (field.isPicked(i, j)) {
					sum += problem.costs(i, j);
				}
			}
		}
		return sum;
	}
}
