package com.literatura.booksearch;

import com.literatura.booksearch.model.Book;
import com.literatura.booksearch.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class BookSearchApplication implements CommandLineRunner {

    private final BookService bookService;

    @Autowired
    public BookSearchApplication(BookService bookService) {
        this.bookService = bookService;
    }

    public static void main(String[] args) {
        SpringApplication.run(BookSearchApplication.class, args);
    }

    @Override
    public void run(String... args) {
        try (Scanner scanner = new Scanner(System.in)) {
            int option = -1;
            while (option != 0) {
                printMenu();
                try {
                    option = scanner.nextInt();
                    scanner.nextLine();

                    switch (option) {
                        case 1:
                            searchAndSaveBooks(scanner);
                            break;
                        case 2:
                            listAllBooks();
                            break;
                        case 3:
                            searchByAuthor(scanner);
                            break;
                        case 4:
                            searchByLanguage(scanner);
                            break;
                        case 5:
                            countTotalBooks();
                            break;
                        case 0:
                            System.out.println("Saliendo de la aplicación. ¡Hasta luego!");
                            break;
                        default:
                            System.out.println("Opción no válida. Intenta de nuevo.");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Entrada no válida. Por favor, introduce un número.");
                    scanner.nextLine();
                    option = -1;
                }
            }
        }
    }

    private void printMenu() {
        System.out.println("\n--- Menú de Opciones ---");
        System.out.println("1. Buscar y guardar libros por título");
        System.out.println("2. Listar todos los libros guardados");
        System.out.println("3. Buscar libros por autor");
        System.out.println("4. Buscar libros por idioma");
        System.out.println("5. Contar el total de libros guardados");
        System.out.println("0. Salir");
        System.out.print("Elige una opción: ");
    }

    private void searchAndSaveBooks(Scanner scanner) {
        System.out.print("Introduce un título para buscar: ");
        String query = scanner.nextLine();
        System.out.println("\nBuscando libros...");
        List<Book> foundBooks = bookService.searchBooks(query);
        if (foundBooks.isEmpty()) {
            System.out.println("No se encontraron libros para la consulta.");
        } else {
            System.out.println("Libros encontrados y guardados:");
            foundBooks.forEach(book -> System.out.println("- Título: " + book.getTitle() + ", Autor: " + book.getAuthor()));
        }
    }

    private void listAllBooks() {
        List<Book> allBooks = bookService.getAllBooks();
        if (allBooks.isEmpty()) {
            System.out.println("No hay libros guardados.");
        } else {
            System.out.println("\n--- Libros Guardados ---");
            allBooks.forEach(book -> System.out.println("- Título: " + book.getTitle() + ", Autor: " + book.getAuthor() + ", Idioma: " + book.getLanguage()));
        }
    }

    private void searchByAuthor(Scanner scanner) {
        System.out.print("Introduce el nombre del autor: ");
        String author = scanner.nextLine();
        List<Book> booksByAuthor = bookService.getBooksByAuthor(author);
        if (booksByAuthor.isEmpty()) {
            System.out.println("No se encontraron libros del autor '" + author + "'.");
        } else {
            System.out.println("\n--- Libros de '" + author + "' ---");
            booksByAuthor.forEach(book -> System.out.println("- Título: " + book.getTitle() + ", Idioma: " + book.getLanguage()));
        }
    }

    private void searchByLanguage(Scanner scanner) {
        System.out.print("Introduce el idioma (ej. 'en', 'es'): ");
        String language = scanner.nextLine();
        List<Book> booksByLanguage = bookService.getBooksByLanguage(language);
        if (booksByLanguage.isEmpty()) {
            System.out.println("No se encontraron libros en el idioma '" + language + "'.");
        } else {
            System.out.println("\n--- Libros en el idioma '" + language + "' ---");
            booksByLanguage.forEach(book -> System.out.println("- Título: " + book.getTitle() + ", Autor: " + book.getAuthor()));
        }
    }

    private void countTotalBooks() {
        long count = bookService.countBooks();
        System.out.println("\nTotal de libros guardados: " + count);
    }
}