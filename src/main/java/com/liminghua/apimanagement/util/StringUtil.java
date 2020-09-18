package com.liminghua.apimanagement.util;

import java.util.List;

/**
 * @ClassName StringUtil
 * @Description: TODO
 * @Author LiMinghua
 * @Date 2020/9/9
 * @Version V1.0
 **/
public class StringUtil {

    public static String getListString(List<String> list) {
        StringBuilder result = new StringBuilder();
        for (String s : list) {
            result.append(s).append(" ");
        }
        return result.toString();
    }
}
