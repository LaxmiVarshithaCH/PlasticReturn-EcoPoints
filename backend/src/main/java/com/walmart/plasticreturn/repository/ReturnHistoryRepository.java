package com.walmart.plasticreturn.repository;

import com.walmart.plasticreturn.model.ReturnHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import com.walmart.plasticreturn.model.User;
public interface ReturnHistoryRepository extends JpaRepository<ReturnHistory, Long> {
	 List<ReturnHistory> findByUser(User user);
}
