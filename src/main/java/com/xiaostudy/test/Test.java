package com.xiaostudy.test;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Test {

    static int a;

    public static void main(String[] args) {
        /*try {
            Test2 test2 = new Test2(2, "test");
            Method name = convertSetter(Test2.class, "id");
            System.out.println(name.invoke(test2));
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }*/
        /*try {
            Method method = Test2.class.getMethod("get" + "id".substring(0, 1).toUpperCase() + "id".substring(1));
            System.out.println(method);
            if(null != method) {
                Object invoke = method.invoke(test2);
                System.out.println(invoke);
            }
        } catch (NoSuchMethodException e) {
            System.out.println("没有该属性的get方法");
        } catch (IllegalAccessException e) {
            System.out.println("该属性的get方法为private");
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }*/
        List list = new ArrayList();
        int size = list.size();
        System.out.println(size);

        System.out.println(a);
    }

    public static Method convertSetter(Class cla, String field) {
        String method_set = "get" + field.substring(0, 1).toUpperCase() + field.substring(1);
        Method method1 = null;
        try {
            method1 = cla.getMethod(method_set);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return method1;
    }
}

class Test2 {
    private int id;
    private String name;

    public Test2(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
