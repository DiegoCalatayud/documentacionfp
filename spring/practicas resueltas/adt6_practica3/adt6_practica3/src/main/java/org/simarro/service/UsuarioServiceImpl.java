package org.simarro.service;

import org.modelmapper.ModelMapper;
import org.simarro.model.Usuario;
import org.simarro.model.dto.UsuarioRequestDto;
import org.simarro.model.dto.UsuarioResponseDto;
import org.simarro.repository.IUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsuarioServiceImpl implements IUsuarioSevice {

    @Autowired
    private IUsuarioRepository repo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public UsuarioResponseDto listarPorId(Integer id) {
        Optional<Usuario> op = repo.findById(id);

        if (op.isPresent()) {
            return modelMapper.map(op.get(), UsuarioResponseDto.class);
        } else {
            return null;
        }
        //return op.isPresent() ? op.get() : new Usuario();
    }

    @Override
    public List<UsuarioResponseDto> listar() {

        return repo.findAll().stream().map(usu -> modelMapper.map(usu, UsuarioResponseDto.class)).collect(Collectors.toList());

        /*

        // Opción 1
        // 1. Obtener la lista de entidades desde el servicio
        List<Usuario> listaUsuarios = service.listar();

        // 2. Crear un Stream a partir de la lista de entidades
        Stream<Usuario> streamUsuarios = listaUsuarios.stream();

        // 3. Transformar cada entidad en un DTO usando modelMapper
        Stream<UsuarioResponseDto> streamUsuariosDto = streamUsuarios.map(entidad -> modelMapper.map(entidad, UsuarioResponseDto.class));

        // 4. Recolectar los DTOs en una lista
        List<UsuarioResponseDto> listaUsuariosResponseDto = streamUsuariosDto.collect(Collectors.toList());

        // Opción 2
        // 1. Obtener la lista de entidades desde el servicio
        List<Usuario> listaUsuarios = service.listar();

        // 2. Crear una nueva lista vacía para los DTOs
        List<UsuarioResponseDto> listaUsuariosResponseDto = new ArrayList<>();

        // 3. Iterar sobre cada entidad en la lista de entidades
        for (Usuario usu : listaUsuarios) {
            // 4. Mapear la entidad a un DTO usando modelMapper
            UsuarioResponseDto dto = modelMapper.map(usu, UsuarioResponseDto.class);

            // 5. Agregar el DTO mapeado a la lista de DTOs
            listaUsuariosResponseDto.add(dto);
        }

        */
    }

    @Override
    public Usuario registrar(UsuarioRequestDto dtoRequest) {

        Usuario usu = modelMapper.map(dtoRequest, Usuario.class);
        usu.setFechaCreacion(LocalDate.now());

        return repo.save(usu);
    }

    @Override
    public Usuario modificar(Integer id, UsuarioRequestDto dtoRequest) {
        Optional<Usuario> op = repo.findById(id);

        if (op.isPresent()) {

            Usuario usuMod = op.get();
            usuMod.setNombre(dtoRequest.getNombre());
            usuMod.setApellidos(dtoRequest.getApellidos());
            usuMod.setEmail(dtoRequest.getEmail());
            usuMod.setPassword(dtoRequest.getPassword());

            return repo.save(usuMod);
        } else {
            return null;
        }
    }

    @Override
    public void eliminar(Integer id) {
        repo.deleteById(id);
    }
}
