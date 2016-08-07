package com.rs.ds.stack;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Created by ravisharma on 8/6/2016.
 */
public class BalanceBrackets {

    private Deque<Character> stack = null;
    private static final String OPENING_BRACKETS = "([{";
    private static final String CLOSING_BRACKETS = ")]}";

    public BalanceBrackets(){
        this.stack = new ArrayDeque<>();
    }

    public String isBalanced(String input) {

        return _isBalanced(input) ? "YES" : "NO";
    }

    private boolean _isBalanced(String input) {
        boolean balanced = true;

        if(input == null || input.isEmpty()){
            throw new RuntimeException("Input is null or empty");
        }

        char[] brackets = input.toCharArray();

        for(int i = 0 ; i <brackets.length ; i++){
            char bracket = brackets[i];
            if(_isOpeningBracket(bracket)){
                stack.push(Character.valueOf(bracket));
                continue;
            }

            if(_isClosingBracket(bracket)){
                if(stack.isEmpty()) return false;
                char top  = stack.peek().charValue();
                if(_isOpeningBracket(top)){
                    if(OPENING_BRACKETS.indexOf(top) != CLOSING_BRACKETS.indexOf(bracket)){
                        return  false;
                    } else {
                        stack.pop();
                    }
                } else {
                    return false;
                }
            }
        }
        return balanced;
    }

    private boolean _isOpeningBracket(char ch){
        return  OPENING_BRACKETS.indexOf(ch) != -1 ;
    }

    private boolean _isClosingBracket(char ch){
        return  CLOSING_BRACKETS.indexOf(ch) != -1 ;
    }
}
