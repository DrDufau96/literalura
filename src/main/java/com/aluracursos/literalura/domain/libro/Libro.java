package com.aluracursos.literalura.domain.libro;

import com.aluracursos.literalura.domain.autor.Autor;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "libros")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String titulo;
    @ManyToOne()
    private Autor autor;
    private Idioma idioma;
    private int numeroDescargas;

    public Libro(Libro datos, Autor autor) {
        this.titulo = datos.titulo;
        setAutor(autor);
        this.idioma = datos.idioma;
        this.numeroDescargas = datos.numeroDescargas;
    }


    public Libro(DatosLibro datos) {
        this.titulo = datos.titulo();
        this.autor = new Autor(datos.autores());
        this.idioma = Idioma.fromString(datos.idiomas().getFirst());
        this.numeroDescargas = datos.numeroDescargas();
    }


    @Override
    public String toString() {
        return "Libro{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", autor=" + autor +
                ", idioma=" + idioma +
                ", numero de descargas=" + numeroDescargas +
                '}';
    }


}