package com.aluracursos.literalura.domain.register;

import com.aluracursos.literalura.domain.libro.Libro;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Registro {
    private List<Libro> libros;


}
