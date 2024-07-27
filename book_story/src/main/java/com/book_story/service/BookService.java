package com.book_story.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;

import com.book_story.common.Utility;
import com.book_story.models.dto.aladin.*;
import com.book_story.models.entity.CommonCode;
import com.book_story.repository.CommonCodeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class BookService {
    private final CommonCodeRepository commonCodeRepository;

    @Value("${aladin.apikey}")
    private String apiKey;

    public List<ItemListDTO> findItemList() throws IOException {
        List<ItemListDTO> list = new ArrayList<>();
        list.add(findItemListBestseller());
        list.add(findItemListNewAll());
        return list;
    }


    public ItemListDTO findItemListBestseller() throws IOException {
        ItemListCondition condition = setDefaultItemListCondition();
        Map<String, String> map = Utility.getMapByClass(condition);
        String urlParam = Utility.getUrlParameterFormat(map);
        String baseUrl = "http://www.aladin.co.kr/ttb/api/ItemList.aspx?";

        RequestData requestData = RequestData.builder()
                .url(baseUrl + urlParam)
                .method("GET")
                .userAgent("Mozilla/5.0")
                .build();

        return Utility.<ItemListDTO>getRequest(requestData, ItemListDTO.class);
    }


    public ItemListDTO findItemListNewAll() throws IOException {
        ItemListCondition condition = setDefaultItemListCondition();
        condition.setQueryType("ItemNewAll");
        Map<String, String> map = Utility.getMapByClass(condition);
        String urlParam = Utility.getUrlParameterFormat(map);
        String baseUrl = "http://www.aladin.co.kr/ttb/api/ItemList.aspx?";

        RequestData requestData = RequestData.builder()
                .url(baseUrl + urlParam)
                .method("GET")
                .userAgent("Mozilla/5.0")
                .build();

        return Utility.<ItemListDTO>getRequest(requestData, ItemListDTO.class);
    }


    public ItemSearchDTO itemSearch(String searchText) throws IOException {
        ItemSearchCondition condition = setDefaultItemSearchCondition(searchText);
        Map<String, String> map = Utility.getMapByClass(condition);
        String urlParam = Utility.getUrlParameterFormat(map);
        String baseUrl = "http://www.aladin.co.kr/ttb/api/ItemSearch.aspx?";

        RequestData requestData = RequestData.builder()
                .url(baseUrl + urlParam)
                .method("GET")
                .userAgent("Mozilla/5.0")
                .build();

        return Utility.<ItemSearchDTO>getRequest(requestData, ItemSearchDTO.class);
    }


    private ItemListCondition setDefaultItemListCondition() {
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

        return condition;
    }


    private ItemSearchCondition setDefaultItemSearchCondition(String searchText) {
        List<CommonCode> commonCodeList = commonCodeRepository.findByCodeGroup("Aladin-ItemSearch");
        BiFunction<List<CommonCode>, String, String> getNameLambda = (list, str) -> {
            return list.stream()
                    .filter(l -> l.getCode().equals(str))
                    .map(c -> (CommonCode) c)
                    .findFirst().get().getName();
        };

        ItemSearchCondition condition = ItemSearchCondition.builder()
                .ttbkey(apiKey)
                .query(searchText)
                .queryType(getNameLambda.apply(commonCodeList, "QueryType"))
                .maxResults(Integer.parseInt(getNameLambda.apply(commonCodeList, "MaxResults")))
                .searchTarget(getNameLambda.apply(commonCodeList, "SearchTarget"))
                .output(getNameLambda.apply(commonCodeList, "output"))
                .version(getNameLambda.apply(commonCodeList, "Version"))
                .cover(getNameLambda.apply(commonCodeList, "Cover"))
                .build();

        return condition;
    }
}
