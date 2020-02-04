package org.opt4j.tutorial.oilfields;

public class Oilfield {

	protected boolean[][] picked;
	protected final int[][] field;
	private boolean feasible;
	

	protected final int size;

	public Oilfield(int size, int[][] field) {
		this.size = size;
		this.field = field;
		picked = new boolean[size][size];

		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				picked[i][j] = false;
			}
		}
	}

	public int getValue(int i, int j){
		return this.field[i][j];
	}

	public void select(int i, int j, boolean value) {
		this.picked[i][j] = value;
	}

	public boolean isPicked(int i, int j) {
		return this.picked[i][j];
	}

	public boolean isFeasible() {
		return this.feasible;
	}

	public void setFeasible(boolean f) {
		this.feasible = f;
	}

	public int size() {
		return this.size;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		String s = "";

		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (picked[i][j]) {
					s += "[" + i + "," + j+ "]:"+ field[i][j] + " ";
				}
			}
		}
		printBoard();
		return s;
	}

	public void printBoard() {
		for (int i = 0; i < field.length; i++) {
			System.out.print("| ");
			for (int j = 0; j < field.length; j++) {
				System.out.print(field[i][j] + " | ");
			}
			System.out.println("");
		}
		System.out.println("---------------------------------");
	}
}
