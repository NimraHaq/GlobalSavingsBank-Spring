package com.vault.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Entity
@Table(name = "card_funds")
public class CardFunds {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "ledger_balance")
    private double ledgerBalance;

    @Column(name = "card_balance")
    private double cardBalance;

    @Column(name = "card_blnc_onhold")
    @Builder.Default
    private double cardBalanceOnHold = 0d;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "card_srno", referencedColumnName = "card_srno", foreignKey = @ForeignKey(name = "FK_cards"))
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Card card;

    @CreationTimestamp
    private LocalDateTime createdOn;

    @UpdateTimestamp
    private LocalDateTime updationTimeStamp;
}
