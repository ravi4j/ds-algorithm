package com.rs.ds.array;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ravisharma on 8/7/2016.
 */
public class DynamicArray {

    private int noOfSequence;
    private Map<Integer , List<Integer>> sMap;
    private int lastAns = 0;
    private DynamicArray _self;

    public DynamicArray(int noOfSequence){
        this._self = this;
        _self.noOfSequence = noOfSequence;
        _self.buildSequence();
    }

    private DynamicArray buildSequence(){
        _self.sMap = new HashMap<>();
        for(int i = 0 ; i < noOfSequence ; i++ ){
            _self.sMap.put(Integer.valueOf(i) , new ArrayList<>());
        }
        return _self;
    }

    public int getNoOfSequence(){
        return _self.sMap.size();
    }

    public int getLastAns(){
        return _self.lastAns;
    }


    public void addElement(int x , int y){
        List<Integer> list = findSeqList(x);
        list.add(Integer.valueOf(y));
    }

    public int findLastAnswer(int x , int y) {
        List<Integer> list = findSeqList(x);
        int index = y % list.size();
        lastAns = list.get(index).intValue();
        return lastAns;
    }

    public List<Integer> findSeqList(int x) {
        int seqIndex = (x ^ _self.lastAns) % _self.noOfSequence;
        return  sMap.get(seqIndex);
    }
}




