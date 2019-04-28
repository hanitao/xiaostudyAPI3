package com.xiaostudy.util;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

/**
 * 获取汉字的拼音
 * @author zgf
 *
 */
public final class SpellUtil {

    private SpellUtil(){}

//    private static StringBuffer pybf;
    /**
     * 获取汉字串拼音首字母，英文字符不变
     *
     * @param chinese 汉字串
     * @return 汉语拼音首字母
     */
    public static String cn2FirstSpell(String chinese) {
        if(StringUtil.isTrimNull(chinese)) {
            return "";
        }

        StringBuffer pybf = new StringBuffer();
        char[] arr = chinese.toCharArray();

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > 128) {
                String s = cn2Spell(arr[i]);
                if(null == s || s.trim().length() <= 0) {
                    return "";
                } else {
                    pybf.append(s.charAt(0));
                }
            } else {
                pybf.append(arr[i]);
            }
        }

        return pybf.toString().replaceAll("\\W", "").trim();
    }

    /**
     * 获取汉字串拼音，英文字符不变
     *
     * @param chinese 汉字串
     * @return 汉语拼音
     */
    public static String cn2Spell(String chinese) {
        if(StringUtil.isTrimNull(chinese)) {
            return "";
        }

        StringBuffer pybf = new StringBuffer();
        char[] arr = chinese.toCharArray();

        for (int i = 0; i < arr.length; i++) {
            String s = cn2Spell(arr[i]);
            if(null == s || s.trim().length() <= 0) {
                return "";
            }
            pybf.append(s);
        }

        return pybf.toString();
    }

    /**
     * 获取汉字串拼音，首字母大写，英文字符不变
     *
     * @param chinese 汉字串
     * @return 汉语拼音
     */
    public static String cn2SpellFirstUpperCase(String chinese) {
        if(StringUtil.isTrimNull(chinese)) {
            return "";
        }

        StringBuffer pybf = new StringBuffer();
        char[] arr = chinese.toCharArray();

        for (int i = 0; i < arr.length; i++) {
            String s = cn2Spell(arr[i]);
            if(null == s || s.trim().length() <= 0) {
                return "";
            }
            pybf.append(StringUtil.toUpperCaseFirstOne(s));
        }

        return pybf.toString();
    }

    public static String cn2Spell(char c) {
        StringBuffer pybf = new StringBuffer();

        HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
        defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);

        if (c > 128) {//是汉字
            try {
                String[] strings = PinyinHelper.toHanyuPinyinStringArray(c, defaultFormat);
                if(null != strings && strings.length > 0) {
                    pybf.append(strings[0]);
                } else {
                    return "";
                }
            } catch (BadHanyuPinyinOutputFormatCombination e) {
                e.printStackTrace();
            }
        } else {//非汉字
            pybf.append(c);
        }

        return pybf.toString();
    }

    public static void main(String[] agrs) {
        String s = cn2Spell("学生");
        System.out.println(s);
        s = cn2FirstSpell("教师");
        System.out.println(s);
        s = cn2SpellFirstUpperCase("教师");
        System.out.println(s);
    }

}