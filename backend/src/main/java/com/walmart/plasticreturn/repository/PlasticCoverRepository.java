package com.walmart.plasticreturn.repository;

import com.walmart.plasticreturn.model.PlasticCover;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PlasticCoverRepository extends JpaRepository<PlasticCover, Long> {
    Optional<PlasticCover> findByBarcode(String barcode);
}
