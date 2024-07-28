package com.book_story.common;

import io.micrometer.common.util.StringUtils;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Map;

import com.book_story.models.dto.aladin.RequestData;
import com.google.gson.Gson;

import java.util.Iterator;
import java.util.List;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

public class Utility {
    // 클래스 타입을 매개변수로 받아
    // "필드명:값" 형태의 Map 변수를 만들어 반환
    public static <T> Map<String, String> getMapByClass(T obj) {
        Map<String, String> map = new HashMap<>();
        Object value;

        for (Field field : obj.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            try {
                value = field.get(obj);
                map.put(field.getName(), value != null ? value.toString() : "");
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return map;
    }


    public static <T> T getRequest(RequestData requestData, Class<T> clazz) throws MalformedURLException, IOException {
        // URL 연결 객체 가져오기
        URL url = new URL(requestData.getUrl());
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        // 요청 메서드, 헤더 설정
        connection.setRequestMethod(requestData.getMethod());
        connection.setRequestProperty("User-Agent", requestData.getUserAgent());

        // 응답 코드 가져오기, 성공 시 200 반환
        int responseCode = connection.getResponseCode();

        // 응답 데이터를 읽을 수 있는 InputStream 객체 가져오기
        InputStream InputStream = connection.getInputStream();
        InputStreamReader inputStreamReader = new InputStreamReader(InputStream, "UTF-8");
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        String inputLine = bufferedReader.readLine();

        Gson gson = new Gson();

        T result = gson.fromJson(inputLine, clazz);
        return result;
    }


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
                strValue = URLEncoder.encode(strValue, StandardCharsets.UTF_8);
                list.add(strKey + "=" + spacingprocessing(strValue));
            }
        }

        return String.join("&", list);
    }


    // 문자열 띄어쓰기 검사
    // -> 공백은 %20으로 바꾸어 준다.
    private static String spacingprocessing(String str) {
        String[] strArray = str.split(" ");
        return String.join("%20", strArray);
    }
}
