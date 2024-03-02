package org.simarro.service;

import org.simarro.model.Usuario;
import org.simarro.model.dto.UsuarioRequestDto;
import org.simarro.model.dto.UsuarioResponseDto;

import java.util.List;

public interface IUsuarioSevice {

    UsuarioResponseDto listarPorId(Integer id);

    List<UsuarioResponseDto> listar();

    Usuario registrar(UsuarioRequestDto usuario);

    Usuario modificar(Integer id, UsuarioRequestDto usuario);

    void eliminar(Integer id);
}
