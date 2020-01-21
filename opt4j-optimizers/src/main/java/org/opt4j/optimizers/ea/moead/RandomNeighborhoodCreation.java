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
 

package org.opt4j.optimizers.ea.moead;

import java.util.List;
import java.util.Random;

public class RandomNeighborhoodCreation implements NeighborhoodCreation{
    private Random random;

    public RandomNeighborhoodCreation(){
        random = new Random();
    }

    public RandomNeighborhoodCreation(long seed){
        random = new Random(seed);
    }
   
    public int[] create(WeightVector v, List<WeightVector> candidates, int neighborhoodSize){
        int numCandidates = candidates.size(); 
        if(numCandidates < neighborhoodSize){
            throw new IllegalArgumentException("Cannot create a neighborhood of size "+ neighborhoodSize + "with only "+ numCandidates + "subproblems!");
        }
        if(v == null){
            throw new IllegalArgumentException("Reference weight vector cannot be null!");
        }
        if(neighborhoodSize == 0){
            throw new IllegalArgumentException("Neighborhood size cannot be 0!");
        }
        int[] neighborhood = new int[neighborhoodSize];
        for(int i = 0; i < neighborhoodSize; i++){
            neighborhood[i] = random.nextInt(numCandidates);
        }
        
        return neighborhood;
    }
}