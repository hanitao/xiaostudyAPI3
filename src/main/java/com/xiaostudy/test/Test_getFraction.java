package com.xiaostudy.test;

import com.xiaostudy.util.MathUtil;

import java.math.BigInteger;

public class Test_getFraction {
    public static void main(String[] agrs) {
        System.out.println(MathUtil.getFraction(0.25));
        System.out.println(MathUtil.getFraction(0.255555));
        System.out.println(MathUtil.getFraction(100/0.5));
        System.out.println(MathUtil.getFraction(10.0));
        System.out.println(MathUtil.getFraction(1.0/3));
        System.out.println(MathUtil.getFraction(0.0));
        System.out.println(MathUtil.getFraction(-2.25));
        System.out.println(Math.sqrt(2));
        System.out.println(MathUtil.getFraction(Math.sqrt(2)));
    }
}
