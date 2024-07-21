package com.enigma.loan.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "t_loan_document")
public class LoanDocument {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "content_type_id")
    private String contentType;

    private String name;

    private String path;

    private Long size;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
}
