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

import java.util.Random;

import org.opt4j.core.start.Constant;

import com.google.inject.Inject;

/**
 * The {@link OilfieldProblem} information. Contains the size of the field and the
 * costs of the fields.
 * 
 * @author lukasiewycz
 * 
 */
public class OilfieldProblem {

    protected final int[][] field;
    
    protected final int numPicks;

	protected final int size;

    protected final Random random;
    

	/**
	 * Constructs a new {@link OilfieldProblem}.
	 * 
	 * @param size
	 *            the size of the field
	 * @param numPicks
	 *            the number of subfields to take
	 * @param seed
	 *            the seed for the random number generator
	 */
	@Inject
	public OilfieldProblem(@Constant(value = "size", namespace = OilfieldProblem.class) int size,
			@Constant(value = "numPicks", namespace = OilfieldProblem.class) int numPicks,
            @Constant(value = "seed", namespace = OilfieldProblem.class) int seed){
		random = new Random(seed);

        this.size = size;
        this.numPicks = numPicks;
		field = new int[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                field[i][j] = random.nextInt(100);
            }
        }

	}

	/**
	 * Returns the costs for one subfield.
	 * 
	 * @param i
	 *            the row
	 * @param j
	 *            the column
	 * @return the costs of this subfield in the dimension
	 */
	public int costs(int i, int j) {
		return field[i][j];
    }
    
    /**
	 * Returns the field.
	 * 
	 * @return the field
	 */
    public int[][] field() {
        return field;
    }

	/**
	 * Returns the size of the field.
	 * 
	 * @return the size of the field
	 */
	public int size() {
		return size;
    }
    
    /**
	 * Returns the number of picks.
	 * 
	 * @return the number of picks
	 */
    public int numPicks() {
        return numPicks;
    }

}
