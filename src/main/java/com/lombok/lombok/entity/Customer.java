package com.lombok.lombok.entity;

import com.lombok.lombok.utils.Constants;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Check;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
@Table(name="customers")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ch_id")
    private int chId;


    @Check(constraints = "is_active IN ('Y', 'N')")
    @ColumnDefault("'Y'")
    @Column(name = "is_active", length = 1, nullable = false,
            columnDefinition = "CHAR(1) DEFAULT 'Y'")
    @Builder.Default
    private String isActive = Constants.IS_ACTIVE;

    @Column(name = "default_card_srno")
    @Builder.Default
    private Long defaultCardSrno = null;

    @Builder.Default
    @Column(name = "registered_cards")
    private int registeredCards = 0;


    @OneToOne(mappedBy = "customer", cascade = CascadeType.ALL)
    private User user;


    @Column(name="created_on", nullable = false)
    @CreationTimestamp
    private LocalDateTime createdOn;

    @Column(name="last_updated_on", nullable = true)
    @UpdateTimestamp
    private LocalDateTime lastUpdatedOn;

}
