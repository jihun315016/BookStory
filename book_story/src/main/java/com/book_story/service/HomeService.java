package com.book_story.service;

import com.book_story.models.dto.aladin.ItemListCondition;
import com.book_story.models.dto.aladin.ItemListDTO;
import com.book_story.models.dto.aladin.RequestData;
import com.book_story.models.entity.CommonCode;
import com.book_story.repository.CommonCodeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;

import com.book_story.common.Utility;

@Service
@RequiredArgsConstructor
public class HomeService {
    private final CommonCodeRepository commonCodeRepository;

    @Value("${aladin.apikey}")
    private String apiKey;

    public List<ItemListDTO> findData() throws IOException {
        List<CommonCode> commonCodeList = commonCodeRepository.findByCodeGroup("Aladin-ItemList");

        BiFunction<List<CommonCode>, String, String> getNameLambda = (list, str) -> {
            return list.stream()
                    .filter(l -> l.getCode().equals(str))
                    .map(c -> (CommonCode) c)
                    .findFirst().get().getName();
        };

        ItemListCondition condition = ItemListCondition.builder()
                .ttbkey(apiKey)
                .queryType(getNameLambda.apply(commonCodeList, "QueryType"))
                .maxResults(Integer.parseInt(getNameLambda.apply(commonCodeList, "MaxResults")))
                .searchTarget(getNameLambda.apply(commonCodeList, "SearchTarget"))
                .output(getNameLambda.apply(commonCodeList, "output"))
                .version(getNameLambda.apply(commonCodeList, "Version"))
                .cover(getNameLambda.apply(commonCodeList, "Cover"))
                .build();

        List<ItemListDTO> list = new ArrayList<>();

        Map<String, String> map = Utility.getMapByClass(condition);

        String urlParam =  Utility.getUrlParameterFormat(map);
        String baseUrl = "http://www.aladin.co.kr/ttb/api/ItemList.aspx?";

        RequestData requestData = RequestData.builder()
                .url(baseUrl + urlParam)
                .method("GET")
                .userAgent("Mozilla/5.0")
                .build();

        list.add(Utility.<ItemListDTO>getRequest(requestData, ItemListDTO.class));

        condition.setQueryType("ItemNewAll");
        map = Utility.getMapByClass(condition);

        urlParam =  Utility.getUrlParameterFormat(map);
        requestData.setUrl(baseUrl + urlParam);

        list.add(Utility.<ItemListDTO>getRequest(requestData, ItemListDTO.class));

        return list;
    }
}
