package com.enigma.loan.model.entity;

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
public class LoanDocument {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column(name = "content_type")
    private String contentType;
    private String name;
    private String path;
    private Long size;
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
}
