package com.paulcars.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tiposdocumento")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TiposDocumento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idTipoDoc;

    @Column(nullable = false, length = 50)
    private String nombreTipoDoc;
}
