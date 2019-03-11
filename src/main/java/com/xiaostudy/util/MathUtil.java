package com.xiaostudy.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

/**
 * @author xiaostudy
 * @desc 数学工具类
 */
public final class MathUtil {

    private static Logger logger = Logger.getLogger(MathUtil.class);
    private static String CLASSNAME = MathUtil.class.getName();

    //私有构造函数，不让new对象
    private MathUtil() {
    }

    /**
     * @param n
     * @param m
     * @return
     * @desc 组合
     * <p>公式：C(n,m)=A(n,m)/m!=n(n-1)...(n-m+1)/m(m-1)...1=n!/((n-m)!*m!) </p>
     * <p style="color:red">例子：Integer n = 3; Integer m = 2; 结果：3</p>
     */
    public final static Integer combinationCnm(Integer n, Integer m) {
        logger.debug(">>>>>" + CLASSNAME + ".combination().....");
        logger.debug("n:" + n);
        logger.debug("m:" + m);
        int count = 0;
        try {
            if (n == null || m == null) {
                return null;
            }

            if (n < 0 || m < 0) {
                return -1;
            }

            if (n >= m) {
                if (m * 2 > n) {
                    m = n - m;
                }
                count = arrangeAnm(n, m) / factorial(m);
            } else {
                if (n * 2 > m) {
                    n = m - n;
                }
                count = arrangeAnm(m, n) / factorial(n);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            logger.debug("<<<<<" + CLASSNAME + ".combination().");
            return count;
        }

    }

    /**
     * @param n
     * @param m
     * @return
     * @desc 排列
     * <p>公式：A(n,m)=n(n-1)...(n-m+1)=n!/(n-m)! </p>
     * <p style="color:red">例子：Integer n = 3; Integer m = 2; 结果：6</p>
     */
    public static Integer arrangeAnm(Integer n, Integer m) {
        logger.debug(">>>>>" + CLASSNAME + ".arrangeAnm().....");
        logger.debug("n:" + n);
        logger.debug("m:" + m);
        int count = 1;
        try {
            if (n == null || m == null) {
                return null;
            }

            if (n < 0 || m < 0) {
                return -1;
            }

            if (n == 0 || m == 0) {
                return 1;
            }

            if (n < m) {
                Integer integer = n;
                n = m;
                m = integer;
            }

            for (int i = n, j = 1; j <= m; i--, j++) {
                count = count * i;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            logger.debug("<<<<<" + CLASSNAME + ".arrangeAnm().");
            return count;
        }

    }

    /**
     * @param integer
     * @return
     * @desc 阶乘
     * <p>公式：n!=n*(n-1)...1 </p>
     * <p style="color:red">例子：Integer integer = 3; 结果：6</p>
     */
    public static Integer factorial(Integer integer) {
        logger.debug(">>>>>" + CLASSNAME + ".factorial().....");
        logger.debug("integer:" + integer);
        if (integer == null) {
            return null;
        }

        if (integer != null && integer < 0) {
            return -1;
        }

        if (integer == 0 || integer == 1) {
            return 1;
        }
        logger.debug("<<<<<" + CLASSNAME + ".factorial().");
        return integer * factorial(integer - 1);

    }

    /**
     * @param ts
     * @param integer
     * @return
     * @desc 通过一数组，以一定组合
     * <p style="color:red">例子：String[] ts = {"1", "2", "3"}; Integer integer = 2; 结果：[[1, 2], [1, 3], [2, 3]]</p>
     */
    public static <T> List<List<T>> combinationTsToInteger(T[] ts, Integer integer) {
        logger.debug(">>>>>" + CLASSNAME + ".combinationStringsToInteger().....");
        logger.debug("strings:" + ts);
        logger.debug("integer:" + integer);
        List<List<T>> listList = new ArrayList<List<T>>();
        try {
            if (ts == null || integer == null) {
                return null;
            }

            if (ts.length < integer) {
                return null;
            }

            List<Integer> lag = new ArrayList<Integer>();
            for (int i = 0; i < integer; i++) {
                lag.add(i);
            }

            for (int count = 0; count < combinationCnm(ts.length, integer); count++) {
                List<T> list = new ArrayList<T>();
                for (int i = 0; i < lag.size(); i++) {// 添加到组合
                    list.add(ts[lag.get(i)]);
                }
                listList.add(list);// 添加到结果里

                if (lag.get(0) == ts.length - lag.size()) {// 判断所有下标是否到了结尾，如果全部到了结尾则退出循环
                    break;
                }

                for (int i = lag.size() - 1; i >= 0; i--) {// 循环下标，往后加1
                    if (lag.get(i) == ts.length - (lag.size() - i)) {// 如果某个下标已经到了结尾，则跳出
                        if (i == 0) {// 判断所有下标是否到了结尾，如果全部到了结尾则退出循环
                            break;
                        }
                        continue;
                    }

                    if (lag.size() - i == 1) {// 最后面下标加1
                        lag.set(lag.size() - 1, lag.get(lag.size() - 1) + 1);
                    } else {
                        lag.set(i, lag.get(i) + 1);
                        for (int j = 1; j < lag.size() - i; j++) {// 后面下标都加1
                            lag.set(i + j, lag.get(i + j - 1) + 1);
                        }
                    }
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            logger.debug("<<<<<" + CLASSNAME + ".combinationStringsToInteger().");
            return listList;
        }
    }

    /**
     * @param str
     * @param regex
     * @param integer
     * @return
     * @desc 通过一串字符串以特定分割得到字符串数组，以一定数字组合
     * <p style="color:red">例子：String str = "1,2,3"; String regex = ","; Integer integer = 2; 结果：[[1, 2], [1, 3], [2, 3]]</p>
     * <p style="color:red">与使用方法combinationTsToInteger，参数为String[] ts = {"1", "2", "3"}; Integer integer = 2一样</p>
     */
    public static List<List<String>> combinationStringBySplitToInteger(String str, String regex, Integer integer) {
        logger.debug(">>>>>" + CLASSNAME + ".combinationStringBySplitToInteger().....");
        logger.debug("str:" + str);
        logger.debug("regex:" + regex);
        logger.debug("integer:" + integer);
        String[] strings = null;
        try {
            if (str == null || regex == null || integer == null) {
                return null;
            }

            strings = str.split(regex);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            logger.debug("<<<<<" + CLASSNAME + ".combinationStringBySplitToInteger().");
            return combinationTsToInteger(strings, integer);
        }
    }

    /**
     * @param ts
     * @param integer
     * @param filename
     * @return
     * @desc 通过一数组，以一定组合，并写入Excel
     */
    public static <T> Boolean combinationTsToIntegerWriteFilename(T[] ts, Integer integer, String filename) {
        logger.debug(">>>>>" + CLASSNAME + ".combinationStringsToIntegerWriteFilename().....");
        logger.debug("strings:" + ts);
        logger.debug("integer:" + integer);
        logger.debug("filename:" + filename);
        String createExcelFile = "";
        try {
            if (ts == null || integer == null || filename == null) {
                return null;
            }

            List<List<T>> list = combinationTsToInteger(ts, integer);
            if (list == null) {
                return false;
            }

            createExcelFile = ExcelUtil.createExcelFile(filename, list);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            logger.debug("<<<<<" + CLASSNAME + ".combinationStringsToIntegerWriteFilename().");
            return new File(createExcelFile).exists();
        }
    }

    /**
     * @param str
     * @param regex
     * @param integer
     * @param filename
     * @return
     * @desc 通过一串字符串以特定分割得到字符串数组，以一定数字组合，并写入Excel
     */
    public static Boolean combinationStringBySplitToIntegerWriteFilename(String str, String regex, Integer integer,
                                                                         String filename) {
        List<List<String>> list = combinationStringBySplitToInteger(str, regex, integer);
        if (list == null || list.size() <= 0) {
            return false;
        }
        String createExcelFile = ExcelUtil.createExcelFile(filename, list);
        return new File(createExcelFile).exists();
    }

    /**
     * @param listT
     * @return
     * @desc 给定集合元素进行排列
     * <p style="color:red">例子：List&lt;T&gt; listT = new ArrayList<String>(); listStr.add("1");listStr.add("2");listStr.add("3"); 结果：[[1, 2, 3], [2, 1, 3], [2, 3, 1], [1, 3, 2], [3, 1, 2], [3, 2, 1]]</p>
     */
    public static <T> List<List<T>> arrangeT(List<T> listT) {
        List<List<T>> list = new ArrayList<List<T>>();

        if (listT == null) {
            return null;
        }

        if (listT.size() <= 0) {//如果为空集合，则返回空集合
            return list;
        }

        if (listT.size() == 1) {//如果集合只有一个元素，那么添加该元素后返回
            list.add(listT);
            return list;
        }

        for (int i = 0; i < listT.size(); i++) {//循环
            T t = listT.get(i);//取出第i索引位置元素
            listT.remove(i);//取出某元素后，从集合中删除该元素
            List<List<T>> list2 = arrangeT(listT);//剩下的元素集合利用递归调用
            for (int j = 0; j < list2.size(); j++) {//遍历删除后递归调用的返回结果
                List<T> listT2 = list2.get(j);//取出第j索引位置的元素
                for (int k = 0; k <= listT2.size(); k++) {//遍历第j索引位置的元素，然后一个个插入第i索引位置元素
                    List<T> listT3 = new ArrayList<T>();
                    listT3.addAll(listT2);
                    if (k == listT2.size()) {//如果到了结尾，那么使用的是添加，而不是插入
                        listT3.add(t);
                        list.add(listT3);
                        break;//到了结尾，那么就跳出遍历第j索引位置元素的遍历，进行第j+1索引位置元素的遍历
                    } else {
                        listT3.add(k, t);
                    }
                    list.add(listT3);
                }
            }
        }
        return list;
    }

    /**
     * @param ts
     * @param integer
     * @return
     * @desc 通过一数组，以一定排列组合
     * <p style="color:red">例子：String[] strings = {"1", "2", "3"};Integer integer = 2;  结果：[[1, 2], [2, 1], [1, 3], [3, 1], [2, 3], [3, 2]]</p>
     */
    public static <T> List<List<T>> arrangeTsToInteger(T[] ts, Integer integer) {
        logger.debug(">>>>>" + CLASSNAME + ".arrangeStringsToInteger().....");
        logger.debug("strings:" + ts);
        logger.debug("integer:" + integer);
        List<List<T>> listList = new ArrayList<List<T>>();
        try {
            if (ts == null || integer == null) {
                return null;
            }

            if (ts.length < integer) {
                return null;
            }

            List<Integer> lag = new ArrayList<Integer>();
            for (int i = 0; i < integer; i++) {
                lag.add(i);
            }

            for (int count = 0; count < combinationCnm(ts.length, integer); count++) {
                List<T> list = new ArrayList<T>();
                for (int i = 0; i < lag.size(); i++) {// 添加到组合
                    list.add(ts[lag.get(i)]);
                }
//				listList.add(list);// 添加到结果里
                listList.addAll(arrangeT(list));

                if (lag.get(0) == ts.length - lag.size()) {// 判断所有下标是否到了结尾，如果全部到了结尾则退出循环
                    break;
                }

                for (int i = lag.size() - 1; i >= 0; i--) {// 循环下标，往后加1
                    if (lag.get(i) == ts.length - (lag.size() - i)) {// 如果某个下标已经到了结尾，则跳出
                        if (i == 0) {// 判断所有下标是否到了结尾，如果全部到了结尾则退出循环
                            break;
                        }
                        continue;
                    }

                    if (lag.size() - i == 1) {// 最后面下标加1
                        lag.set(lag.size() - 1, lag.get(lag.size() - 1) + 1);
                    } else {
                        lag.set(i, lag.get(i) + 1);
                        for (int j = 1; j < lag.size() - i; j++) {// 后面下标都加1
                            lag.set(i + j, lag.get(i + j - 1) + 1);
                        }
                    }
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            logger.debug("<<<<<" + CLASSNAME + ".arrangeStringsToInteger().");
            return listList;
        }
    }

    /**
     * @param ts
     * @param integer
     * @param filename
     * @return
     * @desc 通过一数组，以一定排列组合，并写入文件
     */
    public static <T> Boolean arrangeTsToIntegerWriteFilename(T[] ts, Integer integer, String filename) {
        List<List<T>> list = arrangeTsToInteger(ts, integer);
        if (list == null || list.size() <= 0) {
            return false;
        }

        String createExcelFile = ExcelUtil.createExcelFile(filename, list);
        return new File(createExcelFile).exists();
    }

    /**
     * @desc 一元二次方程解
     * <p>公式：aX平方+bX+c=0</p>
     * <p style="color:red">只有实数解，没有复数解</p>
     * @param a
     * @param b
     * @param c
     * @return
     */
    public static List oneYuanQuadraticEquations(Double a, Double b, Double c) {
        if (a == null || b == null || c == null) {
            return null;
        }
        List list = new ArrayList();

        if(a == 0) {
            if(b == 0) {//无解
                if(c == 0) {
                    list.add(0);
                }
            } else {
                if((-c)%b == 0) {
                    list.add((-c) / b);
                } else {
                    list.add((-(double)c) / b);
                }
            }
            return list;
        }

        Double b2_4ac = b * b - 4 * a * c;

        if (b2_4ac < 0) {//没有实数解
            return list;
        } else if (b2_4ac == 0) {//只有一个解【有两个相同的解】
            list.add((-b) / (2 * a) + (-b) % (2 * a));
            return list;
        }
        //有两个不同的解
        double sqrt = Math.sqrt(b2_4ac);
        if (sqrt - ((int) sqrt) != 0) {
            list.add((-b + sqrt) / (2 * a));
            list.add((-b - sqrt) / (2 * a));
            list.add("(" + (-b) + "+根号" + b2_4ac + ")/" + (2 * a));
            list.add("(" + (-b) + "-根号" + b2_4ac + ")/" + (2 * a));
        } else {
            list.add((-b + (int)sqrt) / (2 * a));
            list.add((-b - (int)sqrt) / (2 * a));
        }
        return list;
    }

    /**
     * @desc 一元二次方程解
     * <p>公式：aX平方+bX+c=0</p>
     * <p style="color:red">复数解</p>
     * @param a
     * @param b
     * @param c
     * @return
     */
    public static List oneYuanQuadraticEquationsI(Double a, Double b, Double c) {
        if (a == null || b == null || c == null) {
            return null;
        }
        List list = new ArrayList();

        if(a == 0) {
            return oneYuanQuadraticEquations(a, b, c);
        }

        Double b2_4ac = b * b - 4 * a * c;

        if (b2_4ac >= 0) {
            return oneYuanQuadraticEquations(a, b, c);
        }

        double sqrt = Math.sqrt(-b2_4ac);

        if (sqrt - ((int) sqrt) != 0) {
            list.add("(" + (-b) + "+" + sqrt + "i)/" + (2 * a));
            list.add("(" + (-b) + "-" + sqrt + "i)/" + (2 * a));
            list.add("(" + (-b) + "+根号" + (-b2_4ac) + "i)/" + (2 * a));
            list.add("(" + (-b) + "-根号" + (-b2_4ac) + "i)/" + (2 * a));
        } else {
            list.add("(" + (-b) + "+" + (int)sqrt + "i)/" + (2 * a));
            list.add("(" + (-b) + "-" + (int)sqrt + "i)/" + (2 * a));
        }
        return list;
    }

    /**
     * @desc 一元二次方程解
     * <p>公式：aX平方+bX+c=0</p>
     * <p style="color:red">只有实数解，没有复数解</p>
     * @param a
     * @param b
     * @param c
     * @return
     */
    public static List oneYuanQuadraticEquations(Integer a, Integer b, Integer c) {
        if (a == null || b == null || c == null) {
            return null;
        }

        return oneYuanQuadraticEquations((double)a, (double)b, (double)c);
    }

    /**
     * @desc 一元二次方程解
     * <p>公式：aX平方+bX+c=0</p>
     * <p style="color:red">复数解</p>
     * @param a
     * @param b
     * @param c
     * @return
     */
    public static List oneYuanQuadraticEquationsI(Integer a, Integer b, Integer c) {
        if (a == null || b == null || c == null) {
            return null;
        }

        return oneYuanQuadraticEquationsI((double)a, (double)b, (double)c);
    }

    /**
     * @desc 等差数列
     * <p>公式：Sn=na1+(n*(n-1))/2*d</p>
     * @param a1 第一项
     * @param n 共n项
     * @param d 步长
     * @return
     */
    public static Object arithmeticSequence(Double a1, Integer n, Double d) {
        if(a1 == null || n == null || d == null) {
            return null;
        }

        if(n == 0 || d == 0) {
            return 0;
        }

        double s = n * a1 + ((double)(n * (n-1))/2) * d;
        if(s - (int)(double)s == 0) {
            return (int)s;
        }
        return s;
    }

    /**
     * @desc 等差数列
     * <p>公式：Sn=na1+(n*(n-1))/2*d</p>
     * @param a1 第一项
     * @param n 共n项
     * @param d 步长
     * @return
     */
    public static Object arithmeticSequence(Integer a1, Integer n, Double d) {
        return arithmeticSequence((double)a1, n, d);
    }

    /**
     * @desc 等差数列
     * <p>公式：Sn=na1+(n*(n-1))/2*d</p>
     * @param a1 第一项
     * @param n 共n项
     * @param d 步长
     * @return
     */
    public static Object arithmeticSequence(Double a1, Integer n, Integer d) {
        return arithmeticSequence(a1, n, (double)d);
    }

    /**
     * @desc 等差数列
     * <p>公式：Sn=na1+(n*(n-1))/2*d</p>
     * @param a1 第一项
     * @param n 共n项
     * @param d 步长
     * @return
     */
    public static Object arithmeticSequence(Integer a1, Integer n, Integer d) {
        return arithmeticSequence((double)a1, n, (double)d);
    }

    /**
     * @desc 等比数列
     * <p>公式：Sn=n*a1  (q=1)
     *          Sn=a1*(1-q的n次方)/(1-q)=(a1-an*q)/(1-q)
     * </p>
     * @param a1
     * @param n
     * @param q
     * @return
     */
    public static Object geometricSequence(Double a1, Integer n, Double q) {
        if(a1 == null || n == null || q == null) {
            return null;
        }

        if(n == 0) {
            return 0;
        }

        if(q == 0) {
            if(a1 - (int)(double)a1 == 0) {
                return (int)(double)a1;
            }
            return a1;
        }

        if(q == 1) {
            if(a1*n - (int)(double)(a1*n) == 0) {
                return (int)(double)(a1*n);
            }
            return a1*n;
        }

        double s = a1*(1-Math.pow(q, n))/(1-q);

        if(s - (int)s == 0) {
            return (int)s;
        }

        return s;
    }

    /**
     * @desc 等比数列
     * <p>公式：Sn=n*a1  (q=1)
     *          Sn=a1*(1-q的n次方)/(1-q)=(a1-an*q)/(1-q)
     * </p>
     * @param a1
     * @param n
     * @param q
     * @return
     */
    public static Object geometricSequence(Integer a1, Integer n, Double q) {
        return geometricSequence((double)a1, n, q);
    }

    /**
     * @desc 等比数列
     * <p>公式：Sn=n*a1  (q=1)
     *          Sn=a1*(1-q的n次方)/(1-q)=(a1-an*q)/(1-q)
     * </p>
     * @param a1
     * @param n
     * @param q
     * @return
     */
    public static Object geometricSequence(Double a1, Integer n, Integer q) {
        return geometricSequence(a1, n, (double)q);
    }

    /**
     * @desc 等比数列
     * <p>公式：Sn=n*a1  (q=1)
     *          Sn=a1*(1-q的n次方)/(1-q)=(a1-an*q)/(1-q)
     * </p>
     * @param a1
     * @param n
     * @param q
     * @return
     */
    public static Object geometricSequence(Integer a1, Integer n, Integer q) {
        return geometricSequence((double)a1, n, (double)q);
    }

    /**
     * @desc 海伦公式
     * <p>公式：S=开方(p*(p-a)*(p-b)*(p-c))   其中p=(a+b+c)/2</p>
     * @param a
     * @param b
     * @param c
     * @return
     */
    public static Object helenFormula(Double a, Double b, Double c){
        if(a == null || b == null || c == null) {
            return null;
        }

        if(triangle(a, b, c) == false) {
            return null;
        }

        if(a+b==c || a+c==b || b+c==a) {
            return 0;
        }

        double p=(a+b+c)/2;
        double q = p*(p-a)*(p-b)*(p-c);

        double s = Math.sqrt(q);
        if(s - (int)s == 0) {
            return (int)s;
        }

        List list = new ArrayList();
        list.add(s);
        list.add("根号(" + q + ")");

        return list;
    }

    /**
     * @desc 海伦公式
     * <p>公式：S=开方(p*(p-a)*(p-b)*(p-c))   其中p=(a+b+c)/2</p>
     * @param a
     * @param b
     * @param c
     * @return
     */
    public static Object helenFormula(Integer a, Integer b, Integer c) {
        if (a == null || b == null || c == null) {
            return null;
        }

        return helenFormula((double)a, (double)b, (double)c);
    }

    /**
     * @desc 判断是否为三角形
     * @param a
     * @param b
     * @param c
     * @return
     */
    public static Boolean triangle(Double a, Double b, Double c) {
        if(a == null || b == null || c == null) {
            return null;
        }

        if(a+b<c) {
            return false;
        }

        if(a+c<b) {
            return false;
        }

        if(b+c<a) {
            return false;
        }
        return true;
    }

    /**
     * @desc 判断是否为三角形
     * @param a
     * @param b
     * @param c
     * @return
     */
    public static Boolean triangle(Integer a, Integer b, Integer c) {
        if(a == null || b == null || c == null) {
            return null;
        }

        return triangle((double)a, (double)b, (double)c);
    }

    /**
     * @desc 二项分布
     * <p>问题：事件发生概率为P，n次独立重复试验，发生k次的概率为？</p>
     * <p>公式：P(ξ=K)=Cn(k)P(k)Q(n-k) 其中K为发生次数，P为发生概率，Q为不发生概率</p>
     * @param n
     * @param k
     * @param p
     * @return
     */
    public static Object binomialDistribution(Integer n, Integer k,Double p) {
        if(n == null || k == null || p == null) {
            return null;
        }

        if(k > n || n <= 0 || k <= 0 || p <= 0 || p > 1) {
            return 0;
        }

        if(p == 1) {
            return 1;
        }

        double s = combinationCnm(n, k) * Math.pow(p, k) * Math.pow(1-p,n-k);

        if(s - (int)s == 0) {
            return (int)s;
        }

        List list = new ArrayList();
        list.add(s);
        list.add(s*100 + "%");
        list.add(getFraction(s));

        return list;
    }

    /**
     * 最大公约数
     * @param a
     * @param b
     * @return
     */
    public static Long gcd(Long a, Long b) { // 用辗转相除法求最大公约数
        if(a == null || b == null) {
            return null;
        }

        //其中一个大于等于或小于等于Long的最值就不找了
        if(a >= Long.MAX_VALUE || a <= Long.MIN_VALUE || b >= Long.MAX_VALUE || b <= Long.MIN_VALUE) {
            return 0L;
        }

        return b == 0 ? a : gcd(b,a%b);
    }

    /**
     * @desc 小数转分数
     * <p>说明：精度丢失时失效。如：double d = 1.0/3;</p>
     * @param d
     * @return
     */
    public static Object getFraction(Double d) {
        Boolean b = true;//默认为正数

        if(d < 0) {
            b = false;
            d = -d;
        }

        double decimal = d - (int)(double)d;//小数部分
        long integer = (long)(d - decimal);//整数部分


        if(decimal == 0) {
            return integer;
        }

        int l = new Double(decimal).toString().length();
        long denominator = (long) Math.pow(10, l-2);//分子
        long molecular = new Long(new Double(decimal).toString().substring(2,l));//分母

        Long gcd = gcd(denominator, molecular);//最大公约数

        if(gcd == null || gcd == 0) {
            return null;
        }

        if(gcd != 1) {
            denominator = denominator/gcd;//约分
            molecular = molecular/gcd;//约分
        }

        molecular = molecular + denominator*integer;//假分数的分子

        if(new Long(molecular) >= Long.MAX_VALUE) {//如果假分数的分子大于等于Long的最大值就返回null
            return null;
        }

        if(b == false) {
            molecular = -molecular;
        }

        if(denominator == 1) {
            return molecular;
        }

        return molecular + "/" + denominator;
    }

}
