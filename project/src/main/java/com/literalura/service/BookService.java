package com.literalura.service;

import com.literalura.model.Book;
import com.literalura.model.GutendexResponse;
import com.literalura.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;
    private final RestTemplate restTemplate;
    private static final String GUTENDEX_API = "https://gutendex.com/books?search=";

    public Book searchAndSaveBook(String title) {
        GutendexResponse response = restTemplate.getForObject(
            GUTENDEX_API + title,
            GutendexResponse.class
        );

        if (response == null || response.getResults().isEmpty()) {
            return null;
        }

        GutendexResponse.GutendexBook gutendexBook = response.getResults().get(0);
        
        if (bookRepository.existsByTitle(gutendexBook.getTitle())) {
            throw new RuntimeException("Book already exists in the database");
        }

        Book book = new Book();
        book.setTitle(gutendexBook.getTitle());
        book.setAuthor(gutendexBook.getAuthors().get(0).getName());
        book.setLanguage(gutendexBook.getLanguages().get(0));
        book.setDownloads(gutendexBook.getDownload_count());

        return bookRepository.save(book);
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public List<String> getAllAuthors() {
        return bookRepository.findAllAuthors();
    }

    public List<Book> getBooksByLanguage(String language) {
        if (!isValidLanguageCode(language)) {
            throw new IllegalArgumentException("Invalid language code. Supported codes: EN, ES, FR, PT");
        }
        return bookRepository.findByLanguage(language.toLowerCase());
    }

    private boolean isValidLanguageCode(String code) {
        List<String> validCodes = List.of("EN", "ES", "FR", "PT");
        return validCodes.contains(code.toUpperCase());
    }
}