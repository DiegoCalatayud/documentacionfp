package org.simarro.service;

import org.simarro.model.Habitacion;

import java.util.List;

public interface IHabitacionService {

    List<Habitacion> listar();
    Habitacion listarPorId(Integer id);
    Habitacion registrar(Habitacion habitacion) throws Exception;
    Habitacion modificar(Habitacion habitacion);
    void eliminar(Integer id);

    // Métodos específicos de esta entidad
    Habitacion modificarOcupacion(Integer id);

    List<Habitacion> listarHabitaciones(Integer idHotel);

    List<Habitacion> listarHabitacionesLibres(Integer idHotel);

}
