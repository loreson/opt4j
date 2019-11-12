package org.opt4j.benchmarks.tauqc;

import org.opt4j.core.config.annotations.Parent;
import org.opt4j.core.problem.ProblemModule;
import org.opt4j.core.start.Constant;


public class TAUQCModule extends ProblemModule {

	@Constant(value = "xMin")
	protected int xMin = 0;

	public int getXMin() {
		return xMin;
	}

	public void setXMin(int xMin) {
		this.xMin = xMin;
	}

	@Constant(value = "xMax")
	protected int xMax = 4;

	public int getXMax() {
		return xMax;
	}

	public void setXMax(int xMax) {
		this.xMax = xMax;
	}

	@Constant(value = "a")
	protected int a = -1;

	public int getA() {
		return a;
	}

	public void setA(int a) {
		this.a = a;
	}

	@Constant(value = "b")
	protected int b = 4;

	public int getB() {
		return b;
	}

	public void setB(int b) {
		this.b = b;
	}

	protected void config() {
		bindProblem(TAUQCCreator.class, TAUQCDecoder.class, TAUQCEvaluator.class);
	}

}
