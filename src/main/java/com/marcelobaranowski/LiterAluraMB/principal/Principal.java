package com.marcelobaranowski.LiterAluraMB.principal;

import com.marcelobaranowski.LiterAluraMB.model.*;
import com.marcelobaranowski.LiterAluraMB.repository.AutorRepository;
import com.marcelobaranowski.LiterAluraMB.repository.LibroRepository;
import com.marcelobaranowski.LiterAluraMB.service.ConsumoAPIGutendex;
import com.marcelobaranowski.LiterAluraMB.service.ConvierteDatos;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Principal {
    Scanner teclado = new Scanner(System.in);
    private ConsumoAPIGutendex consumoAPI = new ConsumoAPIGutendex();
    private static final String URL_BASE = "https://gutendex.com/books/?search=";
    private ConvierteDatos conversorDatos = new ConvierteDatos();
    private List<LibroDB> libros;
    private List<AutorDB> autores;
    private LibroRepository libroRepository;
    private AutorRepository autorRepository;

    @Autowired
    public Principal(LibroRepository libroRepository, AutorRepository autorRepository) {
        this.libroRepository = libroRepository;
        this.autorRepository = autorRepository;
    }

    public void mostrarMenuOpciones() {
        var opcion = -1;
        while (opcion != 0) {
            var menu = """
                    1 - Buscar libro por título
                    2 - Listar libros registrados
                    3 - Listar autores registrados
                    4 - Listar autores vivos en un determinado año
                    5 - Listar libros por idioma
                    0 - Salir
                    """;
            System.out.println(menu);
            System.out.println("Por favor ingrese la opción deseada");
            opcion = teclado.nextInt();
            teclado.nextLine();
            switch (opcion) {
                case 1:
                    buscarLibroPorTitulo();
                    break;
                case 2:
                    listarLibrosRegistrados();
                    break;
                case 3:
                    listarAutoresRegistrados();
                    break;
                case 4:
                    listarAutoresVivos();
                    break;
                case 5:
                    listarLibrosPorIdioma();
                    break;
                case 0:
                    System.out.println("Gracias por usar la aplicación");
                    break;
                default:
                    System.out.println("Opción inválida");
            }
        }
    }

    private Datos getDatos() {
        System.out.println("Escriba el título del libro: ");
        var nombreLibro = teclado.nextLine();
        var json = consumoAPI.obtenerDatosLibros(URL_BASE + nombreLibro.replace(" ", "%20"));
        Datos datos = conversorDatos.obtenerDatosLibros(json, Datos.class);
        return datos;
    }

    private void buscarLibroPorTitulo() {
        Datos datos = getDatos();
        if (datos != null && !datos.resultados().isEmpty()) {
            DatosLibros primerLibro = datos.resultados().get(0);

            LibroDB libro = new LibroDB(primerLibro);

            Optional<LibroDB> libroExiste = libroRepository.findByTitulo(libro.getTitulo());
            if (libroExiste.isPresent()) {
                System.out.println("\nEl libro ya está registrado\n");
            } else {

                if (!primerLibro.autor().isEmpty()) {
                    DatosAutor autor = primerLibro.autor().get(0);
                    AutorDB autor1 = new AutorDB(autor);
                    Optional<AutorDB> autorOptional = autorRepository.findByNombre(autor1.getNombre());

                    if (autorOptional.isPresent()) {
                        AutorDB autorExiste = autorOptional.get();
                        libro.setAutor(autorExiste);
                        libroRepository.save(libro);
                    } else {
                        AutorDB autorNuevo = autorRepository.save(autor1);
                        libro.setAutor(autorNuevo);
                        libroRepository.save(libro);
                    }

                    Integer numeroDescargas = libro.getNumeroDeDescargas() != null ? libro.getNumeroDeDescargas() : 0;
                    System.out.println("********** Libro **********");
                    System.out.printf("Titulo: %s%nAutor: %s%nIdioma: %s%nNumero de Descargas: %s%n",
                            libro.getTitulo(), autor1.getNombre(), libro.getIdioma(), libro.getNumeroDeDescargas());
                    System.out.println("***************************\n");
                } else {
                    System.out.println("Sin autor");
                }
            }
        } else {
            System.out.println("libro no encontrado");
        }
    }

    private void listarLibrosRegistrados() {
        libros = libroRepository.findAll();
        libros.stream()
                .forEach(System.out::println);
    }

    private void listarAutoresRegistrados() {
        autores = autorRepository.findAll();
        autores.stream()
                .forEach(System.out::println);
    }

    private void listarAutoresVivos() {
        System.out.println("Ingrese el año para listar los autores que estaban vivos: ");
        var anio = teclado.nextInt();
        autores = autorRepository.listaAutoresVivos(anio);
        autores.stream()
                .forEach(System.out::println);
    }


    private void listarLibrosPorIdioma() {
        String menuIdioma = """
                Ingrese el idioma para buscar los libros: 
                es >> Español
                en >> Ingles
                fr >> Frances 
                pt >> Portugues
                """;
        //System.out.println("Opcion 5");
        System.out.println(menuIdioma);
        String idiomaBuscado = teclado.nextLine();
        DatosIdioma idioma = null;
        //teclado.next();
        switch (idiomaBuscado){
            case "es":
                idioma = DatosIdioma.valueOf("ESPAÑOL");
                break;
            case "en":
                idioma = DatosIdioma.valueOf("INGLES");
                break;
            case "fr":
                idioma = DatosIdioma.valueOf("FRANCES");
                break;
            case "pt":
                idioma = DatosIdioma.valueOf("PORTUGUES");
                break;
            default:
                System.out.println("Entrada inválida.");
                return;

        }
        buscarPorIdioma(idioma);

    }

    private void buscarPorIdioma(DatosIdioma idioma){
        libros = libroRepository.findByIdioma(idioma);
        if (libros.isEmpty()){
            System.out.println("No hay libros registrados");
        } else {
            libros.stream().forEach(System.out::println);
        }
    }

}

