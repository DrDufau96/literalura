package com.aluracursos.literalura.domain.libro;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LibrosRepository extends JpaRepository<Libro, Long> {

    Optional<Libro> findByTituloIgnoreCase(String tituloLibro);
}
