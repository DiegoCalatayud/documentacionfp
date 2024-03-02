package org.simarro.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Habitacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private int tamano;

    @Column
    private float precio;

    @Column
    private boolean desayuno;

    @Column
    private boolean ocupada;

    @ManyToOne
    @JoinColumn(name = "id_hotel", nullable = false, foreignKey = @ForeignKey(name = "FK_habitacion_hotel"))
    private Hotel hotel;

}
