package org.simarro.controller;

import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.simarro.model.Usuario;
import org.simarro.model.dto.UsuarioRequestDto;
import org.simarro.model.dto.UsuarioResponseDto;
import org.simarro.service.IUsuarioSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private IUsuarioSevice service;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<UsuarioResponseDto>> listar() {
        List<UsuarioResponseDto> lista = service.listar();

        // Código 200 OK para select
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponseDto> listarPorId(@PathVariable("id") Integer id) {
        UsuarioResponseDto obj = service.listarPorId(id);

        if (obj == null) {
            // Código 404 No encontrado
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            // Código 200 OK para select
            return new ResponseEntity<>(obj, HttpStatus.OK);
        }
    }

    @PostMapping
    public ResponseEntity<Usuario> registrar(@Valid @RequestBody UsuarioRequestDto usuarioRequestDto) {
        Usuario obj = service.registrar(usuarioRequestDto);

        // Código 201 CREATED para insert
        return new ResponseEntity<>(obj, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> modificar(@PathVariable Integer id, @Valid @RequestBody UsuarioRequestDto usuarioRequestDto) {
        Usuario obj = service.modificar(id, usuarioRequestDto);

        if (obj == null)
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

        // Código 200 OK para update
        return new ResponseEntity<>(obj, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable("id") Integer id) {
        service.eliminar(id);

        // Código 204 NOT CONTENT para delete
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
