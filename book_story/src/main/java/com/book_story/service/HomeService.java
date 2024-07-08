package com.book_story.service;

import com.book_story.models.dto.aladin.ItemListCondition;
import com.book_story.models.entity.CommonCode;
import com.book_story.repository.CommonCodeRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.cache.spi.support.AbstractReadWriteAccess;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HomeService {
    private final CommonCodeRepository commonCodeRepository;
    private static final String URL = "url";
    private static final String METHOD = "GET";
    private static final String USER_AGENT = "Mozilla/5.0";


    public void findData() throws IOException {
        List<CommonCode> commonCodeList = commonCodeRepository.findByCodeGroup("Aladin-ItemList");

        BiFunction<List<CommonCode>, String, String> getNameLambda = (list, str) -> {
            return list.stream()
                    .filter(l -> l.getCode().equals(str))
                    .map(c -> (CommonCode) c)
                    .findFirst().get().getName();
        };

        ItemListCondition condition = ItemListCondition.builder()
                .queryType(getNameLambda.apply(commonCodeList, "QueryType"))
                .maxResult(Integer.parseInt(getNameLambda.apply(commonCodeList, "MaxResults")))
                .searchTarget(getNameLambda.apply(commonCodeList, "SearchTarget"))
                .output(getNameLambda.apply(commonCodeList, "output"))
                .version(getNameLambda.apply(commonCodeList, "Version"))
                .cover(getNameLambda.apply(commonCodeList, "Cover"))
                .build();

        // ItemListCondition(ttbkey=null, queryType=ItemNewAll, cover=Big, maxResult=6, start=0, searchTarget=Book, output=js, version=20131101)
        System.out.println(condition);


//        // URL 연결 객체 가져오기
//        URL url = new URL(URL);
//        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//
//        // 요청 메서드, 헤더 설정
//        connection.setRequestMethod(METHOD);
//        connection.setRequestProperty("User-Agent", USER_AGENT);
//
//        // 응답 코드 가져오기, 성공 시 200 반환
//        int responseCode = connection.getResponseCode();
//
//        // 응답 데이터를 읽을 수 있는 InputStream 객체 가져오기
//        InputStream InputStream = connection.getInputStream();
//        InputStreamReader inputStreamReader = new InputStreamReader(InputStream, "UTF-8");
//        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
//        String inputLine = bufferedReader.readLine();
//
//        Gson gson = new Gson();
//        ItemListDTO itemListDTO = gson.fromJson(inputLine, ItemListDTO.class);
//        return itemListDTO;
    }
}
