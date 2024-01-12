package com.gdsc.hackerthon.check.application;

import com.gdsc.hackerthon.check.domain.Check;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;

public interface CheckService {
    //방명록에 글 남기기
    Check createCheck(Check check);

    //방명록에 글 삭제하기
    void deleteCheck(Long id);

    //방명록에 글 수정하기
    void updateCheck(Long id, String content);

    //방명록에 글 조회하기
    Check getCheck(Long id);

    Page<Check> getAllChecksInDate(Pageable pageable, LocalDateTime checkDate);
}
