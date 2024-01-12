package com.gdsc.hackerthon.github.application;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gdsc.hackerthon.util.api.ResponseCode;
import com.gdsc.hackerthon.util.exception.UserException;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.kohsuke.github.*;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class GithubService {

    public int getCommitStreak(String githubId) throws IOException {
        GHUser user = getGithubUser(githubId);
        return countWeeklyCommits(user);
    }

    public GHUser getGithubUser(String githubId) throws IOException {
        try{
            GitHub github = new GitHubBuilder().build();
            return github.getUser(githubId);
        }catch (IOException e) {
            throw new UserException(ResponseCode.GITHUB_USER_NOT_FOUND);
        }
    }

    public int countWeeklyCommits(GHUser user) throws IOException {
        Calendar calendar = new GregorianCalendar();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY); // 현재 주의 월요일로 설정

        Date weekStart = calendar.getTime();
        int count = 0;

        for (GHRepository repo : user.listRepositories()) {
            for (GHCommit commit : repo.listCommits()) {
                Date commitDate = commit.getCommitDate();
                if (!commitDate.before(weekStart)) {
                    count++;
                }
            }
        }
        return count;
    }
}
