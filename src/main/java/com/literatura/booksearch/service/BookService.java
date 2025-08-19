package com.literatura.booksearch.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.literatura.booksearch.model.Book;
import com.literatura.booksearch.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final ObjectMapper objectMapper;

    @Autowired
    public BookService(BookRepository bookRepository, ObjectMapper objectMapper) {
        this.bookRepository = bookRepository;
        this.objectMapper = objectMapper;
    }

    private final String apiUrl = "https://gutendex.com/books";

    public List<Book> searchBooks(String query) {
        List<Book> books = new ArrayList<>();
        try {
            String encodedQuery = URLEncoder.encode(query, "UTF-8");
            String urlString = apiUrl + "?search=" + encodedQuery;
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Accept", "application/json");

            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            JsonNode rootNode = objectMapper.readTree(response.toString());
            JsonNode resultsNode = rootNode.get("results");

            if (resultsNode != null && resultsNode.isArray()) {
                for (JsonNode bookNode : resultsNode) {
                    Book book = new Book();
                    if (bookNode.has("title")) {
                        book.setTitle(bookNode.get("title").asText());
                    }
                    if (bookNode.has("authors") && bookNode.get("authors").isArray() && bookNode.get("authors").size() > 0) {
                        JsonNode authorNode = bookNode.get("authors").get(0);
                        if (authorNode.has("name")) {
                            book.setAuthor(authorNode.get("name").asText());
                        }
                    }
                    if (bookNode.has("languages") && bookNode.get("languages").isArray() && bookNode.get("languages").size() > 0) {
                        book.setLanguage(bookNode.get("languages").get(0).asText());
                    }
                    books.add(book);
                }
            }

            if (!books.isEmpty()) {
                bookRepository.saveAll(books);
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error al buscar o guardar los libros: " + e.getMessage());
        }
        return books;
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public List<Book> getBooksByAuthor(String author) {
        return bookRepository.findByAuthorContainingIgnoreCase(author);
    }

    public List<Book> getBooksByLanguage(String language) {
        return bookRepository.findByLanguage(language);
    }

    public long countBooks() {
        return bookRepository.count();
    }
}