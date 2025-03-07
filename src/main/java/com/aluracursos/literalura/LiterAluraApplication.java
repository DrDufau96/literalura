package com.aluracursos.literalura;

import com.aluracursos.literalura.app.App;
import com.aluracursos.literalura.domain.autor.AutorRepository;
import com.aluracursos.literalura.domain.libro.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LiterAluraApplication implements CommandLineRunner {
    @Autowired
    private LibroRepository libroRepository;
    @Autowired
    private AutorRepository autorRepository;

    public static void main(String[] args) {
        SpringApplication.run(LiterAluraApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        App app = new App(libroRepository, autorRepository);
        app.start();

    }
}