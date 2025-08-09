package com.walmart.plasticreturn;
import com.walmart.plasticreturn.model.PlasticCover;
import com.walmart.plasticreturn.model.User;
import com.walmart.plasticreturn.repository.PlasticCoverRepository;
import com.walmart.plasticreturn.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;

@Configuration
public class DataSeeder {

    @Bean
    public CommandLineRunner seedDatabase(UserRepository userRepo, PlasticCoverRepository coverRepo) {
        return args -> {

            if (userRepo.findByEmail("john.doe@example.com") == null) {
                User user = new User();
                user.setName("John Doe");
                user.setEmail("john.doe@example.com");
                user.setEcoPoints(0);
                userRepo.save(user);

                PlasticCover cover = new PlasticCover();
                cover.setBarcode("WMT-12345");
                cover.setPurchaseDate(LocalDateTime.now().minusDays(7));
                cover.setReturned(false);
                cover.setUser(user);
                coverRepo.save(cover);
            }

            if (userRepo.findByEmail("jane.smith@example.com") == null) {
                User user2 = new User();
                user2.setName("Jane Smith");
                user2.setEmail("jane.smith@example.com");
                user2.setEcoPoints(10);
                userRepo.save(user2);

                PlasticCover cover2a = new PlasticCover();
                cover2a.setBarcode("WMT-22222");
                cover2a.setPurchaseDate(LocalDateTime.now().minusDays(5));
                cover2a.setReturned(false);
                cover2a.setUser(user2);
                coverRepo.save(cover2a);

                PlasticCover cover2b = new PlasticCover();
                cover2b.setBarcode("WMT-22223");
                cover2b.setPurchaseDate(LocalDateTime.now().minusDays(3));
                cover2b.setReturned(true); 
                cover2b.setUser(user2);
                coverRepo.save(cover2b);
            }

            if (userRepo.findByEmail("alice.wonder@example.com") == null) {
                User user3 = new User();
                user3.setName("Alice Wonder");
                user3.setEmail("alice.wonder@example.com");
                user3.setEcoPoints(20);
                userRepo.save(user3);

                PlasticCover cover3a = new PlasticCover();
                cover3a.setBarcode("WMT-33331");
                cover3a.setPurchaseDate(LocalDateTime.now().minusDays(10));
                cover3a.setReturned(false);
                cover3a.setUser(user3);
                coverRepo.save(cover3a);

                PlasticCover cover3b = new PlasticCover();
                cover3b.setBarcode("WMT-33332");
                cover3b.setPurchaseDate(LocalDateTime.now().minusDays(4));
                cover3b.setReturned(true); 
                cover3b.setUser(user3);
                coverRepo.save(cover3b);

                PlasticCover cover3c = new PlasticCover();
                cover3c.setBarcode("WMT-33333");
                cover3c.setPurchaseDate(LocalDateTime.now().minusDays(1));
                cover3c.setReturned(false);
                cover3c.setUser(user3);
                coverRepo.save(cover3c);
            }

            System.out.println("✅ Multiple dummy users and covers seeded.");
        };
    }
}
