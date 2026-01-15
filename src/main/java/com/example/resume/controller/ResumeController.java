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

    @Autowired
    ResumeParserService parserService;

    @Autowired
    SkillExtractorService skillService;

    @Autowired
    MatchEngineService matchService;

    @Autowired
    ResumeRepository repository;

    @PostMapping("/upload")
    public ResumeResult uploadResume(
            @RequestParam MultipartFile resume,
            @RequestParam String jobDescription) throws Exception {

        String resumeText = parserService.extractText(resume);

        Set<String> resumeSkills = skillService.extractSkills(resumeText);
        Set<String> jdSkills = skillService.extractSkills(jobDescription);

        double match = matchService.calculateMatch(resumeSkills, jdSkills);

        ResumeResult result = new ResumeResult();
        result.setCandidateName(resume.getOriginalFilename());
        result.setMatchPercentage(match);

        return repository.save(result);
    }
}
