package org.opt4j.optimizers.ea.moead;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomSelector implements Selector {
	private Random random = new Random();

	/**
	 * Randomly selects parents from the provided neighbourhood
	 */
	@Override
	public List<Integer> selectParents(int[] neighbourhood, int numberOfParents) {		
		int size = neighbourhood.length;
		List<Integer> parents = new ArrayList<>();
		
		if(size < numberOfParents) {
			throw new IllegalArgumentException("Can not pick " + numberOfParents + " from neighbourbhood containing " + size + " item(s)!" );
		} else if(size == numberOfParents) {
			parents.add(neighbourhood[0]);
			parents.add(neighbourhood[1]);			
		} else {
			List<Integer> convertedList = new ArrayList<>(size);			

			// First copy all int[] entries into collection
			for (int i=0; i<size; i++) {
				convertedList.add(neighbourhood[i]);
			}
			
			// then pick randomly and remove picked one
			for(int i=0; i<numberOfParents; i++) {
				int r = random.nextInt(size-i);
				
				parents.add(convertedList.get(r));
				convertedList.remove(r);
			}			
		}
		
		return parents;
	}
}
