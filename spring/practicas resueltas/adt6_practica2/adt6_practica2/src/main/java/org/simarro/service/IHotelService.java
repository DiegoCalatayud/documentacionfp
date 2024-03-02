package org.simarro.service;

import org.simarro.model.Habitacion;
import org.simarro.model.Hotel;

import java.util.List;

public interface IHotelService {

    List<Hotel> listar();
    Hotel listarPorId(Integer id);
    Hotel registrar(Hotel hotel);
    Hotel modificar(Hotel hotel);
    void eliminar(Integer id);

    // Métodos específicos de esta entidad
    List<Hotel> listarPorLocalidadCategoria(String localidad, String categoria);
}
