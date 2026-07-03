package com.lombok.lombok.entity;

import com.lombok.lombok.utils.Constants;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Check;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

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
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private User user;

    //if a customer is deleted, all cards must be deleted, so its a cascade delete on customer side
    //but if a card is deleted, customer is not deleted, so we remove cascade-Remove from card side
    @OneToMany(mappedBy = "customer", cascade =  {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.MERGE,
            CascadeType.REFRESH, CascadeType.REMOVE})
    private List<Card> cards;


    @Column(name="created_on", nullable = false)
    @CreationTimestamp
    private LocalDateTime createdOn;

    @Column(name="last_updated_on", nullable = true)
    @UpdateTimestamp
    private LocalDateTime lastUpdatedOn;

}
