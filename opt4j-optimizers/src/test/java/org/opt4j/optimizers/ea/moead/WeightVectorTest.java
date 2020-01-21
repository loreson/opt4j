
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

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.Rule;

import org.junit.rules.ExpectedException;
import java.lang.Math;

public class WeightVectorTest
{
    @Test
    public void testSize()
    {
        WeightVector vec0 = new WeightVector(new double[]{});
        assertEquals(0, vec0.size());
        WeightVector vec1 = new WeightVector(new double[]{1});
        assertEquals(1, vec1.size());
        double[] data = new double[10000];
        WeightVector vec3 = new WeightVector(data);
        assertEquals(10000, vec3.size());
    }
    @Rule
    public ExpectedException thrown = ExpectedException.none();
    @Test
    public void testGet()
    {
        
        WeightVector vec = new WeightVector(new double[]{0,1,2,3,4,5,6,7});
        assertEquals(0, vec.get(0), 0);
        assertEquals(5, vec.get(5), 0);
        assertEquals(7, vec.get(7), 0);
        thrown.expect(ArrayIndexOutOfBoundsException.class);
        vec.get(8);
    
    }
    @Test
    public void testL2Norm()
    {
        WeightVector vec0 = new WeightVector(new double[]{0});
        assertEquals(0, vec0.L2Norm(),2*Math.ulp(0.0));
        WeightVector vec1 = new WeightVector(new double[]{17});
        assertEquals(17, vec1.L2Norm(),2*Math.ulp(17.0));
        WeightVector vec2 = new WeightVector(new double[]{1,0});
        assertEquals(1, vec2.L2Norm(),2*Math.ulp(1.0));
        WeightVector vec3 = new WeightVector(new double[]{1,1});
        assertEquals(Math.sqrt(2), vec3.L2Norm(), 2*Math.ulp(Math.sqrt(2)));
    }
    @Test 
    public void testDot()
    {
        WeightVector vec0 = new WeightVector(new double[]{1,1});
        WeightVector vec1 = new WeightVector(new double[]{1,2});
        WeightVector vec2 = new WeightVector(new double[]{2,2,2});
        WeightVector vec3 = new WeightVector(new double[]{0.5, 1, 1.5});
        assertEquals(3, vec0.dot(vec1), 2*Math.ulp(3.0));
        assertEquals(6, vec2.dot(vec3), 2*Math.ulp(6.0));
        thrown.expect(IllegalArgumentException.class);
        vec0.dot(vec3);
    }

    @Test(expected = IllegalArgumentException.class)
    public void EuclideanDistanceThrowsExceptionTest()
    {
        WeightVector vec0 = new WeightVector(new double[]{1,1});
        WeightVector vec1 = new WeightVector(new double[]{1,1,1});
        vec0.euclideanDistance(vec1);
    }

    @Test 
    public void EuclideanDistanceBasicFunctionalityTest()
    {
        WeightVector vec0 = new WeightVector(new double[]{1,1});
        
        WeightVector vec1 = new WeightVector(new double[]{1,2});
        WeightVector vec2 = new WeightVector(new double[]{2,2});
        WeightVector vec3 = new WeightVector(new double[]{2,2,2});
        WeightVector vec4 = new WeightVector(new double[]{0.5, 1, 1.5});
        
        assertEquals(0, vec0.euclideanDistance(vec0), 2*Math.ulp(3.0));
        assertEquals(1, vec0.euclideanDistance(vec1), 2*Math.ulp(3.0));
        assertEquals(Math.sqrt(2), vec0.euclideanDistance(vec2), 2*Math.ulp(3.0));
        assertEquals(Math.sqrt(14)/2, vec3.euclideanDistance(vec4), 2*Math.ulp(3.0));
    }

}

