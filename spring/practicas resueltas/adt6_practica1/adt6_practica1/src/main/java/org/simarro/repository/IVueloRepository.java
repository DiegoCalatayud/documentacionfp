package org.simarro.repository;

import org.simarro.model.Vuelo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IVueloRepository extends JpaRepository<Vuelo, Integer> {

    // Métodos específicos de esta entidad mediante JPQL
    //Opción 1 - Eliminar
    @Modifying
    @Query(value = "DELETE FROM vuelo WHERE destino LIKE %:destino%", nativeQuery = true)
    void eliminarPorDestino(@Param("destino") String destino);
    // Opción 2 - Eliminar
    void deleteByDestino(String destino);

    List<Vuelo> findByOrigenAndDestinoAndNumEscalas(String origen, String destino, Integer numeroEscalas);
    List<Vuelo> findByOrigenAndDestino(String origen, String destino);
    List<Vuelo> findByOrigenAndNumEscalas(String origen, Integer numeroEscalas);
    List<Vuelo> findByDestinoAndNumEscalas(String destino, Integer numeroEscalas);
    List<Vuelo> findByOrigen(String origen);
    List<Vuelo> findByDestino(String destino);
    List<Vuelo> findByNumEscalas(Integer numeroEscalas);

    // Opción 1 - ExisteDestino
    @Query("SELECT CASE WHEN COUNT(v) > 0 THEN 'true' ELSE 'false' END FROM Vuelo v WHERE v.destino = :destino")
    boolean existePorDestino(@Param("destino") String destino);

    // Opción 2 - ExisteDestino
    boolean existsByDestino(String destino);

    // Búsqueda por cualquier parámetro
    @Query("SELECT v FROM Vuelo v WHERE " +
            "(:origen IS NULL OR v.origen = :origen OR :origen = '') AND " +
            "(:destino IS NULL OR v.destino = :destino OR :destino = '') AND " +
            "(:numEscalasMin IS NULL OR v.numEscalas >= :numEscalasMin) AND " +
            "(:numEscalasMax IS NULL OR v.numEscalas <= :numEscalasMax) AND " +
            "(:precioMin IS NULL OR v.precio >= :precioMin) AND " +
            "(:precioMax IS NULL OR v.precio <= :precioMax)")
    List<Vuelo> findVuelos(
            @Param("origen") String origen,
            @Param("destino") String destino,
            @Param("numEscalasMin") Integer numEscalasMin,
            @Param("numEscalasMax") Integer numEscalasMax,
            @Param("precioMin") Float precioMin,
            @Param("precioMax") Float precioMax
    );

}
