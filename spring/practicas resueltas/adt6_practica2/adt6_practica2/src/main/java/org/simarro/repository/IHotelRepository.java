package org.simarro.repository;

import org.simarro.model.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IHotelRepository extends JpaRepository<Hotel, Integer> {

    // Métodos específicos de esta entidad mediante JPQL
    List<Hotel> findByLocalidad(String localidad);

    List<Hotel> findByCategoria(String categoria);

    List<Hotel> findByLocalidadAndCategoria(String localidad, String categoria);
}
