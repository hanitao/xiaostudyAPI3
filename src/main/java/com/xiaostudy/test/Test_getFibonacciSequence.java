package com.xiaostudy.test;

import com.xiaostudy.util.MathUtil;

public class Test_getFibonacciSequence {

    public static void main(String[] agrs) {
        Integer fibonacciSequence = 0;
        for(int i = 1; i < 10; i++) {
            fibonacciSequence = MathUtil.getFibonacciSequence(i);
            System.out.println(fibonacciSequence);
        }
    }
}
