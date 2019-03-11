package com.xiaostudy.test;

import com.xiaostudy.util.MathUtil;

public class Test_helenFormula {
    public static void main(String[] agrs) {
        Object o = MathUtil.helenFormula(3, 4, 5);
        System.out.println(o);
        o = MathUtil.helenFormula(3, 4, 6);
        System.out.println(o);

//        System.out.println( Math.pow(27, (double)1/3));
    }
}
