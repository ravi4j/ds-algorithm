package com.rs.ds.array;

import org.junit.*;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * Created by ravisharma on 8/7/2016.
 */
public class DynamicArrayTest {

    Scanner sc ;

    @Before
    public void setUp(){
        sc = new Scanner(this.getClass().getClassLoader().getResourceAsStream("array/DynamicArray-Input.txt"));
    }


    @Test
    public void testDynamicArrayFromInputFile(){
        int noOfSequence = sc.nextInt();
        DynamicArray dynamicArray = new DynamicArray(noOfSequence);
        assertEquals(dynamicArray.getNoOfSequence() , noOfSequence);
        int noOfQuery = sc.nextInt();
         List<Integer> lastAnsSeq = new ArrayList<>();
        while(noOfQuery > 0) {
             int op = sc.nextInt();
             int seqNo = sc.nextInt();
             int element = sc.nextInt();
             switch (op){
                 case 1:
                     List<Integer> list = dynamicArray.findSeqList(seqNo);
                     int size = dynamicArray.findSeqList(seqNo).size();
                     dynamicArray.addElement(seqNo, element);
                     assertEquals(list.size() , size + 1);
                     assertEquals(list.get(size).intValue() , element);
                     break;
                 case 2:
                     int lastAns = dynamicArray.findLastAnswer(seqNo, element);
                     lastAnsSeq.add(lastAns);

             }
            noOfQuery--;
        }
        assertArrayEquals(lastAnsSeq.toArray(new Integer[0]) , new Integer[]{Integer.valueOf(7) , Integer.valueOf(3)});
    }

    @Test
    public void testAddElementQuery(){
        DynamicArray dynamicArray = new DynamicArray(2);

        dynamicArray.addElement(0,5);
        List<Integer> seq = dynamicArray.findSeqList(0);
        assertEquals(seq.size() , 1);
        assertEquals(seq.get(0).intValue() , 5);

        dynamicArray.addElement(1,7);
        seq = dynamicArray.findSeqList(1);
        assertEquals(seq.size() , 1);
        assertEquals(seq.get(0).intValue() , 7);

        dynamicArray.addElement(0,3);
        seq = dynamicArray.findSeqList(0);
        assertEquals(seq.size() , 2);
        assertEquals(seq.get(1).intValue() , 3);
    }

    @Test
    public void testFindLastAnswer(){
        DynamicArray dynamicArray = new DynamicArray(2);
        dynamicArray.addElement(0,5);
        dynamicArray.addElement(1,7);
        dynamicArray.addElement(0,3);

        int lastAns = dynamicArray.findLastAnswer(1,0);
        assertEquals(lastAns , 7);
        lastAns = dynamicArray.findLastAnswer(1,1);
        assertEquals(lastAns , 3);
    }


    @After
    public void destroy(){
        sc.close();
    }

}
