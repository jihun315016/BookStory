package com.book_story.service;

import com.book_story.models.dto.ItemListDTO;
import com.google.gson.Gson;
import org.springframework.stereotype.Service;

import java.net.URL;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

@Service
public class HomeService {
    private static final String URL = "url";
    private static final String METHOD = "GET";
    private static final String USER_AGENT = "Mozilla/5.0";

    public void findData() throws IOException {
        // URL 연결 객체 가져오기
        URL url = new URL(URL);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        // 요청 메서드, 헤더 설정
        connection.setRequestMethod(METHOD);
        connection.setRequestProperty("User-Agent", USER_AGENT);

        // 응답 코드 가져오기, 성공 시 200 반환
        int responseCode = connection.getResponseCode();

        // 응답 데이터를 읽을 수 있는 InputStream 객체 가져오기
        InputStream InputStream = connection.getInputStream();
        InputStreamReader inputStreamReader = new InputStreamReader(InputStream, "UTF-8");
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        String inputLine = bufferedReader.readLine();

        Gson gson = new Gson();
        ItemListDTO itemListDTO = gson.fromJson(inputLine, ItemListDTO.class);
        System.out.println(itemListDTO);
        System.out.println(itemListDTO.getItem());
    }
}
