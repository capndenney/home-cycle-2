package com.home_cycle.data.services;

import com.home_cycle.data.dto.request.FeedbackDTO;
import com.resend.*;
import com.resend.services.emails.model.SendEmailRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    @Value("${RESEND_API_KEY}")
    private String apiKey;
    @Value("${FEEDBACK_EMAIL}")
    private String myEmail;

    public void sendFeedbackEmail(FeedbackDTO feedback) {
        Resend resend = new Resend(apiKey);

        SendEmailRequest sendEmailRequest = SendEmailRequest.builder()
                .from("onboarding@resend.dev")
                .replyTo(feedback.getEmail())
                .to(myEmail)
                .subject("Home Cycle Feedback Collected from " + feedback.getName())
                .html("<p>" + feedback.getFeedback() + "</p>")
                .build();

        try {
            resend.emails().send(sendEmailRequest);
        } catch (
                Exception e) {
            System.err.println("Error sending email: " + e.getMessage());
        }
    }
}
