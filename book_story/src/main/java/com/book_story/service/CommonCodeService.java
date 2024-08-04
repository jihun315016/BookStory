package com.book_story.service;

import com.book_story.models.entity.CommonCode;
import com.book_story.repository.CommonCodeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.BiFunction;

@RequiredArgsConstructor
@Service
public class CommonCodeService {
    private final CommonCodeRepository commonCodeRepository;

    List<CommonCode> findByCodeGroup(String codeGroup) {
        return commonCodeRepository.findByCodeGroup(codeGroup);
    }


    public String getCommonCode(List<CommonCode> list, String code) {
        return list.stream()
                .filter(l -> l.getCode().equals(code))
                .map(c -> (CommonCode) c)
                .findFirst().get().getName();
    }
}
