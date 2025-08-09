package com.walmart.plasticreturn.controller;

import com.walmart.plasticreturn.service.PlasticReturnService;
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

import com.walmart.plasticreturn.dto.ScanResponse;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class PlasticReturnController {

    @Autowired
    private PlasticReturnService returnService;
    
    @Autowired
    private UserRepository userRepo;

    @Autowired
    private ReturnHistoryRepository returnHistoryRepo;
    
    @Autowired
    private EmailService emailService;
    
    @Autowired
    private PlasticCoverRepository plasticCoverRepo;

    @PostMapping("/scan")
    public ResponseEntity<String> scanBarcode(@RequestParam("file") MultipartFile file) {
        try {
            String response = returnService.handleBarcodeScan(file);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("❌ Error: " + e.getMessage());
        }
    }
    
    @PostMapping("/scan-barcode")
    public ResponseEntity<ScanResponse> scanBarcodeDirect(@RequestBody Map<String, String> request) {
        try {
            String barcode = request.get("barcode");
            System.out.println("📦 Received barcode from React: " + barcode);

            ScanResponse response = returnService.handleBarcodeFromText(barcode);

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            System.out.println("❌ Exception in scanBarcodeDirect: " + e.getMessage());
            return ResponseEntity.internalServerError()
                    .body(new ScanResponse("❌ Error: " + e.getMessage(), null, 0));
        }
    }

    @GetMapping("/scan-barcode")
    public ResponseEntity<String> fallbackGetScanBarcode() {
        return ResponseEntity.status(405).body("❌ This endpoint only supports POST requests.");
    }
    
    @GetMapping("/user")
    public ResponseEntity<?> getUserByEmail(@RequestParam String email) {
        try {
            User user = userRepo.findByEmail(email);
            if (user == null) {
                return ResponseEntity.status(404).body("User not found");
            }
            return ResponseEntity.ok(user);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error: " + e.getMessage());
        }
    }

    @GetMapping("/return-history")
    public ResponseEntity<?> getReturnHistoryByUserEmail(@RequestParam String email) {
        try {
            User user = userRepo.findByEmail(email);
            if (user == null) {
                return ResponseEntity.status(404).body("User not found");
            }

            List<ReturnHistory> history = returnHistoryRepo.findByUser(user);
            return ResponseEntity.ok(history);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error: " + e.getMessage());
        }
    }

}
