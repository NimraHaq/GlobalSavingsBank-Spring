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
@Table(name = "cards")
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "card_srno")
    private long cardSrno;

    @Column(name = "card_no", unique = true, nullable = false, updatable = false)
    private long cardNo;

    @Column(name = "category", length = 1)
    private String category;

    @Column(name = "ch_id")
    private int ch_id;

    @Column(name = "primary_cardno")
    private long primaryCardNo;

    @Check(constraints = "is_main_card IN ('Y', 'N')")
    @ColumnDefault("'Y'")
    @Column(name = "is_main_card", length = 1, nullable = false,
    columnDefinition = "CHAR(1) DEFAULT 'Y'")
    private String isMainCard= Constants.IS_ACTIVE;

    @Check(constraints = "is_active IN ('Y', 'N')")
    @ColumnDefault("'Y'")
    @Column(name = "is_active", length = 1, nullable = false,
            columnDefinition = "CHAR(1) DEFAULT 'Y'")
    private String isActive= Constants.IS_ACTIVE;

    @Column(name="created_on", nullable = false)
    @CreationTimestamp
    private LocalDateTime createdOn;

    @Column(name="last_updated_on")
    @UpdateTimestamp
    private LocalDateTime lastUpdatedOn;
}
