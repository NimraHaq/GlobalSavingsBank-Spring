package com.lombok.lombok.entity;

import com.lombok.lombok.utils.Constants;
import jakarta.persistence.*;
import lombok.*;
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

    @Check(constraints = "card_status IN ('A', 'B', 'F')")
    @ColumnDefault("'A'")
    @Column(name = "card_status", length = 1, nullable = false)
    @Builder.Default
    private String cardStatus = Constants.CARD_PRE_ACTIVE_STATUS;

    @Check(constraints = "is_active IN ('Y', 'N')")
    @ColumnDefault("'Y'")
    @Column(name = "is_active", length = 1, nullable = false,
            columnDefinition = "CHAR(1) DEFAULT 'Y'")
    private String isActive= Constants.IS_ACTIVE;

    @OneToOne(mappedBy = "card", cascade = CascadeType.ALL)
    private CardFunds cardFunds;

    //not including cascade remove type, because customer is not deleted when card deletes
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "ch_id", referencedColumnName = "ch_id", foreignKey = @ForeignKey(name = "FK_customer"))
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Customer customer;

    @Column(name="created_on", nullable = false)
    @CreationTimestamp
    private LocalDateTime createdOn;

    @Column(name="last_updated_on")
    @UpdateTimestamp
    private LocalDateTime lastUpdatedOn;
}
