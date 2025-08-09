package com.walmart.plasticreturn.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendSummaryEmail(String toEmail, int totalPoints, List<String> barcodes) throws MessagingException {
    	System.out.println("📤 Sending email to: " + toEmail);
        System.out.println("📬 Subject: Plastic Return Summary");
        System.out.println("✅ Total Points: " + totalPoints);
        System.out.println("📦 Barcodes: " + barcodes);
        
        
    	MimeMessage message = mailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

        helper.setTo(toEmail);
        helper.setSubject("✅ EcoPoints Summary - Plastic Cover Returns");

        StringBuilder html = new StringBuilder();
        html.append("<div style='font-family:Arial,sans-serif;padding:20px;'>");
        html.append("<h2 style='color:green;'>🌱 EcoPoints Summary</h2>");
        html.append("<p>Hi,</p>");
        html.append("<p>Thank you for returning your plastic covers today! ♻️</p>");
        
        html.append("<div style='margin-top:20px;padding:10px;background:#e0f7e9;color:#2e7d32;font-weight:bold;width:fit-content;border-radius:6px;'>");
        html.append("Total EcoPoints Earned: ").append(totalPoints).append(" points");
        html.append("</div>");
        
        html.append("<p><strong>📦 Barcodes scanned:</strong></p>");
        html.append("<ul>");
        for (String barcode : barcodes) {
            html.append("<li>").append(barcode).append("</li>");
        }
        html.append("</ul>");
        
        html.append("<p style='margin-top:20px;'>Keep up the good work for a greener tomorrow!</p>");
        html.append("<p>Regards,<br/>EcoRewards Team</p>");
        html.append("</div>");

        helper.setText(html.toString(), true); 

        mailSender.send(message);
    }
}
