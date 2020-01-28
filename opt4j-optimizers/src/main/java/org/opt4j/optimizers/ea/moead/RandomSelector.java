package org.opt4j.optimizers.ea.moead;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class RandomSelector implements Selector {

	/**
	 * Randomly selects parents from the provided neighbourhood
	 */
	@Override
	public List<Integer> selectParents(int[] neighbourhood, int numberOfParents) {
		assertIsValidArray(neighbourhood);

		int neighbourhoodSize = neighbourhood.length;
		List<Integer> parents = new ArrayList<>();

		assertIsValidNumberOfParents(numberOfParents, neighbourhoodSize);

		if (neighbourhoodSize == numberOfParents) {
			parents.add(neighbourhood[0]);
			parents.add(neighbourhood[1]);
		} else {
			// First copy all int[] entries into collection
			List<Integer> convertedList = IntStream.of(neighbourhood).boxed().collect(Collectors.toList());

			// then shuffle list and pick correct amount
			Collections.shuffle(convertedList);
			parents = convertedList.subList(0, numberOfParents);
		}

		return parents;
	}

	private void assertIsValidNumberOfParents(int numberOfParents, int neighbourhoodSize) {
		if (numberOfParents < 2) {
			throw new IllegalArgumentException("Provided number of parents must be greater or equals 2!");
		}

		if (neighbourhoodSize < numberOfParents) {
			throw new IllegalArgumentException("Can not pick " + numberOfParents + " from neighbourhood containing "
					+ neighbourhoodSize + " item(s)!");
		}
	}

	private void assertIsValidArray(int[] neighbourhood) {
		if (neighbourhood == null) {
			throw new NullPointerException("Provided neighbourhood array is null!");
		}
	}
}
