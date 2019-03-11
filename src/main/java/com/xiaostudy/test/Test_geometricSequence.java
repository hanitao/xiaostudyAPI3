package com.xiaostudy.test;

import com.xiaostudy.util.MathUtil;

public class Test_geometricSequence {
    public static void main(String[] agrs) {
        Object o = MathUtil.geometricSequence( 1, 3, 0.5);
        System.out.println(o);
        o = MathUtil.geometricSequence( 1, 3,  1.5);
        System.out.println(o);
        o = MathUtil.geometricSequence( 1, 3,  1.25);
        System.out.println(o);
    }
}
