package net.xanarey.securitylearn.service;

import net.xanarey.securitylearn.model.CityNotification;

import java.util.List;

public interface CityNotificationService {

    CityNotification findByName(String name);

    CityNotification getById(Long id);

    List<CityNotification> getAllCity();

    CityNotification save(CityNotification city);

    void delete(CityNotification city);

    void update(CityNotification city);

}
