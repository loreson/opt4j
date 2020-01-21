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
import java.util.Map;
import java.util.HashMap;
import java.util.LinkedHashMap;

import static java.util.stream.Collectors.*;
import static java.util.Map.Entry;

// TODO maybe rework this into somehting like "DistanceBasedneighborhoodCreation" and then leave space to what should be used as a "Distance"
public class EuclideanNeighborhoodCreation implements NeighborhoodCreation{

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
        Map<Integer, Double> distances = new HashMap<>();
        for(int i = 0; i < numCandidates; i++){
            WeightVector e = candidates.get(i);
            double distance = v.euclideanDistance(e);
            distances.put(i, distance);
        }

        // from https://www.javacodegeeks.com/2017/09/java-8-sorting-hashmap-values-ascending-descending-order.html
        Map<Integer, Double> sorted = distances
            .entrySet()
            .stream()
            .sorted(Map.Entry.comparingByValue())
            .collect(
                toMap(Entry::getKey, Entry::getValue, (e1, e2) -> e2,
                    LinkedHashMap::new));

        int[] neighborhood = new int[neighborhoodSize];
        int i = 0;
        for(Integer key : sorted.keySet()){
            if(i >= neighborhoodSize)
                break;
            neighborhood[i] = key;
            i++;
            
        }
        return neighborhood;
    }
}