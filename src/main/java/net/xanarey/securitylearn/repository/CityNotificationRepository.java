package net.xanarey.securitylearn.repository;

import net.xanarey.securitylearn.model.CityNotification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CityNotificationRepository extends JpaRepository<CityNotification, Long> {
    Optional<CityNotification> findByCity(String name);
}
