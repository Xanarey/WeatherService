package net.xanarey.securitylearn.service.impl;

import lombok.extern.slf4j.Slf4j;
import net.xanarey.securitylearn.model.CityNotification;
import net.xanarey.securitylearn.repository.CityNotificationRepository;
import net.xanarey.securitylearn.service.CityNotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class CityNotificationImpl implements CityNotificationService {

    private final CityNotificationRepository cityNotificationRepository;

    @Autowired
    public CityNotificationImpl(CityNotificationRepository cityNotificationRepository) {
        this.cityNotificationRepository = cityNotificationRepository;
    }

    @Override
    public CityNotification findByName(String name) {
        log.info("IN CityNotificationImpl findByName {}", name);
        return cityNotificationRepository.findByCity(name).orElse(new CityNotification());
    }

    @Override
    public CityNotification getById(Long id) {
        log.info("IN CityNotificationImpl getById {}", id);
        return cityNotificationRepository.findById(id).orElse(new CityNotification());
    }

    @Override
    public List<CityNotification> getAllCity() {
        log.info("IN CityNotificationImpl getAllCity");
        return cityNotificationRepository.findAll();
    }

    @Override
    public CityNotification save(CityNotification city) {
        log.info("IN CityNotificationImpl save {}", city);
        return cityNotificationRepository.save(city);
    }

    @Override
    public void delete(CityNotification city) {
        log.info("IN CityNotificationImpl delete {}", city);
        cityNotificationRepository.delete(city);
    }

    @Override
    public void update(CityNotification city) {
        log.info("IN CityNotificationImpl update {}", city);
        cityNotificationRepository.save(city);
    }
}
