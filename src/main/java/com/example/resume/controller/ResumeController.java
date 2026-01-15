package com.example.resume.controller;

import com.example.resume.model.ResumeResult;
import com.example.resume.repository.ResumeRepository;
import com.example.resume.service.MatchEngineService;
import com.example.resume.service.ResumeParserService;
import com.example.resume.service.SkillExtractorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.Set;


@RestController
@RequestMapping("/api/resume")
public class ResumeController {

    private final ResumeParserService parserService;
    private final SkillExtractorService skillService;
    private final MatchEngineService matchService;
    private final ResumeRepository repository;

    @Autowired
    public ResumeController(ResumeParserService parserService,
                           SkillExtractorService skillService,
                           MatchEngineService matchService,
                           ResumeRepository repository) {
        this.parserService = parserService;
        this.skillService = skillService;
        this.matchService = matchService;
        this.repository = repository;
    }

    @PostMapping("/upload")
    public ResumeResult uploadResume(
            @RequestParam MultipartFile resume,
            @RequestParam String jobDescription) throws Exception {

        // Input validation
        if (resume.isEmpty()) {
            throw new IllegalArgumentException("Resume file cannot be empty");
        }
        
        if (jobDescription == null || jobDescription.trim().isEmpty()) {
            throw new IllegalArgumentException("Job description cannot be empty");
        }

        String resumeText = parserService.extractText(resume);

        Set<String> resumeSkills = skillService.extractSkills(resumeText);
        Set<String> jdSkills = skillService.extractSkills(jobDescription);

        double match = matchService.calculateMatch(resumeSkills, jdSkills);

        ResumeResult result = new ResumeResult();
        result.setCandidateName(resume.getOriginalFilename());
        result.setMatchPercentage(match);

        return repository.save(result);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public org.springframework.http.ResponseEntity<String> handleIllegalArgument(IllegalArgumentException e) {
        return org.springframework.http.ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public org.springframework.http.ResponseEntity<String> handleGenericException(Exception e) {
        return org.springframework.http.ResponseEntity.status(500)
                .body("An error occurred while processing the resume: " + e.getMessage());
    }
}
