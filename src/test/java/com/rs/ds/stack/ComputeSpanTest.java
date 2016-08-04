package com.rs.ds.stack;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

/**
 * Created by ravisharma on 8/4/2016.
 */

public class ComputeSpanTest {

    private ComputeSpan compute;

    @Before
    public void setUp(){
        compute = new ComputeSpan();
    }

    @Test
    public void testComputeSpanWithEmptyArray(){
        int[] spans = compute.computeSpan(new int[0]);
        assertEquals(spans.length , 0);
    }

    @Test
    public void testComputeSpanWithNonEmptyArray(){
       int[] spans = compute.computeSpan(new int[]{10, 4, 5, 90, 120, 80});
        assertEquals(spans.length , 6);
        assertArrayEquals(spans , new int[]{1, 1, 2, 4 , 5  ,1});

    }
}
