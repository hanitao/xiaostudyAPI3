package com.xiaostudy.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

/**
 * @desc 数学工具类
 * @author xiaostudy
 *
 */
public class MathUtil {

	private static Logger logger = Logger.getLogger(MathUtil.class);
	private static String CLASSNAME = MathUtil.class.getName();

	/**
	 * @desc 组合
	 * <p>公式：C(n,m)=A(n,m)/m!=n(n-1)...(n-m+1)/m(m-1)...1=n!/((n-m)!*m!) </p>
	 * <p style="color:red">例子：Integer n = 3; Integer m = 2; 结果：3</p>
	 * @param n
	 * @param m
	 * @return
	 */
	public static Integer combinationCnm(Integer n, Integer m) {
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
				count =  arrangeAnm(n, m) / factorial(m);
			} else {
				if (n * 2 > m) {
					n = m - n;
				}
				count =  arrangeAnm(m, n) / factorial(n);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			logger.debug("<<<<<" + CLASSNAME + ".combination().");
			return count;
		}

	}

	/**
	 * @desc 排列
	 * <p>公式：A(n,m)=n(n-1)...(n-m+1)=n!/(n-m)! </p>
	 * <p style="color:red">例子：Integer n = 3; Integer m = 2; 结果：6</p>
	 * @param n
	 * @param m
	 * @return
	 */
	public static Integer arrangeAnm(Integer n, Integer m) {
		logger.debug(">>>>>" + CLASSNAME + ".arrangeAnm().....");
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
	 * @desc 阶乘
	 * <p>公式：n!=n*(n-1)...1 </p>
	 * <p style="color:red">例子：Integer integer = 3; 结果：6</p>
	 * @param integer
	 * @return
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
	 * @desc 通过一数组，以一定组合
	 * <p style="color:red">例子：String[] ts = {"1", "2", "3"}; Integer integer = 2; 结果：[[1, 2], [1, 3], [2, 3]]</p>
	 * @param ts
	 * @param integer
	 * @return
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

				if (lag.get(0) == ts.length-lag.size()) {// 判断所有下标是否到了结尾，如果全部到了结尾则退出循环
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
	 * @desc 通过一串字符串以特定分割得到字符串数组，以一定数字组合
	 * <p style="color:red">例子：String str = "1,2,3"; String regex = ","; Integer integer = 2; 结果：[[1, 2], [1, 3], [2, 3]]</p>
	 * <p style="color:red">与使用方法combinationTsToInteger，参数为String[] ts = {"1", "2", "3"}; Integer integer = 2一样</p>
	 * @param str
	 * @param regex
	 * @param integer
	 * @return
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
	 * @desc 通过一数组，以一定组合，并写入Excel
	 * @param ts
	 * @param integer
	 * @param filename
	 * @return
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
	 * @desc 通过一串字符串以特定分割得到字符串数组，以一定数字组合，并写入Excel
	 * @param str
	 * @param regex
	 * @param integer
	 * @param filename
	 * @return
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
	 * @desc 给定集合元素进行排列
	 * <p style="color:red">例子：List&lt;T&gt; listT = new ArrayList<String>(); listStr.add("1");listStr.add("2");listStr.add("3"); 结果：[[1, 2, 3], [2, 1, 3], [2, 3, 1], [1, 3, 2], [3, 1, 2], [3, 2, 1]]</p>
	 * @param listT
	 * @return
	 */
	public static <T> List<List<T>> arrangeT(List<T> listT) {
		List<List<T>> list = new ArrayList<List<T>>();

		if(listT == null) {
			return null;
		}

		if(listT.size() <= 0) {//如果为空集合，则返回空集合
			return list;
		}

		if(listT.size() == 1) {//如果集合只有一个元素，那么添加该元素后返回
			list.add(listT);
			return list;
		}

		for(int i = 0; i < listT.size(); i++) {//循环
			T t = listT.get(i);//取出第i索引位置元素
			listT.remove(i);//取出某元素后，从集合中删除该元素
			List<List<T>> list2 = arrangeT(listT);//剩下的元素集合利用递归调用
			for(int j = 0; j < list2.size(); j++) {//遍历删除后递归调用的返回结果
				List<T> listT2 = list2.get(j);//取出第j索引位置的元素
				for(int k = 0; k <= listT2.size(); k++) {//遍历第j索引位置的元素，然后一个个插入第i索引位置元素
					List<T> listT3 = new ArrayList<T>();
					listT3.addAll(listT2);
					if(k == listT2.size()) {//如果到了结尾，那么使用的是添加，而不是插入
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
	 * @desc 通过一数组，以一定排列组合
	 * <p style="color:red">例子：String[] strings = {"1", "2", "3"};Integer integer = 2;  结果：[[1, 2], [2, 1], [1, 3], [3, 1], [2, 3], [3, 2]]</p>
	 * @param ts
	 * @param integer
	 * @return
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

				if (lag.get(0) == ts.length-lag.size()) {// 判断所有下标是否到了结尾，如果全部到了结尾则退出循环
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
	 * @desc 通过一数组，以一定排列组合，并写入文件
	 * @param ts
	 * @param integer
	 * @param filename
	 * @return
	 */
	public static <T> Boolean arrangeTsToIntegerWriteFilename(T[] ts, Integer integer, String filename) {
		List<List<T>> list = arrangeTsToInteger(ts, integer);
		if (list == null || list.size() <= 0) {
			return false;
		}

		String createExcelFile = ExcelUtil.createExcelFile(filename, list);
		return new File(createExcelFile).exists();
	}

}
