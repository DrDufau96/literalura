package com.aluracursos.literalura.domain.libro;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LibroRepository extends JpaRepository<Libro, Long> {

       Optional<Libro> findByTituloContainingIgnoreCase(String titulo);

       List<Libro> findByIdioma(Idioma idioma);

}
