package com.example.resume.service;

import org.springframework.stereotype.Service;
import java.util.Set;


@Service
public class MatchEngineService {

    public double calculateMatch(Set<String> resumeSkills, Set<String> jdSkills) {
        if (jdSkills == null || jdSkills.isEmpty()) {
            return 0.0; // prevent NaN
        }
        long matched = resumeSkills.stream()
                .filter(jdSkills::contains)
                .count();

        return ((double) matched / jdSkills.size()) * 100;
    }
}

