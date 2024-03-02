package org.simarro.controller;

import org.simarro.model.Vuelo;
import org.simarro.service.IVueloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/vuelos")
public class VueloController {

    @Autowired
    private IVueloService service;

    @GetMapping
    public ResponseEntity<List<Vuelo>> listar() {
        List<Vuelo> lista = service.listar();

        // Código 200 OK para select
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Vuelo> registrar(@RequestBody Vuelo vuelo) {
        Vuelo obj = service.registrar(vuelo);

        // Código 201 CREATED para insert
        return new ResponseEntity<>(obj, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Vuelo> modificar(@RequestBody Vuelo vuelo) {
        Vuelo obj = service.modificar(vuelo);

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
    @DeleteMapping("/eliminardestino")
    public ResponseEntity<Void> eliminarPorDestino(@RequestParam(value = "destino") String destino) {

        if (!service.existsByDestino(destino)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        service.eliminarPorDestino(destino);

        // Código 204 NOT CONTENT para delete
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Opción 1 - Búsqueda filtrada
    @GetMapping("/busquedafiltrada1")
    public ResponseEntity<List<Vuelo>> listarTodasCondiciones(
            @RequestParam(value = "origen", required = true) String origen,
            @RequestParam(value = "destino", required = true) String destino,
            @RequestParam(value = "escalas", required = true) Integer escalas) {

        // Obtengo todos los vuelos
        List<Vuelo> todos = service.listar();

        List<Vuelo> resultados = new ArrayList<>();

        for (Vuelo flight : todos) {
            if (flight.getOrigen().toLowerCase().equals(origen) &&
                    flight.getDestino().toLowerCase().equals(destino) &&
                    flight.getNumEscalas() == escalas) {

                resultados.add(flight);
            }
        }

        // Código 200 OK para select
        return new ResponseEntity<>(resultados, HttpStatus.OK);
    }

    // Opción 2 - Búsqueda filtrada
    @GetMapping("/busquedafiltrada2")
    public ResponseEntity<List<Vuelo>> listarParametros(
            @RequestParam(value = "origen", required = false) String origen,
            @RequestParam(value = "destino", required = false) String destino,
            @RequestParam(value = "escalas", required = false) Integer escalas) {

        List<Vuelo> lista = null;

        if (origen != null) {
            lista = service.findByOrigen(origen);
        } else {
            if (destino != null) {
                lista = service.findByDestino(destino);
            } else {
                lista = service.findByNumEscalas(escalas);
            }
        }

        // Código 200 OK para select
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }

    // Opción 3 - Búsqueda filtrada
    @GetMapping("/busquedafiltrada3")
    public ResponseEntity<List<Vuelo>> buscarVuelos(
            @RequestParam(required = false) String origen,
            @RequestParam(required = false) String destino,
            @RequestParam(required = false) Integer numeroEscalas) {

        List<Vuelo> vuelos = service.findByOrigenAndDestinoAndNumeroEscalas(origen, destino, numeroEscalas);

        return new ResponseEntity<>(vuelos, HttpStatus.OK);
    }

    // Búsqueda por cualquier parámetro
    @GetMapping("/busquedafiltrada4")
    public ResponseEntity<List<Vuelo>> searchVuelo(
            @RequestParam(value = "origen", required = false) String origen,
            @RequestParam(value = "destino", required = false) String destino,
            @RequestParam(value = "numEscalasMin", required = false, defaultValue = "0") Integer numEscalasMin,
            @RequestParam(value = "numEscalasMax", required = false, defaultValue = "999") Integer numEscalasMax,
            @RequestParam(value = "precioMin", required = false, defaultValue = "0") Float precioMin,
            @RequestParam(value = "precioMax", required = false, defaultValue = "999999") Float precioMax) {

        List<Vuelo> vuelos = service.findVuelos(origen, destino, numEscalasMin, numEscalasMax, precioMin, precioMax);

        return new ResponseEntity<>(vuelos, HttpStatus.OK);
    }
}
