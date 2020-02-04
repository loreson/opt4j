
package org.opt4j.tutorial.oilfields;

import org.opt4j.core.config.Icons;
import org.opt4j.core.config.annotations.Icon;
import org.opt4j.core.config.annotations.Info;
import org.opt4j.core.config.annotations.Order;
import org.opt4j.core.problem.Creator;
import org.opt4j.core.problem.Decoder;
import org.opt4j.core.problem.ProblemModule;
import org.opt4j.core.start.Constant;

/**
 * The {@link OilfieldModule} is used for the configuration of the Oilfield problem.
 * Containing the size and dimensions of the problem as well as the
 * {@link Decoder} strategy.
 * 
 * @author lukasiewycz
 * 
 */
@Icon(Icons.PROBLEM)
@Info("The n-Oilfield problem as optimization problem with linear objective functions.")
public class OilfieldModule extends ProblemModule {

	@Info("The size of the board (size*size).")
	@Order(0)
	@Constant(value = "size", namespace = OilfieldProblem.class)
	protected int size = 40;

	@Info("Number of fields to pick")
	@Order(1)
	@Constant(value = "numPicks", namespace = OilfieldProblem.class)
	protected int numPicks = 3;

	@Info("The seed of the problem generator (for the objective function).")
	@Order(2)
	@Constant(value = "seed", namespace = OilfieldProblem.class)
	protected int seed = 0;

	
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.opt4j.start.Opt4JModule#config()
	 */
	@Override
	public void config() {

		bind(OilfieldProblem.class).in(SINGLETON);

		bindProblem(OilfieldCreator.class, OilfieldDecoder.class, OilfieldErrorEvaluator.class);
		addEvaluator(OilfieldEvaluator.class);
	}

	/**
	 * Sets the size of the board of the Oilfield problem.
	 * 
	 * @param size
	 *            the size of the board
	 */
	public void setSize(int size) {
		this.size = size;
	}

	/**
	 * Returns the size of the board of the Oilfield problem.
	 * 
	 * @return the size of the board
	 */
	public int getSize() {
		return size;
	}

	/**
	 * Return the seed of the problem generator.
	 * 
	 * @return the seed of the problem generator
	 */
	public int getSeed() {
		return seed;
	}

	/**
	 * Sets the seed of the problem generator.
	 * 
	 * @param seed
	 *            the seed of the problem generator
	 */
	public void setSeed(int seed) {
		this.seed = seed;
	}

	/**
	 * Return the the number of fields tp pick.
	 * 
	 * @return the number of fields tp pick
	 */
	public int getNumPicks() {
		return numPicks;
	}

	/**
	 * Sets the the number of fields tp pick.
	 * 
	 * @param numPicks
	 *            the number of fields to pick
	 */
	public void setNumPicks(int numPicks) {
		this.numPicks = numPicks;
	}

}