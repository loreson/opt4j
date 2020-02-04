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

import java.util.List;
import java.util.ArrayList;
import java.util.Collection;

import org.opt4j.core.Individual;
import org.opt4j.core.IndividualFactory;
import org.opt4j.core.Objectives;
import org.opt4j.core.optimizer.IndividualCompleter;
import org.opt4j.core.optimizer.IterativeOptimizer;
import org.opt4j.core.optimizer.Population;
import org.opt4j.core.optimizer.TerminationException;
import org.opt4j.core.start.Constant;
import org.opt4j.operators.crossover.Crossover;
import org.opt4j.operators.mutate.Mutate;
import org.opt4j.optimizers.ea.Mating;
import org.opt4j.core.optimizer.Archive;
import org.opt4j.core.common.archive.UnboundedArchive;

import com.google.inject.Inject;

/**
 * TODO:
 * The {@link EvolutionaryAlgorithm} is an implementation of an Evolutionary
 * Algorithm based on the operators {@link Crossover} and {@link Mutate}. It
 * uses a {@link Selector} for the mating and environmental selection.
 * 
 * @author lukasiewycz, glass
 * 
 */
public class MultiobjectiveEvolutionaryAlgorithm implements IterativeOptimizer {

	protected final int m;

	protected final int N;

	protected final int T;

	protected final int newIndividuals;

	protected final Selector selector;

	protected final Mating mating;

	protected final Decomposition decomposition;

	protected final NeighbourhoodCreation neighbourhoodCreation;

	private final Population population;

	// maybe we don't need this? Is it possible to get an initial population?	
	private final PopulationInitialization populationInitialization;

	private final ReferencePointCreation referencePointCreation;
	
	private final Repair repair; 

	protected List<WeightVector> weights;
	
	protected List<int []> neighborhoods;

	protected Archive externalPopulation;

	protected double[] referencePoints;
	
	protected Individual[] x;

	/**
	 * Constructs an {@link MultiObjectiveEvolutionaryAlgorithm} with a {@link Population}, a
	 * {@link Selector}, a {@link mating}, a
	 * {@link decomposition}, a {@link repait}, the number of generations, the
	 * number of objective functions per subproblem, the number of subproblems, the number of wiehgt vectors in the neighborhood, 
	 * and the number of new Individuals per iteration.
	 * 
	 * @param population
	 *            the population
	 * @param selector
	 *            the selector
	 * @param mating
	 *            the mating method
	 * @param decomposition
	 * 			  the decomposition method
	 * @param repair
	 * 			  the repair method
	 * @param m
	 * 			  the number of objective functions	and entries of a weight vector
	 * @param N
	 *            the number of subproblems
	 * @param T
	 *            the number of weight vectors in the neighborhood
	 * @param newIndividuals
	 * 			  the number of new Individuals created by the mating method
	 */
	@Inject
	public MultiobjectiveEvolutionaryAlgorithm(
			Population population,
			Selector selector,
			Mating mating,
			Decomposition decomposition,
			NeighbourhoodCreation neighbourhoodCreation,
			PopulationInitialization populationInitialization,
			ReferencePointCreation referencePointCreation,
			Repair repair,
			@Constant(value = "m", namespace = MultiobjectiveEvolutionaryAlgorithm.class) int m,
			@Constant(value = "N", namespace = MultiobjectiveEvolutionaryAlgorithm.class) int N,
			@Constant(value = "T", namespace = MultiobjectiveEvolutionaryAlgorithm.class) int T,
			@Constant(value = "newIndividuals", namespace = MultiobjectiveEvolutionaryAlgorithm.class) int newIndividuals ) {
		this.selector = selector;
		this.mating = mating;
		this.decomposition = decomposition;
		this.neighbourhoodCreation = neighbourhoodCreation;
		this.populationInitialization = populationInitialization;
		this.referencePointCreation = referencePointCreation;
		this.repair = repair;
		this.m = m;
		this.N = N;
		this.T = T;
		this.newIndividuals = newIndividuals;
		this.population = population;

		if (m <= 0) {
			throw new IllegalArgumentException("Invalid m: " + m);
		}
		if (N <= 0) {
			throw new IllegalArgumentException("Invalid N: " + N);
		}
		if (T <= 0) {
			throw new IllegalArgumentException("Invalid T: " + T);
		}
		if (newIndividuals <= 0) {
			throw new IllegalArgumentException("Invalid newIndividuals: " + newIndividuals);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.opt4j.core.optimizer.IterativeOptimizer#initialize()
	 * 
	 */
	@Override
	public void initialize() {
		weights = decomposition.decompose(N, m);
		
		// Step 1.1
		externalPopulation = new UnboundedArchive();

		// Step 1.2
		neighborhoods = new ArrayList<>(N);
		for( int i = 0; i < N; i++){
			neighborhoods.add(neighbourhoodCreation.create(weights, T));
		}

		// Step 1.3
		// if(!population.isEmpty())
			// population = populationInitialization.initialization(m);

		x = new Individual[N];
		population.toArray(x);
		
		// Step 1.4
		referencePoints = new double[m];
		for ( int i = 0; i < m; i++){
			referencePoints[i] = referencePointCreation.create();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.opt4j.core.optimizer.IterativeOptimizer#next()
	 */
	@Override
	public void next() throws TerminationException {
		for( int i = 0; i < N; i++) {
			// Step 2.1) Reproduction
			List<Integer> parents = selector.selectParents(neighborhoods.get(i));
			List<Individual> parentCollection = new ArrayList<>(parents.size());
			for(int j = 0; j < parents.size(); j++)
				parentCollection.add(x[parents.get(i)]);
			
			Collection<Individual> offspring = mating.getOffspring( newIndividuals , parentCollection);
			Individual best = offspring.iterator().next();
			
			// Step 2.2) Improvement
			best = repair.repairSolution(best);
			while(offspring.iterator().hasNext()){
				Individual toCheck = offspring.iterator().next();
				if(toCheck.getObjectives().weaklyDominates(best.getObjectives()))
					best = toCheck;
			}
			// Step 2.3) Update of z ???

			// Step 2.4) Update of Neighboring Solutions
			// Unsure about this
			Objectives objectives = best.getObjectives();
			for(int j = 0; j < T; j++){
				Individual toCheck = x[ neighborhoods.get(i)[j] ];
				if(objectives.weaklyDominates(toCheck.getObjectives() )){
					x[ neighborhoods.get(i)[j] ] = best;
				}
			}

			// Step 2.5) Update of EP
			externalPopulation.update(best);
		}
	}
}
