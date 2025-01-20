package com.aluracursos.literalura.app;

import com.aluracursos.literalura.domain.Datos;
import com.aluracursos.literalura.domain.autor.Autor;
import com.aluracursos.literalura.domain.autor.AutorRepository;
import com.aluracursos.literalura.domain.libro.Idioma;
import com.aluracursos.literalura.domain.libro.Libro;
import com.aluracursos.literalura.domain.libro.LibroRepository;
import com.aluracursos.literalura.service.ConsumoApi;
import com.aluracursos.literalura.service.ConvierteDatos;

import java.util.*;

public class App {
    private final Scanner scanner = new Scanner(System.in);
    private final ConsumoApi api = new ConsumoApi();
    private final ConvierteDatos conversor = new ConvierteDatos();
    private final LibroRepository libroRepository;
    private final AutorRepository autorRepository;

    String menu = """
            \n
            Selecciona el número de la opción deseada:
            1 - Buscar libro por título
            2 - Lista de libros registrados
            3 - Lista de autores registrados
            4 - Lista de libros según el idioma
            5 - Listar autores vivos en un año determinado
            0 - Salir
            \n""";

    public App(LibroRepository  libroRepository, AutorRepository autorRepository) {
        this.autorRepository = autorRepository;
        this.libroRepository = libroRepository;

    }

    public List<Idioma> crearListaDeIdiomas() {
        List<Idioma> todosLosIdiomas = new ArrayList<>();
        for (Idioma idioma : Idioma.values()) {
            List<Libro> libroList = libroRepository.findByIdioma(idioma);
            if (libroList != null && !libroList.isEmpty()) {
                todosLosIdiomas.add(idioma);
            }
        }
        return todosLosIdiomas;
    }

    public void start() {
        var opcion = -1;
        while (opcion != 0) {
            try {
                System.out.println(menu);
                System.out.print("Seleccione una opción: ");
                opcion = scanner.nextInt();
                scanner.nextLine(); // Limpiar el buffer

                switch (opcion) {
                    case 1:
                        buscarLibroPorNombre();
                        break;
                    case 2:
                        listaDeLibrosRegistados();
                        break;
                    case 3:
                        listaDeAutoresRegistrados();
                        break;
                    case 4:
                        listaDeLibrosPorIdioma();
                        break;
                    case 5:
                        listarAutoresVivosEnAno();
                        break;
                    case 0:
                        System.out.println("Salir...");
                        break;
                    default:
                        System.out.println("Opción inválida");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: Entrada inválida. Por favor, ingrese un número.");
                scanner.nextLine();
            }
        }
    }

    private void listarAutoresVivosEnAno() {
        System.out.print("Ingrese el año para buscar autores vivos: ");
        try {
            int ano = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer

            List<Autor> autoresVivos = autorRepository.findAll().stream()
                    .filter(autor -> (autor.getNacimiento() <= ano) &&
                            ((autor.getFallecimiento() == null) || (autor.getFallecimiento() > ano)))
                    .toList();

            if (autoresVivos.isEmpty()) {
                System.out.println("No se encontraron autores vivos en el año " + ano + ".");
            } else {
                System.out.println("Autores vivos en el año " + ano + ":");
                autoresVivos.forEach(autor -> System.out.println("- " + autor.getNombre()));
            }
        } catch (InputMismatchException e) {
            System.out.println("Error: Año inválido. Por favor, ingrese un número válido.");
            scanner.nextLine();
        }
    }

    private void buscarLibroPorNombre() {
        System.out.println("Escriba el nombre del libro que desea buscar:");
        String busqueda = scanner.nextLine().toLowerCase().replace(" ", "+");
        try {
            validarDatos(obtenerDatosLibro(busqueda));
        } catch (NoSuchElementException e) {
            System.out.println("\nLibro no encontrado!\n");

        }


    }

    private void listaDeLibrosRegistados() {
        List<Libro> libroList = libroRepository.findAll();
        libroList.forEach(this::mostrarDatosLibro);

    }

    private void listaDeAutoresRegistrados() {
        List<Autor> autorList = autorRepository.findAll();
        autorList.forEach(this::mostrarDatosDelAutor);

    }

    private void listaDeLibrosPorIdioma() {
        List<Idioma> todosLosIdiomas = crearListaDeIdiomas();

        System.out.println("Todos los idiomas registrados:");
        for (Idioma idioma : todosLosIdiomas) {
            System.out.println(idioma.getIdiomaEnEspanol() + " (" + idioma.getIdioma() + ")");
        }
        try {
            System.out.println("Elija el idioma a buscar escribiendo su abreviatura\n:"
            );

            var busqueda = scanner.nextLine().toLowerCase();

            Idioma idioma = Idioma.fromString(busqueda);

            List<Libro> libroList = libroRepository.findByIdioma(idioma);
            if (libroList != null && !libroList.isEmpty()) {
                System.out.println("\nLibros en " + busqueda + "\n");
                libroList.forEach(this::mostrarDatosLibro);
            } else {
                System.out.println("\n No se encontraron libros en el idioma: " + busqueda+"\n");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }


    }

    private Datos obtenerDatosLibro(String busqueda) {
        String json = api.obtenerDatos("http://gutendex.com/books/?search=" + busqueda);
        return conversor.obtenerLosDatos(json, Datos.class);
    }

    public void mostrarDatosLibro(Libro libro) {
        var DatosLibro = "----- LIBRO -----" +
                "\nTitulo: " + libro.getTitulo() +
                "\nAutor: " + libro.getAutor().getNombre() +
                "\nIdioma: " + libro.getIdioma() +
                "\nNumero de descargas: " + libro.getNumeroDescargas() +
                "\n-----------------\n";
        System.out.println(DatosLibro);
    }

    private void mostrarDatosDelAutor(Autor autor) {
        var DatosAutor = "----- AUTOR -----" +
                "\nNombre : " + autor.getNombre() +
                "\nAño de nacimiento: " + autor.getNacimiento() +
                "\nAño de fallecimiento: " + autor.getFallecimiento() +
                "\n-----------------\n";
        System.out.println(DatosAutor);

    }

    public void validarDatos(Datos datos) {

        Libro libroEntrada = new Libro(datos.libros().getFirst());
        Autor autorEntrada = new Autor(libroEntrada.getAutor());


        Optional<Autor> autorDBOptional = autorRepository.findByNombre(autorEntrada.getNombre());
        Optional<Libro> libroDBOptional = libroRepository.findByTituloContainingIgnoreCase(libroEntrada.getTitulo());

        if (libroDBOptional.isPresent()) {
            Libro libroDB = libroDBOptional.get();
            if (libroEntrada.getTitulo().equalsIgnoreCase(libroDB.getTitulo())) {
                mostrarDatosLibro(libroDB);
                return;
            }
        }

        if (autorDBOptional.isPresent()) {
            Autor autorDB = autorDBOptional.get();
            System.out.println("Guardando nuevo libro para el autor: " + autorDB.getNombre());
            Libro nuevoLibro = new Libro(libroEntrada, autorDB);
            mostrarDatosLibro(nuevoLibro);
            libroRepository.save(nuevoLibro);
        } else {

            System.out.println("\nNuevo registro para libro y autor realizado.\n");

            Libro nuevoLibro = new Libro(libroEntrada, autorEntrada);
            mostrarDatosLibro(nuevoLibro);
            autorRepository.save(autorEntrada);
            libroRepository.save(nuevoLibro);
        }

    }
}