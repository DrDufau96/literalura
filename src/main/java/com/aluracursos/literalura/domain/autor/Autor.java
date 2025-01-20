package com.aluracursos.literalura.domain.autor;

import com.aluracursos.literalura.domain.libro.Libro;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "autores")
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String nombre;
    private Integer nacimiento;
    private Integer fallecimiento;

    @Column(unique = true)
    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Libro> libro = new ArrayList<>();



    public Autor(List<DatosAutor> datos) {
        this.nombre = datos.getFirst().nombre();
        this.nacimiento = datos.getFirst().nacimiento();
        this.fallecimiento = datos.getFirst().fallecimiento();
    }


    public Autor(Autor datos) {
        this.nombre = datos.nombre;
        this.nacimiento = datos.nacimiento;
        this.fallecimiento = datos.fallecimiento;
    }
}