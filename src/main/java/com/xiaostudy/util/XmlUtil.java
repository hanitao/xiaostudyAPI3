package com.xiaostudy.util;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * XML工具类
 * @author xiaostudy
 * @date 2019.4.26
 * @version 1.0.0
 */
public class XmlUtil {

    public static void main(String[] args) {
        Boolean aBoolean = Xml2ExcelFile("C:\\Users\\Administrator\\Desktop\\test3.xml", "C:\\Users\\Administrator\\Desktop\\test3.xlsx");
        System.out.println(aBoolean);
    }

    /**
     * Xml转Excel文件
     * @param strXmlFilePath Xml文件路径
     * @param strExcleFilePath Excel文件存放路径
     * @return
     */
    public static Boolean Xml2ExcelFile(String strXmlFilePath, String strExcleFilePath) {
        if(StringUtil.isTrimNull(strXmlFilePath) || StringUtil.isTrimNull(strExcleFilePath)) {
            return false;
        }

        List<List<String>> listList = readXml(strXmlFilePath);
        if(null == listList || listList.isEmpty()) {
            return false;
        }

        return ExcelUtil.createExcelFile(strExcleFilePath, listList);
    }

    /**
     * 读取Xml文件，以List<List<String>>形式返回、适合sql导出的xml
     * @param strFile
     * @return
     */
    public static List<List<String>> readXml(String strFile) {
        if(StringUtil.isTrimNull(strFile)) {
            return null;
        }

        SAXReader reader = new SAXReader();
        File file = new File(strFile);
        if(!file.exists() || !file.isFile()) {
            return null;
        }

        Document document = null;
        try {
            document = reader.read(file);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        List<List<String>> listList = new ArrayList<>();

        if (null != document) {
            Element root = document.getRootElement();
            List<Element> childElements = root.elements();
            List<String> titleList = new ArrayList<>();
            listList.add(titleList);
            boolean flag = true;
            for (Element child : childElements) {
                List<String> list = new ArrayList<>();

                List<Element> elementList = child.elements();
                for (Element ele : elementList) {
                    if(flag) {
                        titleList.add(ele.getName());
                    }
                    list.add(ele.getText());
                }
                flag = false;

                listList.add(list);

                System.out.println();
            }
        }

        return listList;
    }
}