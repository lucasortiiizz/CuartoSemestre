package com.mycompany.algoritmo;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class primertarea{

    // Clase interna Libro
    static class Libro {
        private String titulo;
        private String autor;
        private String isbn;
        private boolean disponible;

        // Constructor
        public Libro(String titulo, String autor, String isbn) {
            this.titulo = titulo;
            this.autor = autor;
            this.isbn = isbn;
            this.disponible = true;
        }

        // Métodos
        public void prestar() {
            if (disponible) {
                disponible = false;
                System.out.println("El libro '" + titulo + "' ha sido prestado.");
            } else {
                System.out.println("El libro '" + titulo + "' no está disponible.");
            }
        }

        public void devolver() {
            disponible = true;
            System.out.println("El libro '" + titulo + "' ha sido devuelto.");
        }

        public void mostrarDetalles() {
            System.out.println("Título: " + titulo);
            System.out.println("Autor: " + autor);
            System.out.println("ISBN: " + isbn);
            System.out.println("Disponible: " + (disponible ? "Sí" : "No"));
        }

        public String getIsbn() {
            return isbn;
        }

        public boolean isDisponible() {
            return disponible;
        }
    }

    // Clase interna Usuario
    static class Usuario {
        private String nombre;
        private String idUsuario;
        private List<Libro> librosPrestados;

        // Constructor
        public Usuario(String nombre, String idUsuario) {
            this.nombre = nombre;
            this.idUsuario = idUsuario;
            this.librosPrestados = new ArrayList<>();
        }

        // Métodos
        public void prestarLibro(Libro libro) {
            if (libro.isDisponible()) {
                librosPrestados.add(libro);
                libro.prestar();
            } else {
                System.out.println("El libro '" + libro.getIsbn() + "' no está disponible.");
            }
        }

        public void devolverLibro(Libro libro) {
            if (librosPrestados.remove(libro)) {
                libro.devolver();
            } else {
                System.out.println("Este libro no está en la lista de libros prestados.");
            }
        }

        public void mostrarPrestamos() {
            System.out.println("Libros prestados por " + nombre + ":");
            for (Libro libro : librosPrestados) {
                libro.mostrarDetalles();
            }
        }

        public String getIdUsuario() {
            return idUsuario;
        }
    }

    // Clase interna Biblioteca
    static class Biblioteca {
        private List<Libro> libros;
        private List<Usuario> usuarios;

        // Constructor
        public Biblioteca() {
            libros = new ArrayList<>();
            usuarios = new ArrayList<>();
        }

        // Métodos
        public void añadirLibro(Libro libro) {
            libros.add(libro);
            System.out.println("Libro '" + libro.getIsbn() + "' añadido a la biblioteca.");
        }

        public void registrarUsuario(Usuario usuario) {
            usuarios.add(usuario);
            System.out.println("Usuario '" + usuario.getIdUsuario() + "' registrado.");
        }

        public void prestarLibro(String isbn, String idUsuario) {
            Libro libro = buscarLibro(isbn);
            Usuario usuario = buscarUsuario(idUsuario);

            if (libro != null && usuario != null) {
                usuario.prestarLibro(libro);
            } else {
                System.out.println("No se puede realizar la operación. Libro o usuario no encontrado.");
            }
        }

        public void devolverLibro(String isbn, String idUsuario) {
            Libro libro = buscarLibro(isbn);
            Usuario usuario = buscarUsuario(idUsuario);

            if (libro != null && usuario != null) {
                usuario.devolverLibro(libro);
            } else {
                System.out.println("No se puede realizar la operación. Libro o usuario no encontrado.");
            }
        }

        private Libro buscarLibro(String isbn) {
            for (Libro libro : libros) {
                if (libro.getIsbn().equals(isbn)) {
                    return libro;
                }
            }
            return null;
        }

        private Usuario buscarUsuario(String idUsuario) {
            for (Usuario usuario : usuarios) {
                if (usuario.getIdUsuario().equals(idUsuario)) {
                    return usuario;
                }
            }
            return null;
        }
    }

    public static void main(String[] args) {
        Biblioteca biblioteca = new Biblioteca();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- Sistema de Biblioteca ---");
            System.out.println("1. Añadir libro");
            System.out.println("2. Registrar usuario");
            System.out.println("3. Prestar libro");
            System.out.println("4. Devolver libro");
            System.out.println("5. Mostrar detalles de libro");
            System.out.println("6. Mostrar préstamos de usuario");
            System.out.println("7. Salir");
            System.out.print("Seleccione una opción: ");

            int opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea

            switch (opcion) {
                case 1:
                    System.out.print("Ingrese título del libro: ");
                    String titulo = scanner.nextLine();
                    System.out.print("Ingrese autor del libro: ");
                    String autor = scanner.nextLine();
                    System.out.print("Ingrese ISBN del libro: ");
                    String isbn = scanner.nextLine();
                    biblioteca.añadirLibro(new Libro(titulo, autor, isbn));
                    break;
                case 2:
                    System.out.print("Ingrese nombre del usuario: ");
                    String nombre = scanner.nextLine();
                    System.out.print("Ingrese ID del usuario: ");
                    String idUsuario = scanner.nextLine();
                    biblioteca.registrarUsuario(new Usuario(nombre, idUsuario));
                    break;
                case 3:
                    System.out.print("Ingrese ISBN del libro a prestar: ");
                    String isbnPrestar = scanner.nextLine();
                    System.out.print("Ingrese ID del usuario: ");
                    String idUsuarioPrestar = scanner.nextLine();
                    biblioteca.prestarLibro(isbnPrestar, idUsuarioPrestar);
                    break;
                case 4:
                    System.out.print("Ingrese ISBN del libro a devolver: ");
                    String isbnDevolver = scanner.nextLine();
                    System.out.print("Ingrese ID del usuario: ");
                    String idUsuarioDevolver = scanner.nextLine();
                    biblioteca.devolverLibro(isbnDevolver, idUsuarioDevolver);
                    break;
                case 5:
                    System.out.print("Ingrese ISBN del libro para mostrar detalles: ");
                    String isbnDetalles = scanner.nextLine();
                    Libro libro = biblioteca.buscarLibro(isbnDetalles);
                    if (libro != null) {
                        libro.mostrarDetalles();
                    } else {
                        System.out.println("Libro no encontrado.");
                    }
                    break;
                case 6:
                    System.out.print("Ingrese ID del usuario para mostrar préstamos: ");
                    String idUsuarioPrestamo = scanner.nextLine();
                    Usuario usuario = biblioteca.buscarUsuario(idUsuarioPrestamo);
                    if (usuario != null) {
                        usuario.mostrarPrestamos();
                    } else {
                        System.out.println("Usuario no encontrado.");
                    }
                    break;
                case 7:
                    System.out.println("Saliendo del sistema.");
                    scanner.close();
                    return;
                default:
                    System.out.println("Opción inválida. Intente de nuevo.");
            }
        }
    }
}
