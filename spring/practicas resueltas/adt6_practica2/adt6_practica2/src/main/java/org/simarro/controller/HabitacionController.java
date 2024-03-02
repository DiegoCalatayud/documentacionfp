package org.simarro.controller;

import org.simarro.model.Habitacion;
import org.simarro.service.IHabitacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/habitaciones")
public class HabitacionController {

    @Autowired
    private IHabitacionService service;

    @GetMapping
    public ResponseEntity<List<Habitacion>> listar() {
        List<Habitacion> lista = service.listar();

        // Código 200 OK para select
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Habitacion> registrar(@RequestBody Habitacion habitacion) {

        try {
            Habitacion obj = service.registrar(habitacion);

            // Código 201 CREATED para insert
            return new ResponseEntity<>(obj, HttpStatus.CREATED);
        } catch (Exception e) {

            // Código 400 no existe el hotel de esa habitación
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

    }

    @PutMapping
    public ResponseEntity<Habitacion> modificar(@RequestBody Habitacion habitacion) {
        Habitacion obj = service.modificar(habitacion);

        // Código 200 OK para update
        return new ResponseEntity<>(obj, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable("id") Integer id) {
        service.eliminar(id);

        // Código 204 NOT CONTENT para delete
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /////////////////////////////////////////
    // Métodos específicos de esta entidad //
    /////////////////////////////////////////
    @GetMapping("/{idHotel}")
    public ResponseEntity<List<Habitacion>> listarPorHotel(@PathVariable("idHotel") Integer idHotel) {

        List<Habitacion> lista = service.listarHabitaciones(idHotel);

        // Código 200 OK para select
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }

    @GetMapping("/{idHotel}/libres")
    public ResponseEntity<List<Habitacion>> listarPorHotelLibres(@PathVariable("idHotel") Integer idHotel) {

        List<Habitacion> lista = service.listarHabitacionesLibres(idHotel);

        // Código 200 OK para select
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }

    @PutMapping("/{id}/ocupada")
    public ResponseEntity<Habitacion> modificarOcupacion(@PathVariable Integer id) {
        Habitacion obj = service.modificarOcupacion(id);

        if (obj == null) {
            // Código 404 No encontrado
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            // Código 200 OK para update
            return new ResponseEntity<>(obj, HttpStatus.OK);
        }
    }
}
