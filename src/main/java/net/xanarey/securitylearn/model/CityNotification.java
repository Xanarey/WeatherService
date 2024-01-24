package net.xanarey.securitylearn.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "city_notification")
public class CityNotification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "city")
    private String city;

    @Column(name = "notification_threshold")
    private int notificationThreshold;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
