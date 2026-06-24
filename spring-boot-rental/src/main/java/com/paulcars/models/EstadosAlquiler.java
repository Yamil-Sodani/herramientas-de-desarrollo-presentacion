package com.paulcars.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "estadosalquiler")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EstadosAlquiler {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idEstadoAlquiler;

    @Column(nullable = false, length = 30)
    private String nombreEstado;
}
