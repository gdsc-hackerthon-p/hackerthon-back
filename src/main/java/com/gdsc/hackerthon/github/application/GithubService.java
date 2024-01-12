package com.gdsc.hackerthon.github.application;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gdsc.hackerthon.util.api.ResponseCode;
import com.gdsc.hackerthon.util.exception.UserException;
import lombok.RequiredArgsConstructor;
import org.kohsuke.github.*;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

@Service
@RequiredArgsConstructor
public class GithubService {

    public int getCommitStreak(String githubId) throws IOException {
        GHUser user = getGithubUser(githubId);
        return countConsecutiveCommits(user);
    }

    public GHUser getGithubUser(String githubId) throws IOException {
        try{
            GitHub github = new GitHubBuilder().build();
            return github.getUser(githubId);
        }catch (IOException e) {
            throw new UserException(ResponseCode.GITHUB_USER_NOT_FOUND);
        }
    }

    public int countConsecutiveCommits(GHUser user) throws IOException {
        Calendar calendar = Calendar.getInstance();
        Set<Date> commitDates = new HashSet<>();

        for (GHRepository repo : user.listRepositories()) {
            for (GHCommit commit : repo.listCommits()) {
                Date commitDate = commit.getCommitDate();
                // 커밋 날짜를 날짜만 포함하도록 조정
                calendar.setTime(commitDate);
                calendar.set(Calendar.HOUR_OF_DAY, 0);
                calendar.set(Calendar.MINUTE, 0);
                calendar.set(Calendar.SECOND, 0);
                calendar.set(Calendar.MILLISECOND, 0);
                commitDates.add(calendar.getTime());
            }
        }

        int count = 0;
        while (commitDates.contains(calendar.getTime())) {
            count++;
            calendar.add(Calendar.DATE, -1);
        }

        return count;
    }


}
