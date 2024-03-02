package org.simarro.service;

import org.simarro.model.Habitacion;
import org.simarro.repository.IHabitacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HabitacionServiceImpl implements IHabitacionService {

    @Autowired
    private IHabitacionRepository repo;

    @Autowired
    private IHotelService hotelService;

    @Override
    public Habitacion registrar(Habitacion habitacion) throws Exception {

        // Verificar si el hotel asociado existe
        Integer hotelId = habitacion.getHotel().getId();

        if (hotelId == null || hotelService.listarPorId(hotelId) == null) {
            // System.out.println("El hotel con el ID " + hotelId + " no existe.");
            throw new Exception("El hotel con el ID " + hotelId + " no existe.");
        }

        // Si el hotel existe, guardar la habitación
        return repo.save(habitacion);
    }

    @Override
    public Habitacion modificar(Habitacion habitacion) {
        return repo.save(habitacion);
    }

    @Override
    public List<Habitacion> listar() {
        return repo.findAll();
    }

    @Override
    public Habitacion listarPorId(Integer id) {
        Optional<Habitacion> op = repo.findById(id);
        return op.isPresent() ? op.get() : null;
        //return op.orElseGet(Habitacion::null);
    }

    @Override
    public void eliminar(Integer id) {
        repo.deleteById(id);
    }

    // Métodos específicos de esta entidad
    @Override
    public Habitacion modificarOcupacion(Integer id) {

        Habitacion obj = listarPorId(id);

        if (obj != null) {
            if(obj.isOcupada()){
                obj.setOcupada(false);
            }else{
                obj.setOcupada(true);
            }
            obj = modificar(obj);
        }

        return obj;
    }

    @Override
    public List<Habitacion> listarHabitaciones(Integer idHotel) {
        return repo.listarHabitaciones(idHotel);
    }

    @Override
    public List<Habitacion> listarHabitacionesLibres(Integer idHotel) {
        return repo.listarHabitacionesLibres(idHotel);
    }
}
