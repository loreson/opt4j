/*******************************************************************************
 * Copyright (c) 2019 Opt4J
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

package org.opt4j.optimizers.ea.moead;

import org.opt4j.core.config.annotations.Info;
import org.opt4j.core.config.annotations.Order;
import org.opt4j.core.optimizer.MaxIterations;
import org.opt4j.core.optimizer.OptimizerModule;
import org.opt4j.core.start.Constant;

/**
 * The {@link MultiobjectiveEvolutionaryAlgorithmModule} configures the
 * {@link MultiobjectiveEvolutionaryAlgorithm}.
 * 
 * @author Johannes-Sebastian See
 * 
 */

@Info("Multi-Objective Evolutionary Algorithm")
public class MultiobjectiveEvolutionaryAlgorithmModule extends OptimizerModule {

	@Info("The number of generations.")
	@Order(0)
	@MaxIterations
	protected int generations = 100;

	@Constant(value = "m", namespace = MultiobjectiveEvolutionaryAlgorithm.class)
	@Info("The number of objectives per subproblem")
	@Order(1)
	protected int m = 5;

	@Constant(value = "N", namespace = MultiobjectiveEvolutionaryAlgorithm.class)
	@Info("The number of the subproblems considered.")
	@Order(2)
	protected int N = 10;

	@Constant(value = "T", namespace = MultiobjectiveEvolutionaryAlgorithm.class)
	@Info("The number of the weight vectors in the neighborhood.")
	@Order(3)
	protected int T = 10;

	@Constant(value = "numberOfParents", namespace = MultiobjectiveEvolutionaryAlgorithm.class)
	@Info("The number of Parents from which the new individual will be created from.")
	@Order(4)
	protected int numberOfParents = 2;

	@Constant(value = "newIndividuals", namespace = MultiobjectiveEvolutionaryAlgorithm.class)
	@Info("The number of new solutions per iteration")
	@Order(4)
	protected int newIndividuals = 1;



	/**
	 * Returns the number of generations.
	 * 
	 * @return the number of generations
	 */
	public int getGenerations() {
		return generations;
	}

	/**
	 * Sets the number of generations.
	 * 
	 * @see #getGenerations
	 * @param generations
	 *            the number of generations
	 */
	public void setGenerations(int generations) {
		this.generations = generations;
	}

	
	/**
	 * Returns the number of subproblems.
	 * 
	 * @return the number of subproblems
	 */
	public int getSubproblemsCount() {
		return N;
	}

	/**
	 * Sets the number of subproblems.
	 * 
	 * @param N the number of subproblems
	 */
	public void setSubproblemsCount(int N) {
		this.N = N;
	}

	

	/**
	 * Returns The number of the weight vectors in the neighborhood {@code T}.
	 * 
	 * @return The number of the weight vectors in the neighborhood
	 */
	public int getWeightVectorsPerNeighborhood() {
		return T;
	}

	/**
	 * Sets the number of weight vectors {@code T}.
	 * 
	 * @param T
	 *            The number of the weight vectors
	 */
	public void setWeightVectorsPerNeighborhood(int T) {
		this.T = T;
	}

	/**
	 * Returns The number of objectives per subproblem {@code m}.
	 * 
	 * @return The number of objectives per subproblem
	 */
	public int getObjectivesCount() {
		return m;
	}

	/**
	 * Sets the number of objectives per subproblem {@code m}.
	 * 
	 * @param m
	 *            The number of objectives per subproblem
	 */
	public void setObjectivesCount(int m) {
		this.m = m;
	}

	/**
	 * Returns The number of new Individuals per iteration {@code newIndividuals}.
	 * 
	 * @return The number of new Individuals per iteration
	 */
	public int getnewIndividualsCount() {
		return newIndividuals;
	}

	/**
	 * Sets the number of new individuals per iteration {@code newIndividuals}.
	 * 
	 * @param newIndividuals
	 *            The number of new Individuals per iteration
	 */
	public void setNewIndividualsCount(int newIndividuals) {
		this.newIndividuals = newIndividuals;
	}

	


	/*
	 * (non-Javadoc)
	 * 
	 * @see org.opt4j.start.Opt4JModule#config()
	 */
	@Override
	public void config() {
		bindIterativeOptimizer(MultiobjectiveEvolutionaryAlgorithm.class);
	}
}
