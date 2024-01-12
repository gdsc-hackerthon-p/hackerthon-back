package com.gdsc.hackerthon.user.repository;

import com.gdsc.hackerthon.HackerthonApplication;
import com.gdsc.hackerthon.user.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ContextConfiguration;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@DataJpaTest
@ContextConfiguration(classes = HackerthonApplication.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    public void UserRepositoryIsNotNull() {
        assertThat(userRepository).isNotNull();
    }

    @Test
    public void 유저등록(){
        //given
        final User user = User.builder()
                .nickname("testNickname")
                .githubEmail("testEmail")
                .profileImageUrl("testImageUrl")
                .point(0)
                .commitStreak(0)
                .githubUrl("testGithubUrl")
                .build();

        //when
        final User savedUser = userRepository.save(user);

        //then
        assertThat(savedUser.getId()).isNotNull();
        assertThat(savedUser.getNickname()).isEqualTo("testNickname");
        assertThat(savedUser.getGithubEmail()).isEqualTo("testEmail");
        assertThat(savedUser.getProfileImageUrl()).isEqualTo("testImageUrl");
        assertThat(savedUser.getPoint()).isEqualTo(0);
        assertThat(savedUser.getCommitStreak()).isEqualTo(0);
        assertThat(savedUser.getGithubUrl()).isEqualTo("testGithubUrl");

    }

    @Test
    public void 유저가_존재하는가(){
        //given
        final User user = User.builder()
                .nickname("testNickname")
                .githubEmail("testEmail")
                .profileImageUrl("testImageUrl")
                .point(0)
                .commitStreak(0)
                .githubUrl("testGithubUrl")
                .build();

        //when
        userRepository.save(user);
        final User userResult = userRepository.findByGithubEmail("testEmail");

        //then
        assertThat(userResult.getNickname()).isEqualTo("testNickname");
        assertThat(userResult.getGithubEmail()).isEqualTo("testEmail");
        assertThat(userResult.getProfileImageUrl()).isEqualTo("testImageUrl");
        assertThat(userResult.getPoint()).isEqualTo(0);
        assertThat(userResult.getCommitStreak()).isEqualTo(0);
    }

    @Test
    public void 페이징을_통한_유저_조회(){
        //given
        final User user1 = User.builder()
                .nickname("testNickname1")
                .githubEmail("testEmail1")
                .profileImageUrl("testImageUrl1")
                .point(0)
                .commitStreak(0)
                .githubUrl("testGithubUrl1")
                .build();

        final User user2 = User.builder()
                .nickname("testNickname2")
                .githubEmail("testEmail2")
                .profileImageUrl("testImageUrl2")
                .point(0)
                .commitStreak(0)
                .githubUrl("testGithubUrl2")
                .build();

        //when
        userRepository.save(user1);
        userRepository.save(user2);

        final Pageable pageable = PageRequest.of(0, 1);
        final User userResult = userRepository.findAll(pageable).getContent().get(0);

        //then
        assertThat(userResult.getNickname()).isEqualTo("testNickname1");
        assertThat(userResult.getGithubEmail()).isEqualTo("testEmail1");
        assertThat(userResult.getProfileImageUrl()).isEqualTo("testImageUrl1");
        assertThat(userResult.getPoint()).isEqualTo(0);
        assertThat(userResult.getCommitStreak()).isEqualTo(0);
        assertThat(userResult.getGithubUrl()).isEqualTo("testGithubUrl1");
    }


}
