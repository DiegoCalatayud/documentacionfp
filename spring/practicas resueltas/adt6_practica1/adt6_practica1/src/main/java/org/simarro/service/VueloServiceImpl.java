package org.simarro.service;

import jakarta.transaction.Transactional;
import org.simarro.model.Vuelo;
import org.simarro.repository.IVueloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VueloServiceImpl implements IVueloService {

    @Autowired
    private IVueloRepository repo;

    @Override
    public Vuelo registrar(Vuelo vuelo) {
        return repo.save(vuelo);
    }

    @Override
    public Vuelo modificar(Vuelo vuelo) {
        return repo.save(vuelo);
    }

    @Override
    public List<Vuelo> listar() {
        return repo.findAll();
    }

    @Override
    public void eliminar(Integer id) {
        repo.deleteById(id);
    }

    // Métodos específicos de esta entidad

    @Override
    @Transactional
    public void eliminarPorDestino(String destino) {
        //Opción 1 - Eliminar
        repo.eliminarPorDestino(destino);

        //Opción 2 - Eliminar
        // repo.deleteByDestino(destino);
    }

    @Override
    public boolean existsByDestino(String destino) {
        // Opción 1 - ExisteDestino
        return repo.existsByDestino(destino);

        // Opción 2 - ExisteDestino
        // return repo.existePorDestino(destino);

    }

    @Override
    public List<Vuelo> findByOrigen(String origen) {
        return repo.findByOrigen(origen);
    }

    @Override
    public List<Vuelo> findByDestino(String destino) {
        return repo.findByDestino(destino);
    }

    @Override
    public List<Vuelo> findByNumEscalas(Integer numeroEscalas) {
        return repo.findByNumEscalas(numeroEscalas);
    }

    @Override
    public List<Vuelo> findByOrigenAndDestinoAndNumeroEscalas(String origen, String destino, Integer numeroEscalas) {
        if (origen != null && destino != null && numeroEscalas != null) {
            return repo.findByOrigenAndDestinoAndNumEscalas(origen, destino, numeroEscalas);
        } else if (origen != null && destino != null) {
            return repo.findByOrigenAndDestino(origen, destino);
        } else if (origen != null && numeroEscalas != null) {
            return repo.findByOrigenAndNumEscalas(origen, numeroEscalas);
        } else if (destino != null && numeroEscalas != null) {
            return repo.findByDestinoAndNumEscalas(destino, numeroEscalas);
        } else if (origen != null) {
            return repo.findByOrigen(origen);
        } else if (destino != null) {
            return repo.findByDestino(destino);
        } else if (numeroEscalas != null) {
            return repo.findByNumEscalas(numeroEscalas);
        } else {
            return repo.findAll();
        }
    }

    @Override
    public List<Vuelo> findVuelos(String origen, String destino, Integer numEscalasMin, Integer numEscalasMax, Float precioMin, Float precioMax) {
        return repo.findVuelos(origen, destino, numEscalasMin, numEscalasMax, precioMin, precioMax);    }

}
