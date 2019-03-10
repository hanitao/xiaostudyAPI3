package com.xiaostudy.test;

import java.util.ArrayList;
import java.util.List;

import com.xiaostudy.util.MathUtil;

/**
 * @desc 组合测试类
 * @author xiaostudy
 *
 */
public class Test_combination {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
//		test();
//		test2();
        test3();
    }

    //
    public static void test() {
//        String[] strings = {"12", "24", "23", "35", "34", "46", "45", "08", "07", "19", "18", "30", "29", "40", "02", "01", "13"};
        String[] strings = {"1", "2", "3"};
        List<List<String>> listList = MathUtil.combinationTsToInteger(strings, 2);

        System.out.println(listList);
        System.out.println(listList.size());
//        com.xiaostudy.util.ExcelUtil.createExcelFile("E:\\test.xlsx", listList);
    }

    public static void test2() {
        List<String> listStr = new ArrayList<String>();
        listStr.add("1");
        listStr.add("2");
        listStr.add("3");
//		listStr.add("4");

        List<List<String>> list = MathUtil.arrangeT(listStr);
        System.out.println("list: " + list);
        System.out.println("list.size(): " + list.size());
    }

    public static void test3() {
        String[] strings = {"1", "2", "3"};
        List<List<String>> list = MathUtil.arrangeTsToInteger(strings, 2);
        System.out.println(list.size());
        System.out.println(list);
    }

}
