package org.simarro.controller;

import org.simarro.model.Hotel;
import org.simarro.service.IHotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hoteles")
public class HotelController {

    @Autowired
    private IHotelService service;

    @GetMapping
    public ResponseEntity<List<Hotel>> listar() {
        List<Hotel> lista = service.listar();

        // Código 200 OK para select
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Hotel> registrar(@RequestBody Hotel hotel) {
        Hotel obj = service.registrar(hotel);

        // Código 201 CREATED para insert
        return new ResponseEntity<>(obj, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Hotel> modificar(@RequestBody Hotel hotel) {
        Hotel obj = service.modificar(hotel);

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
    @GetMapping("/buscar")
    public ResponseEntity<List<Hotel>> buscar(@RequestParam(required = false) String localidad,
                                              @RequestParam(required = false) String categoria) {

        List<Hotel> vuelos = service.listarPorLocalidadCategoria(localidad, categoria);

        // Código 200 OK para select
        return new ResponseEntity<>(vuelos, HttpStatus.OK);
    }
}
