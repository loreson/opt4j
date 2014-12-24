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
 

package org.opt4j.benchmarks.zdt;

import java.util.Random;

import org.opt4j.benchmarks.BinaryString;
import org.opt4j.benchmarks.N;
import org.opt4j.core.common.random.Rand;
import org.opt4j.core.problem.Creator;

import com.google.inject.Inject;

/**
 * The {@link ZDT5BinaryCreator} for the ZDT5 problem.
 * 
 * @author lukasiewycz
 * 
 */
public class ZDT5BinaryCreator implements Creator<BinaryString> {

	protected final Random random;

	protected final int length;

	/**
	 * Constructs a {@link ZDT5BinaryCreator}.
	 * 
	 * @param random
	 *            the random number generator
	 * @param n
	 *            the n value
	 */
	@Inject
	public ZDT5BinaryCreator(Rand random, @N int n) {
		super();
		this.random = random;

		length = ((n > 0) ? 30 : 0) + (n - 1) * 5;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.opt4j.core.problem.Creator#create()
	 */
	@Override
	public BinaryString create() {
		BinaryString string = new BinaryString();
		string.init(random, length);
		return string;
	}

}
