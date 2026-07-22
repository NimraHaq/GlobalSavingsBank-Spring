package com.vault.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@AllArgsConstructor
@NoArgsConstructor
@Data //included Getter, Setter, ReqArgsConstructor, ToString, Equals and HashCode
@Builder

@Entity
@Table(name = "transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "trans_id")
    private Long transId;


    @Column(name = "service_id", length = 20, nullable = false)
    private String serviceId;

    @Column(name = "service_charges")
    @Builder.Default
    private BigDecimal serviceCharges = BigDecimal.ZERO;

    @Column(name = "amount_processed", precision = 19, scale = 2)
    private BigDecimal amountProcessed;

    @ManyToOne(cascade = {CascadeType.MERGE,CascadeType.REFRESH, CascadeType.DETACH}, fetch = FetchType.EAGER)
    @JoinColumn(name = "card_no", referencedColumnName = "card_no", foreignKey = @ForeignKey(name = "FK_transactions_card"), nullable = false)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Card card;

    @Column(name="created_on", nullable = false)
    @CreationTimestamp
    private LocalDateTime createdOn;

    @Column(name="last_updated_on")
    @UpdateTimestamp
    private LocalDateTime lastUpdatedOn;
}
