package com.paulcars.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tiposvehiculo")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TiposVehiculo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idTipo;

    @Column(nullable = false, length = 50)
    private String nombreTipoVehiculo;
}
