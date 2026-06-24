package com.paulcars.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@Entity
@Table(name = "vehiculos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Vehiculo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idVehiculo;

    @Column(nullable = false, length = 15)
    private String placa;

    @Column(nullable = false, length = 30)
    private String color;

    @Column(nullable = false)
    private Integer anio;

    @Column(nullable = false, length = 50)
    private String numeroMotor;

    @Column(nullable = false, length = 17)
    private String numeroVin;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal precioDia;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal precioHora;

    @ManyToOne
    @JoinColumn(name = "id_modelo", nullable = false)
    private Modelo modelo;

    @ManyToOne
    @JoinColumn(name = "id_tipo", nullable = false)
    private TiposVehiculo tipo;

    @ManyToOne
    @JoinColumn(name = "id_combustible", nullable = false)
    private Combustibles combustible;

    @ManyToOne
    @JoinColumn(name = "id_estado", nullable = false)
    private EstadosVehiculo estado;
}
