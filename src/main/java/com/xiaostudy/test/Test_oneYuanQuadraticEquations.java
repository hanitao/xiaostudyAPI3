package com.xiaostudy.test;

import com.xiaostudy.util.MathUtil;

import java.util.List;

public class Test_oneYuanQuadraticEquations {

    public static void main(String[] agrs) {
        //test1();
        //test2();
    }

    private static void test2() {
        List list = MathUtil.oneYuanQuadraticEquationsI(0, 4, 4);
        System.out.println(list);
        list = MathUtil.oneYuanQuadraticEquationsI(0, 4, 5);
        System.out.println(list);
        list = MathUtil.oneYuanQuadraticEquationsI(1, -4, 4);
        System.out.println(list);
        list = MathUtil.oneYuanQuadraticEquationsI(1, -4, -8);
        System.out.println(list);
        list = MathUtil.oneYuanQuadraticEquationsI(1, -4, 5);
        System.out.println(list);
        list = MathUtil.oneYuanQuadraticEquationsI(1, -4, 6);
        System.out.println(list);
        list = MathUtil.oneYuanQuadraticEquationsI(1, -4, 3);
        System.out.println(list);
        list = MathUtil.oneYuanQuadraticEquationsI(1, -4, 2);
        System.out.println(list);
    }

    private static void test1() {
        List list = MathUtil.oneYuanQuadraticEquations(1, 4, 4);
        System.out.println(list);
        list = MathUtil.oneYuanQuadraticEquations(1, -4, 4);
        System.out.println(list);
        list = MathUtil.oneYuanQuadraticEquations(1, -4, 4);
        System.out.println(list);
        list = MathUtil.oneYuanQuadraticEquations(1, -4, 5);
        System.out.println(list);
        list = MathUtil.oneYuanQuadraticEquations(1, -4, 3);
        System.out.println(list);
        list = MathUtil.oneYuanQuadraticEquations(1, -4, 2);
        System.out.println(list);
    }
}
