package com.gdsc.hackerthon.github.application;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gdsc.hackerthon.util.api.ResponseCode;
import com.gdsc.hackerthon.util.exception.UserException;
import lombok.RequiredArgsConstructor;
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
        return countConsecutiveCommits(user, githubId);
    }

    public GHUser getGithubUser(String githubId) throws IOException {
        try{
            GitHub github = new GitHubBuilder().withOAuthToken("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").build();
            log.info("githubId: {}", githubId);
            return github.getUser(githubId);
        }catch (IOException e) {
            throw new UserException(ResponseCode.GITHUB_USER_NOT_FOUND);
        }
    }

    public int countConsecutiveCommits(GHUser user, String githubId) throws IOException {
        Calendar calendar = Calendar.getInstance();
        Set<Date> commitDates = new HashSet<>();

        GitHub github = new GitHubBuilder().withOAuthToken("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").build();
        int count = 0;
        //오늘부터 날짜를 거슬러 올라오면서 커밋이 한개라도 있으면 다음 날짜로 넘어가고, 커밋이 없으면 멈춤
        while (true) {
            GHCommitSearchBuilder commitSearchBuilder = github.searchCommits()
                    .author(githubId)
                    .committerDate(calendar.toString());

            GHCommit[] commits = commitSearchBuilder.list().toArray();

            if (commits.length == 0) {
                break;
            }
            count += 1;
            calendar.add(Calendar.DATE, -1);
        }
        return count;
    }


}
