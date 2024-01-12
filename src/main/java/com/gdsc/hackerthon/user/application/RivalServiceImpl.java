package com.gdsc.hackerthon.user.application;

import com.gdsc.hackerthon.user.domain.User;
import com.gdsc.hackerthon.user.domain.UserPrevFirst;
import com.gdsc.hackerthon.user.domain.UserRival;
import com.gdsc.hackerthon.user.repository.RivalRepository;
import com.gdsc.hackerthon.user.repository.UserRepository;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class RivalServiceImpl implements RivalService{


    private final RivalRepository rivalRepository;
    private final UserRepository userRepository;

    /*
    @Override
    public List<UserRival> getRival(Long userId) {
        return rivalRepository.findRival(userId);
    }

    @Override
    public void updateRival(Long userId, UserRival point){
        rivalRepository.findRival(userId);
    }

     */

    @Override
    public void addRival(Long userId, Long rivalId){
        if (!userRepository.existsById(rivalId)){

        }
        User user_id = userRepository.findById(userId).orElse(null);
        User rival_id = userRepository.findById(rivalId).orElse(null);

        userRepository.save(user_id);

    }

    @Override
    public void requestToUser(Long requestId){
        Optional<UserRival> findrivalRequest = rivalRepository.findById(requestId);
        UserRival userRival = findrivalRequest.get();
        rivalRepository.save(userRival);
    }

    @Override
    public void requestFromUser(Long requestId){
        Optional<UserRival> findrivalRequest = rivalRepository.findById(requestId);
        UserRival userRival = findrivalRequest.get();
        rivalRepository.save(userRival);
    }

    @Override
    public void deleteRival(Long requestId){
        rivalRepository.deleteById(requestId);
    }

}

