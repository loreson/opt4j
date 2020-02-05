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
import org.opt4j.core.config.annotations.Ignore;
import org.opt4j.core.config.annotations.Order;
import org.opt4j.core.optimizer.MaxIterations;
import org.opt4j.core.optimizer.OptimizerModule;
import org.opt4j.core.start.Constant;

import org.opt4j.optimizers.ea.CrossoverRate;
import org.opt4j.optimizers.ea.ConstantCrossoverRate;

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

	@Constant(value = "n", namespace = MultiobjectiveEvolutionaryAlgorithm.class)
	@Info("The number of the subproblems considered.")
	@Order(2)
	protected int n = 10;

	@Constant(value = "t", namespace = MultiobjectiveEvolutionaryAlgorithm.class)
	@Info("The number of the weight vectors in the neighborhood.")
	@Order(3)
	protected int t = 10;

	@Constant(value = "numberOfParents", namespace = MultiobjectiveEvolutionaryAlgorithm.class)
	@Info("The number of Parents from which the new individual will be created from.")
	@Order(4)
	protected int numberOfParents = 2;

	@Constant(value = "newIndividuals", namespace = MultiobjectiveEvolutionaryAlgorithm.class)
	@Info("The number of new solutions per iteration")
	@Order(5)
	protected int newIndividuals = 1;

	@Info("Performs a crossover operation with this given rate.")
	@Order(6)
	@Constant(value = "rate", namespace = ConstantCrossoverRate.class)
	protected double crossoverRate = 0.95;

	
	@Ignore
	protected CrossoverRateType crossoverRateType = CrossoverRateType.CONSTANT;
	
	/**
	 * The {@link CrossoverRateType} allows to choose between different types of
	 * crossover rates.
	 * 
	 * @author glass
	 * 
	 */
	public enum CrossoverRateType {
		/**
		 * Use a constant crossover rate.
		 */
		CONSTANT;
	}

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
	 * Returns The number of objectives per subproblem {@code m}.
	 * 
	 * @return The number of objectives per subproblem
	 */
	public int getM() {
		return m;
	}

	/**
	 * Sets the number of objectives per subproblem {@code m}.
	 * 
	 * @param m
	 *            The number of objectives per subproblem
	 */
	public void setM(int m) {
		this.m = m;
	}
	
	/**
	 * Returns the number of subproblems.
	 * 
	 * @return the number of subproblems
	 */
	public int getN() {
		return n;
	}

	/**
	 * Sets the number of subproblems.
	 * 
	 * @param N the number of subproblems
	 */
	public void setN(int n) {
		this.n = n;
	}

	

	/**
	 * Returns The number of the weight vectors in the neighborhood {@code T}.
	 * 
	 * @return The number of the weight vectors in the neighborhood
	 */
	public int getT() {
		return t;
	}

	/**
	 * Sets the number of weight vectors {@code T}.
	 * 
	 * @param T
	 *            The number of the weight vectors
	 */
	public void setT(int t) {
		this.t = t;
	}

	/**
	 * Returns the number of parents from which to create new individuals {@code numberOfParents}.
	 * 
	 * @return the number of parents
	 */
	public int getNumberOfParents() {
		return numberOfParents;
	}

	/**
	 * Sets the number of parents from which to create new individuals {@code numberOfParents}.
	 * 
	 * @param newIndividuals
	 *            The number of new Individuals per iteration
	 */
	public void setNumberOfParents(int numberOfParents) {
		this.numberOfParents = numberOfParents;
	}

	/**
	 * Returns The number of new Individuals per iteration {@code newIndividuals}.
	 * 
	 * @return The number of new Individuals per iteration
	 */
	public int getnewIndividuals() {
		return newIndividuals;
	}

	/**
	 * Sets the number of new individuals per iteration {@code newIndividuals}.
	 * 
	 * @param newIndividuals
	 *            The number of new Individuals per iteration
	 */
	public void setnewIndividuals(int newIndividuals) {
		this.newIndividuals = newIndividuals;
	}
	
	/**
	 * Returns the type of crossover rate that is used.
	 * 
	 * @return the crossoverRateType
	 */
	public CrossoverRateType getCrossoverRateType() {
		return crossoverRateType;
	}

	/**
	 * Sets the type of crossover rate to use.
	 * 
	 * @param crossoverRateType
	 *            the crossoverRateType to set
	 */
	public void setCrossoverRateType(CrossoverRateType crossoverRateType) {
		this.crossoverRateType = crossoverRateType;
	}

	/**
	 * Returns the used crossover rate.
	 * 
	 * @return the crossoverRate
	 */
	public double getCrossoverRate() {
		return crossoverRate;
	}

	/**
	 * Sets the crossover rate.
	 * 
	 * @param crossoverRate
	 *            the crossoverRate to set
	 */
	public void setCrossoverRate(double crossoverRate) {
		this.crossoverRate = crossoverRate;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.opt4j.start.Opt4JModule#config()
	 */
	@Override
	public void config() {
		bindIterativeOptimizer(MultiobjectiveEvolutionaryAlgorithm.class);
		bind(CrossoverRate.class).to(ConstantCrossoverRate.class).in(SINGLETON);
	}
}
