package com.gdsc.hackerthon.user.application;

import com.gdsc.hackerthon.user.domain.User;
import com.gdsc.hackerthon.user.domain.UserRival;
import java.util.List;


public interface RivalService {

    void addRival(Long userId, Long rivalId);
    void requestToUser(Long requestId);
    void requestFromUser(Long requestId);

    void deleteRival(Long requestId);
}
