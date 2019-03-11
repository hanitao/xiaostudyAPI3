package com.xiaostudy.test;

import com.xiaostudy.util.MathUtil;

public class Test_arithmeticSequence {

    public static void main(String[] agrs) {
        Object o = MathUtil.arithmeticSequence( 1, 100, 1);
        System.out.println(o);
        o = MathUtil.arithmeticSequence(1, 100,  1.5);
        System.out.println(o);
        o = MathUtil.arithmeticSequence( 1, 3,  1.5);
        System.out.println(o);
    }
}
