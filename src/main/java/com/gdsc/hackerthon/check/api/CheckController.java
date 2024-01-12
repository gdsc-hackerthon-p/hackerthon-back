package com.gdsc.hackerthon.check.api;

import com.gdsc.hackerthon.check.application.CheckService;
import com.gdsc.hackerthon.check.domain.Check;
import com.gdsc.hackerthon.util.api.ApiResponse;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/check")
@RequiredArgsConstructor
public class CheckController {

    private final CheckService checkService;

    @PostMapping("/createCheck")
    public ApiResponse<Check> createCheck(@RequestBody Check check) {
        Check createdCheck = checkService.createCheck(check);
        return ApiResponse.success(checkService.createCheck(createdCheck),"방명록 글쓰기 성공");
    }

    @DeleteMapping("/deleteCheck/{id}")
    public ApiResponse<Void> deleteCheck(@PathVariable Long id) {
        checkService.deleteCheck(id);
        return ApiResponse.success(null,"방명록 글삭제 성공");
    }

    @PutMapping("/updateCheck/{id}")
    public ApiResponse<Void> updateCheck(@PathVariable Long id, @RequestParam String content) {
        checkService.updateCheck(id,content);
        return ApiResponse.success(null,"방명록 글수정 성공");
    }

    @GetMapping("/getCheck")
    public ApiResponse<Check> getCheck(@PathVariable Long id) {
        Check check = checkService.getCheck(id);
        return ApiResponse.success(checkService.getCheck(id),"방명록 글 조회 성공");
    }

    @GetMapping("/getAllChecksInDate")
    public ApiResponse<Page<Check>> getAllChecksInDate(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime checkDate,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Check> check = checkService.getAllChecksInDate(pageable, checkDate);
        return ApiResponse.success(checkService.getAllChecksInDate(pageable,checkDate),"날짜별 조회 성공");
    }


}
