package com.example.resume.service;

import org.springframework.stereotype.Service;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class SkillExtractorService {

    private static final List<String> SKILLS = List.of(
            "java", "spring", "spring boot", "sql", "python", "kafka", "docker"
    );

    public Set<String> extractSkills(String text) {
        Set<String> found = new HashSet<>();
        text = text.toLowerCase();

        for (String skill : SKILLS) {
            if (text.contains(skill)) {
                found.add(skill);
            }
        }
        return found;
    }
}

