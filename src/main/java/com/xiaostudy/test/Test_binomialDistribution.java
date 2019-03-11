package com.xiaostudy.test;

import com.xiaostudy.util.MathUtil;

import java.math.BigInteger;

public class Test_binomialDistribution {

    public static void main(String[] agrs) {
        System.out.println(MathUtil.binomialDistribution(3, 1, (double)1/3));
        System.out.println(MathUtil.binomialDistribution(3, 1, (double)1/4));
        System.out.println(MathUtil.binomialDistribution(10, 3, (double)1/3));
        System.out.println((double)1/3 == 0.333333333333333333333);
        System.out.println(0.333333333333333333333*3);
        System.out.println((long) (1/0.0000000000000000001));
    }
}
