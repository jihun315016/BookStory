package com.book_story.repository;

import com.book_story.models.entity.CommonCode;
import com.book_story.models.key.CommonCodePK;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommonCodeRepository extends JpaRepository<CommonCode, CommonCodePK> {
    List<CommonCode> findByCodeGroup(String codeGroup);
}
