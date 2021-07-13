package br.com.lucashenriquedev.ibmchallenge.domains.books.service;

import br.com.lucashenriquedev.ibmchallenge.commons.exceptions.InvalidArgumentException;
import br.com.lucashenriquedev.ibmchallenge.domains.books.model.Book;
import br.com.lucashenriquedev.ibmchallenge.domains.books.repository.BookRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.Collections;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {

    @InjectMocks
    private BookService service;

    @Mock
    private BookRepository repository;

    @Test
    public void shouldInsert() {
        var mockBook = getMockBook(10L);

        Mockito.when(repository.save(ArgumentMatchers.any()))
                .thenReturn(mockBook);
        Mockito.when(repository.existsBySbn(ArgumentMatchers.any()))
                .thenReturn(false);

        var saved = service.insert(mockBook);
        Assertions.assertNotNull(saved);
    }

    @Test
    public void shouldFailedOnInsert() {
        Mockito.when(repository.existsBySbn(ArgumentMatchers.any()))
                .thenReturn(true);

        Assertions.assertThrows(
                InvalidArgumentException.class,
                () -> service.insert(getMockBook(10L)));
    }


    private Page<Book> getMockPageBook(Long id) {
        return new PageImpl<>(Collections.singletonList(getMockBook(id)));
    }

    private Book getMockBook(Long id) {
        return getMockBook(id, "Nome");
    }

    private Book getMockBook(Long id, String name) {
        return Book.builder()
                .id(id)
                .sbn("sbn")
                .name(name)
                .description("Descrição")
                .author("Autor")
                .availableQuantity(10)
                .build();
    }
}
