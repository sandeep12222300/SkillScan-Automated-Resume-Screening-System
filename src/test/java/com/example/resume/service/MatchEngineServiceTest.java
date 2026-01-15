package com.example.resume.service;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class MatchEngineServiceTest {

    private final MatchEngineService matchEngineService = new MatchEngineService();

    @Test
    void testCalculateMatch_PerfectMatch() {
        Set<String> resumeSkills = Set.of("java", "spring", "sql");
        Set<String> jdSkills = Set.of("java", "spring", "sql");

        double match = matchEngineService.calculateMatch(resumeSkills, jdSkills);

        assertEquals(100.0, match, 0.01);
    }

    @Test
    void testCalculateMatch_PartialMatch() {
        Set<String> resumeSkills = Set.of("java", "spring");
        Set<String> jdSkills = Set.of("java", "spring", "sql", "kafka");

        double match = matchEngineService.calculateMatch(resumeSkills, jdSkills);

        assertEquals(50.0, match, 0.01);
    }

    @Test
    void testCalculateMatch_NoMatch() {
        Set<String> resumeSkills = Set.of("python", "django");
        Set<String> jdSkills = Set.of("java", "spring");

        double match = matchEngineService.calculateMatch(resumeSkills, jdSkills);

        assertEquals(0.0, match, 0.01);
    }

    @Test
    void testCalculateMatch_EmptyResumeSkills() {
        Set<String> resumeSkills = new HashSet<>();
        Set<String> jdSkills = Set.of("java", "spring");

        double match = matchEngineService.calculateMatch(resumeSkills, jdSkills);

        assertEquals(0.0, match, 0.01);
    }

    @Test
    void testCalculateMatch_EmptyJdSkills() {
        Set<String> resumeSkills = Set.of("java", "spring");
        Set<String> jdSkills = new HashSet<>();

        double match = matchEngineService.calculateMatch(resumeSkills, jdSkills);

        assertEquals(0.0, match, 0.01);
    }

    @Test
    void testCalculateMatch_NullJdSkills() {
        Set<String> resumeSkills = Set.of("java", "spring");

        double match = matchEngineService.calculateMatch(resumeSkills, null);

        assertEquals(0.0, match, 0.01);
    }
}
