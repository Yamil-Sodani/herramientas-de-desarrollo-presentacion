package com.paulcars.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "combustibles")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Combustibles {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCombustible;

    @Column(nullable = false, length = 30)
    private String nombreCombustible;
}
