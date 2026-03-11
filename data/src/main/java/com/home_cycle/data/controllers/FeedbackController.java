package com.home_cycle.data.controllers;

import com.home_cycle.data.dto.request.FeedbackDTO;
import com.home_cycle.data.services.EmailService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/feedback")
public class FeedbackController {
    private final EmailService emailService;

    public FeedbackController(EmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping
    public void submitFeedback(@Valid @RequestBody FeedbackDTO feedbackDTO) {
        emailService.sendFeedbackEmail(feedbackDTO);
    }
}
