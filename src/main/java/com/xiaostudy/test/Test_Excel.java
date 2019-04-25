package com.xiaostudy.test;

import com.xiaostudy.util.ExcelUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Test_Excel {

    public static void main(String[] agrs) {
        /*List<List<String>> listList = new ArrayList<List<String>>();
        String str1 = "DA-2018-DQ11-000";
        String str2 = "-0";
        for(int i = 600; i <= 1000; i++) {
            List<String> list = new ArrayList<>();
            list.add("" + i);
            list.add(str1 + i + str2 + i);
            listList.add(list);
        }
        System.out.println(listList);
        String excelFile = ExcelUtil.createExcelFile("E:/sj.xlsx", listList);
        System.out.println(excelFile);*/

        /*List<List<String>> excelContent = ExcelUtil.getExcelContent("E:\\工作文件\\任务\\4.22-4.26\\03_卷内批量导入慢的问题\\test.xlsx");
        List<List<String>> listList = new ArrayList<List<String>>();
        for(int i = 0; i < excelContent.size(); i++) {
            List<String> stringList = excelContent.get(i);
            if(stringList.size() < 2) {
                continue;
            }
            List<String> list = new ArrayList<>();
            list.add("" + i);
            list.add(stringList.get(0) + "-" + stringList.get(1));
            listList.add(list);
        }
        System.out.println(listList);
        String excelFile = ExcelUtil.createExcelFile("E:/sj.xlsx", listList);
        System.out.println(excelFile);*/

    }
}
