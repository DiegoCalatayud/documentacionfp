package org.simarro.service;

import org.simarro.model.Hotel;
import org.simarro.repository.IHotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HotelServiceImpl implements IHotelService {

    @Autowired
    private IHotelRepository repo;

    @Override
    public Hotel registrar(Hotel hotel) {
        return repo.save(hotel);
    }

    @Override
    public Hotel modificar(Hotel hotel) {
        return repo.save(hotel);
    }

    @Override
    public List<Hotel> listar() {
        return repo.findAll();
    }

    @Override
    public Hotel listarPorId(Integer id) {
        Optional<Hotel> op = repo.findById(id);
        return op.isPresent() ? op.get() : null;
        //return op.orElseGet(Hotel::null);
    }

    @Override
    public void eliminar(Integer id) {
        repo.deleteById(id);
    }

    // Métodos específicos de esta entidad
    @Override
    public List<Hotel> listarPorLocalidadCategoria(String localidad, String categoria) {

        if (localidad != null && categoria != null) {
            return repo.findByLocalidadAndCategoria(localidad, categoria);
        } else if (localidad != null) {
            return repo.findByLocalidad(localidad);
        } else if (categoria != null) {
            return repo.findByCategoria(categoria);
        } else {
            // Si no pongo ningún parámetros en la petición
            return repo.findAll();
        }
    }
}
