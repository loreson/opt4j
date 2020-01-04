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



import java.io.Console;
import java.util.ArrayList;
import java.util.List;

class MaximallySparseSelection implements Selection
{
    private double distance(WeightVector i, WeightVector j)
    {
        if(i.size() != j.size()){
            throw new IllegalArgumentException("distance between vectors of different dimension is not defined");
        }
        double dist = 0;
        for(int l = 0; l< i.size(); l++){
            double x = i.get(l) - j.get(l);
            dist += x*x;
        }
        return Math.sqrt(dist);
    }
    @Override
    public List<WeightVector> select(List<WeightVector> initial, int N){
        List<WeightVector> result = new ArrayList<WeightVector>();
        int M;
        if(initial.size() == 0){
            if(N == 0){
                return result; //return an empty List here
            }
            else{
            M = 0; //Otherwise this will throw later
            }
        }
        else{
             M = initial.get(0).size();
        }
        if(N < M){
            throw new IllegalArgumentException("Can not use MaximallySparseSelection to select less vectors than there are objectives");
        }
        if(  initial.size() < N-M){
            throw new IllegalArgumentException("to small initial set "+initial.size()+" to select "+ N +"Vectors");
        }
        for(int i = 0; i < M; i++)
        {
            double[] extreme = new double[M];
            extreme[i] = 1.0;
            result.add(new WeightVector(extreme));
        }
        for(int k = M; k<N; k++){
            
            int max =0;
            double maxDist = 0;
            for(int j = 0; j < initial.size(); j++){
                double dist = 0;
                for(int i =0; i< k; i++){
                    dist+= distance(result.get(i),initial.get(j));
                }
                if(dist > maxDist){
                    maxDist = dist;
                    max = j;
                }
            }
            result.add(initial.get(max));
            initial.remove(max);
        }

        return result;
    }
}