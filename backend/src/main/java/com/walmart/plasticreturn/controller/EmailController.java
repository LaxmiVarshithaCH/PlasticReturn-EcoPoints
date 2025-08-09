package com.walmart.plasticreturn.controller;

import com.walmart.plasticreturn.service.PlasticReturnService;

import jakarta.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.*;
import com.walmart.plasticreturn.model.User;
import com.walmart.plasticreturn.model.ReturnHistory;
import com.walmart.plasticreturn.repository.UserRepository;
import com.walmart.plasticreturn.repository.ReturnHistoryRepository;
import com.walmart.plasticreturn.repository.PlasticCoverRepository;
import com.walmart.plasticreturn.service.EmailService;
import com.walmart.plasticreturn.dto.EmailSummaryRequest;


@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class EmailController {

    @Autowired
    private EmailService emailService;

    @PostMapping("/send-summary-email")
    public ResponseEntity<String> sendSummaryEmail(@RequestBody EmailSummaryRequest request) {
        if (request.getEmail() == null || request.getEmail().isEmpty()) {
            return ResponseEntity.badRequest().body("❌ Email address is missing.");
        }

        System.out.println("📨 Sending summary email to: " + request.getEmail());
        System.out.println("📦 Barcodes: " + request.getBarcodes());
        System.out.println("🌱 Total Points: " + request.getTotalPoints());

        try {
            emailService.sendSummaryEmail(
                request.getEmail(),
                request.getTotalPoints(),
                request.getBarcodes()
            );
            return ResponseEntity.ok("✅ Summary email sent successfully.");
        } catch (MessagingException e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body("❌ Failed to send email: " + e.getMessage());
        }
    }
}
