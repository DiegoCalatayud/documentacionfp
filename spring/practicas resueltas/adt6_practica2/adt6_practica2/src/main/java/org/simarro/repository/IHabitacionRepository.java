package org.simarro.repository;

import org.simarro.model.Habitacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IHabitacionRepository extends JpaRepository<Habitacion, Integer> {

    @Query("FROM Habitacion h WHERE h.hotel.id = :idHotel")
    List<Habitacion> listarHabitaciones(@Param("idHotel") Integer idHotel);

    @Query("FROM Habitacion h WHERE h.hotel.id = :idHotel AND h.ocupada = false")
    List<Habitacion> listarHabitacionesLibres(@Param("idHotel") Integer id);
}
