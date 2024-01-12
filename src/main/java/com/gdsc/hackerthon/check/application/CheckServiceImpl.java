package com.gdsc.hackerthon.check.application;

import com.gdsc.hackerthon.check.domain.Check;
import com.gdsc.hackerthon.check.repository.CheckRepository;
import com.gdsc.hackerthon.user.domain.User;
import com.gdsc.hackerthon.user.repository.UserRepository;
import com.gdsc.hackerthon.util.api.ResponseCode;
import com.gdsc.hackerthon.util.exception.UserException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class CheckServiceImpl implements CheckService{
    private final CheckRepository checkRepository;
    private final UserRepository userRepository;
    @Override
    public Check createCheck(Check check) {
        User user = findUserById(check.getUser().getId());

        userRepository.updateUserByIdAndPoint(user.getId(), user.getPoint() + 100);
        return checkRepository.save(check);
    }

    @Override
    public void deleteCheck(Long id) {
        User user = findUserById(id);

        userRepository.updateUserByIdAndPoint(user.getId(), user.getPoint() - 100);
        checkRepository.deleteById(id);
    }

    @Override
    public void updateCheck(Long id, String content) {
        Check check = checkRepository.findById(id).orElseThrow(()
                -> new UserException(ResponseCode.CHECK_NOT_FOUND));
        check.updateContent(content);
        checkRepository.save(check);
    }

    @Override
    public Check getCheck(Long id) {
        return checkRepository.findById(id).orElseThrow(()
                -> new UserException(ResponseCode.CHECK_NOT_FOUND));
    }

    @Override
    public Page<Check> getAllChecksInDate(Pageable pageable, LocalDateTime checkDate) {
        return checkRepository.findByCheckDateBetweenOrderByCheckDateDesc(pageable,
                checkDate.withHour(0).withMinute(0).withSecond(0),
                checkDate.withHour(23).withMinute(59).withSecond(59));
    }

    private User findUserById(Long id){
        return userRepository.findById(id).orElseThrow(()
                -> new UserException(ResponseCode.USER_NOT_FOUND));
    }
}
