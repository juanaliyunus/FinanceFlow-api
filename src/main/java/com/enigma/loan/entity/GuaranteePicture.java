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
@Table(name = "t_guarantee_picture")
public class GuaranteePicture {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String contentType;

    private String name;

    private String path;

    private Long size;
}
