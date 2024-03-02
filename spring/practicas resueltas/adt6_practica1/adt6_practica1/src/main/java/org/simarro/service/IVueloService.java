package org.simarro.service;

import org.simarro.model.Vuelo;

import java.util.List;

public interface IVueloService {

    List<Vuelo> listar();
    Vuelo registrar(Vuelo vuelo);
    Vuelo modificar(Vuelo vuelo);
    void eliminar(Integer id);

    // Métodos específicos de esta entidad
    void eliminarPorDestino(String destino);
    boolean existsByDestino(String destino);
    List<Vuelo> findByOrigen(String origen);
    List<Vuelo> findByDestino(String destino);
    List<Vuelo> findByNumEscalas(Integer numeroEscalas);
    List<Vuelo> findByOrigenAndDestinoAndNumeroEscalas(String origen, String destino, Integer numeroEscalas);
    List<Vuelo> findVuelos(String origen, String destino, Integer numEscalasMin,
                           Integer numEscalasMax, Float precioMin, Float precioMax);

}
