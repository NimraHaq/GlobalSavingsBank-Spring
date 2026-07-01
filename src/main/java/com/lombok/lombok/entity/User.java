package com.lombok.lombok.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Check;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.engine.internal.Cascade;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private int id;

    @Column(name="username", nullable = false, length = 20, unique = true)
    private String username;

    @Column(name="password", length = 20)
    private String password;

    @Column(name="role", nullable = false)
    private String role;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Check(constraints = "is_active IN ('Y', 'N')")
    @ColumnDefault("'Y'")
    @Column(name = "is_active", length = 1, nullable = false,
            columnDefinition = "CHAR(1) DEFAULT 'Y'")
    private String isActive = "Y";

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="ch_id")
    private Customer customer;

    @CreationTimestamp
    @Column(name="created_on")
    private LocalDateTime createdOn;

    @UpdateTimestamp
    @Column(name="last_updated_on", nullable = true)
    private LocalDateTime lastUpdatedOn;

}
