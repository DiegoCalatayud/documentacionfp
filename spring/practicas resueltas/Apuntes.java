public class Apuntes {
    /*
<dependency>
<groupId>org.modelmapper</groupId>
<artifactId>modelmapper</artifactId>
<version>3.0.0</version>
</dependency>
<dependency>
<groupId>org.springframework.boot</groupId>
<artifactId>spring-boot-starter-validation</artifactId>
</dependency>

    Estructura:
        controller [UsuarioController]
        model [Usuario]
            dto[UsuarioRequestDto, UsuarioResponseDto]
        repository [(i)IUsuarioRepository]
        service [(i)IUsuarioService,UsuarioServiceImpl]
        utils [ModelMapperConfig]


        spring.jpa.show-sql=true
        spring.jpa.hibernate.ddl-auto=update

        spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
        spring.datasource.url=jdbc:mysql://localhost/adt6_practica3
        spring.datasource.username=root
        spring.datasource.password=

        # spring.sql.init.mode=always

    data.sql
        CREATE TABLE IF NOT EXISTS producto (
            id INT AUTO_INCREMENT PRIMARY KEY,
            nombre VARCHAR(255),
            descripcion TEXT,
            categoria VARCHAR(50),
            precio DECIMAL(10, 2),
            fecha_creacion DATE
        );

        INSERT INTO producto (nombre, descripcion, categoria, precio, fecha_creacion)
        VALUES('Monitor', 'DELL S Series 27 pulgadas', 'Tecnología', 216.10, '2022/12/18');
    UsuarioController
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

    Usuario
        @Data
        @AllArgsConstructor
        @NoArgsConstructor
        @Entity
        public class Usuario {

            @Id
            @GeneratedValue(strategy = GenerationType.IDENTITY)
            private Integer id;

            @Column
            private String nombre;

            @Column
            private String apellidos;

            @Column
            private String email;

            @Column
            private String password;

            @Column(name = "fecha_creacion")
            private LocalDate fechaCreacion;
        }
    relacion one to many
        @OneToMany(mappedBy = "seguro", cascade = CascadeType.ALL)
        private List<AsistenciaMedica> asistenciasMedicas;

        @ManyToOne(cascade = CascadeType.ALL)
        @JoinColumn(name="id_seguro")
        private Seguro seguro;


    IXXXRepository
        @Repository
        public interface IHotelRepository extends JpaRepository<Hotel, Integer> {
            @Query("FROM Habitacion h WHERE h.hotel.id = :idHotel")
            List<Habitacion> listarHabitaciones(@Param("idHotel") Integer idHotel);

            @Query("FROM Habitacion h WHERE h.hotel.id = :idHotel AND h.ocupada = false")
            List<Habitacion> listarHabitacionesLibres(@Param("idHotel") Integer id);
            // Métodos específicos de esta entidad mediante JPQL
            List<Hotel> findByLocalidad(String localidad);

            List<Hotel> findByCategoria(String categoria);

            List<Hotel> findByLocalidadAndCategoria(String localidad, String categoria);
        }

    IusuarioService
        public interface IUsuarioSevice {

            UsuarioResponseDto listarPorId(Integer id);

            List<UsuarioResponseDto> listar();

            Usuario registrar(UsuarioRequestDto usuario);

            Usuario modificar(Integer id, UsuarioRequestDto usuario);

            void eliminar(Integer id);
        }

    IusuarioServiceImpl
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

                [[[[[]]]]]

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

                [[[[[]]]]]

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


    UsuarioRequestDto
        @Data
        @AllArgsConstructor
        @NoArgsConstructor
        public class UsuarioRequestDto {

            @NotBlank
            private String nombre;

            @Size(min = 6, max = 50, message = "Apellidos debe tener entre 6 y 50.")
            private String apellidos;

            @Email
            private String email;

            @Pattern(regexp = "^[A-Za-z0-9]{4,8}$")
            private String password;
        }
    ModelMapperConfig
        @Configuration
        public class ModelMapperConfig {

            @Bean
            public ModelMapper modelMapper() {
                return new ModelMapper();
            }
        }
    UsuarioResponseDto
        @Data
        @AllArgsConstructor
        @NoArgsConstructor
        public class UsuarioResponseDto {

            private String nombre;
            private String email;
            @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
            private LocalDate fechaCreacion;

        }

        List<Producto> productosFiltrados = op.stream()
                .filter(p -> p.getDescripcion().length() > 3  )
                .sorted(Comparator.comparing(Producto::getNombre) // Ordenar por nombre ascendente
                        .thenComparing(Comparator.comparing(Producto::getPrecio).reversed()))
                .collect(Collectors.toList());


    @Size(min = 6, max = 50, message = "Los apellidos han de tener entre 6 y 50 caracteres.")
    @Pattern(regexp = "^(?=.*[A-Za-z0-9]).{4,8}$")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    @Column(nullable = false)
    @Column(name = "fecha_creacion")




        try {//en el controller
            Habitacion obj = service.registrar(habitacion);

            // Código 201 CREATED para insert
            return new ResponseEntity<>(obj, HttpStatus.CREATED);
        } catch (Exception e) {

            // Código 400 no existe el hotel de esa habitación
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        //Interface IHabitacionController
        Habitacion registrar(Habitacion habitacion) throws Exception;

        //HabitacionServiceImpl
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
     */
}
