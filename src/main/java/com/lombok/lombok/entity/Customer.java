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

    @Column(name = "first_name", length = 45)
    private String firstName;

    @Column(name = "last_name", length = 45)
    private String lastName;

    @Column(name = "cnic", length = 45, nullable = false)
    private String cnic;

    @Column(name = "phone", length = 45)
    private String phoneNumber;

    @Column(name = "age")
    private Integer age;

    @Column(name = "postal_code")
    private String postalCode;

    @Column(name = "address")
    private String address;

    @Column(name = "email")
    private String email;

    @Column(name = "gender")
    private String gender;

    @Check(constraints = "is_active IN ('A', 'B', 'F)")
    @ColumnDefault("'A'")
    @Column(name = "status", length = 1, nullable = false)
    @Builder.Default
    private String status = Constants.CARD_PRE_ACTIVE_STATUS;

    @Check(constraints = "is_active IN ('Y', 'N')")
    @ColumnDefault("'Y'")
    @Column(name = "is_active", length = 1, nullable = false,
            columnDefinition = "CHAR(1) DEFAULT 'Y'")
    @Builder.Default
    private String isActive = Constants.IS_ACTIVE;

    @OneToOne(mappedBy = "customer", cascade = CascadeType.ALL)
    private User user;

    @Column(name="created_on", nullable = false)
    @CreationTimestamp
    private LocalDateTime createdOn;

    @Column(name="last_updated_on")
    @UpdateTimestamp
    private LocalDateTime lastUpdatedOn;

}
