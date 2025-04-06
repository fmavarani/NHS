package org.alerts.entity;

import jakarta.persistence.*;
import lombok.*;


@Getter
@Setter
@Entity
@AllArgsConstructor
@Table(name = "alert")
@NoArgsConstructor
public class Alert {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Primary key

    @Column(nullable = false)
    private String productUrl;

    @Column(nullable = false)
    private String cron;

    @Column(nullable = false)
    private Double desiredPrice;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user.id", nullable = false)
    private User user;

    @Builder
    public Alert(String productUrl, String cron, Double desiredPrice, User user) {
        this.productUrl = productUrl;
        this.cron = cron;
        this.desiredPrice = desiredPrice;
        this.user = user;
    }

    @Override
    public String toString() {
        return "Alert{" +
                "id=" + id +
                ", productUrl='" + productUrl + '\'' +
                ", cron='" + cron + '\'' +
                ", desiredPrice=" + desiredPrice +
                ", user=" + (user != null ? user.getId() : null) +
                '}';
    }
}
