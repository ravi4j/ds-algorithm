package com.rs.ds.stack;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by ravisharma on 8/6/2016.
 */
public class BalanceBracketsTest {

    private BalanceBrackets balanceBrackets = null;

    @Before
    public void setBalanceBrackets(){
        balanceBrackets = new BalanceBrackets();
    }

    @Test(expected = RuntimeException.class)
    public void testWithEmptyString(){
        balanceBrackets.isBalanced("");
        fail("Expected an RuntimeException to be thrown");

    }

    @Test
    public void testWithNullInput(){
        try{
            balanceBrackets.isBalanced(null);
            fail("Expected an RuntimeException to be thrown");
        } catch(RuntimeException ex){
            assertEquals(ex.getMessage() , "Input is null or empty");
        }

    }

    @Test
    public void testBalancedBrackets(){
        //{{[[(())]]}}
        //([])[()]{[]}
        String balanced = balanceBrackets.isBalanced("{{[[(())]]}}");
        assertEquals(balanced , "YES");
    }

    @Test
    public void testBalancedNonBrackets(){
        //{{[[(())]]}}
        //([])[()]{[]}
        String balanced = balanceBrackets.isBalanced("(ABCEDFG)");
        assertEquals(balanced , "YES");
    }

    @Test
    public void testNonBalancedBrackets(){
        String balanced = balanceBrackets.isBalanced("{[(])}");
        assertEquals(balanced , "NO");
    }

    @Test
    public void testOnlyOpeningBalancedBrackets(){
        String balanced = balanceBrackets.isBalanced("([{");
        assertEquals(balanced , "NO");
    }

    @Test
    public void testOnlyClosingBalancedBrackets(){
        String balanced = balanceBrackets.isBalanced(")]}");
        assertEquals(balanced , "NO");
    }
}
