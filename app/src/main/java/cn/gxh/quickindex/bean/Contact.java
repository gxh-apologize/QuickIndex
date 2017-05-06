package cn.gxh.quickindex.bean;

import cn.gxh.quickindex.util.PinyinUtils;

/**
 * Created by GXH on 2017/5/6.
 */
public class Contact {
    public String name;

    /** 姓名首字母 */
    public String initLetter;

    /** 姓名拼音 */
    public String pinyin;

    public Contact(String name) {
        this.name = name;

        // 中国 -> zhongguo
        pinyin = PinyinUtils.getPinyin(name).toUpperCase();
        initLetter = pinyin.charAt(0) + "";
    }
}
