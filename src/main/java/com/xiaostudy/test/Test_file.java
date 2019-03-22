package com.xiaostudy.test;

import com.xiaostudy.util.FileUtil;

import java.io.File;
import java.util.List;

public class Test_file {

    public static void main(String[] agrs) {
        /*List<String> fileNames = FileUtil.getFileNames(new File("E:\\个人文件\\笔记\\"));
        System.out.println(fileNames);*/

        List<List<String>> repetitionFiles = FileUtil.getRepetitionFiles(new File("E:\\个人文件\\视频"));
        for(int i = 0; i < repetitionFiles.size(); i++) {
            System.out.println(repetitionFiles.get(i));
        }
    }
}
