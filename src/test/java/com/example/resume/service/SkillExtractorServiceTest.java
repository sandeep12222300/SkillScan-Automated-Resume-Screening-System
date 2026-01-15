package com.example.resume.service;

import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class SkillExtractorServiceTest {

    private final SkillExtractorService skillExtractorService = new SkillExtractorService();

    @Test
    void testExtractSkills_FindsAllSkills() {
        String text = "I have experience with Java, Spring Boot, SQL, Python, Kafka, and Docker";
        
        Set<String> skills = skillExtractorService.extractSkills(text);
        
        assertTrue(skills.contains("java"));
        assertTrue(skills.contains("spring boot"));
        assertTrue(skills.contains("sql"));
        assertTrue(skills.contains("python"));
        assertTrue(skills.contains("kafka"));
        assertTrue(skills.contains("docker"));
    }

    @Test
    void testExtractSkills_CaseInsensitive() {
        String text = "JAVA Spring BOOT sql PYTHON";
        
        Set<String> skills = skillExtractorService.extractSkills(text);
        
        assertTrue(skills.contains("java"));
        assertTrue(skills.contains("spring boot"));
        assertTrue(skills.contains("sql"));
        assertTrue(skills.contains("python"));
    }

    @Test
    void testExtractSkills_NoSkillsFound() {
        String text = "I have experience with C++, Ruby, and PHP";
        
        Set<String> skills = skillExtractorService.extractSkills(text);
        
        assertTrue(skills.isEmpty());
    }

    @Test
    void testExtractSkills_PartialMatch() {
        String text = "I know Java and SQL";
        
        Set<String> skills = skillExtractorService.extractSkills(text);
        
        assertEquals(2, skills.size());
        assertTrue(skills.contains("java"));
        assertTrue(skills.contains("sql"));
    }

    @Test
    void testExtractSkills_EmptyText() {
        String text = "";
        
        Set<String> skills = skillExtractorService.extractSkills(text);
        
        assertTrue(skills.isEmpty());
    }

    @Test
    void testExtractSkills_OnlySpring() {
        String text = "Spring framework experience";
        
        Set<String> skills = skillExtractorService.extractSkills(text);
        
        assertTrue(skills.contains("spring"));
    }
}
