package com.walmart.plasticreturn.service;

import com.walmart.plasticreturn.model.*;
import com.walmart.plasticreturn.repository.*;
import com.walmart.plasticreturn.util.BarcodeDecoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.walmart.plasticreturn.dto.ScanResponse;
import java.time.LocalDateTime;

@Service
public class PlasticReturnService {

    @Autowired
    private PlasticCoverRepository plasticCoverRepo;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private ReturnHistoryRepository returnHistoryRepo;
    


    public String handleBarcodeScan(MultipartFile file) throws Exception {
        String decoded = BarcodeDecoder.decode(file.getInputStream());

        if (decoded == null) {
            return "Failed to decode barcode.";
        }

        var optionalCover = plasticCoverRepo.findByBarcode(decoded);

        if (optionalCover.isEmpty()) {
            return "Barcode not found.";
        }

        PlasticCover cover = optionalCover.get();

        if (cover.isReturned()) {
            return "This plastic cover was already returned.";
        }

        cover.setReturned(true);
        plasticCoverRepo.save(cover);

        User user = cover.getUser();
        user.setEcoPoints(user.getEcoPoints() + 10); // Add 10 points
        userRepo.save(user);

        ReturnHistory history = new ReturnHistory();
        history.setBarcode(decoded);
        history.setReturnDate(LocalDateTime.now());
        history.setPointsAwarded(10);
        history.setUser(user);
        returnHistoryRepo.save(history);

        return "Success! 10 EcoPoints added for barcode: " + decoded;
    }

    public ScanResponse handleBarcodeFromText(String barcode) {
        System.out.println("📦 Received barcode: " + barcode);
        var optionalCover = plasticCoverRepo.findByBarcode(barcode);

        if (optionalCover.isEmpty()) {
            System.out.println("❌ Barcode not found: " + barcode);
            return new ScanResponse("❌ Barcode not found.", null, 0);
        }

        PlasticCover cover = optionalCover.get();

        if (cover.isReturned()) {
            System.out.println("⚠️ Barcode already returned: " + barcode);
            return new ScanResponse("⚠️ This plastic cover was already returned.", cover.getUser().getEmail(), 0);
        }

        cover.setReturned(true);
        plasticCoverRepo.save(cover);

        User user = cover.getUser();
        user.setEcoPoints(user.getEcoPoints() + 10);
        userRepo.save(user);

        ReturnHistory history = new ReturnHistory();
        history.setBarcode(barcode);
        history.setReturnDate(LocalDateTime.now());
        history.setPointsAwarded(10);
        history.setUser(user);
        returnHistoryRepo.save(history);

        System.out.println("✅ Points updated and history saved.");

        return new ScanResponse("✅ Success! 10 EcoPoints added for barcode: " + barcode, user.getEmail(), 10);
    }


}
