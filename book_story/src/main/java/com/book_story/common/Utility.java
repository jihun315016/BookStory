package com.book_story.common;

import io.micrometer.common.util.StringUtils;

import java.util.Map;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;

public class Utility {
    // GET 요청에서 url parameter 문자열 생성
    // -> 키1=값1&키2=값2 ...
    public static String getUrlParameterFormat(Map<String, String> map) {
        Iterator<String> keys = map.keySet().iterator();
        String strKey, strValue;
        List<String> list = new ArrayList<>();

        while (keys.hasNext()) {
            strKey = keys.next();
            strValue = map.get(strKey);
            if (!StringUtils.isBlank(strValue)) {
                list.add(strKey + "=" + strValue);
            }
        }

        return String.join("&", list);
    }
    
    
    // 문자열 띄어쓰기 검사
    // -> 공백은 %20으로 바꾸어 준다.
    public static String spacingprocessing(String str) {
        String[] strArray = str.split(" ");
        return String.join("%20", strArray);
    }
}
