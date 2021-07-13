package br.com.lucashenriquedev.ibmchallenge.domains.books.controller;

import br.com.lucashenriquedev.ibmchallenge.commons.exceptions.InvalidArgumentException;
import br.com.lucashenriquedev.ibmchallenge.commons.exceptions.ResourceNotFound;
import br.com.lucashenriquedev.ibmchallenge.domains.books.controller.request.InsertBookRequest;
import br.com.lucashenriquedev.ibmchallenge.domains.books.controller.request.UpdateBookRequest;
import br.com.lucashenriquedev.ibmchallenge.domains.books.messages.BookMessages;
import br.com.lucashenriquedev.ibmchallenge.domains.books.model.Book;
import br.com.lucashenriquedev.ibmchallenge.domains.books.service.BookService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Collections;

@WebMvcTest(BookController.class)
public class BookControllerTest {

    private static final String BASE_PATH = "/books";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService bookService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void shouldInsert() throws Exception {
        Mockito.when(bookService.insert(ArgumentMatchers.any()))
                .thenReturn(getMockBook(1L));

        String payload = objectMapper.writeValueAsString(getMockInsertBookRequest());

        this.mockMvc.perform(MockMvcRequestBuilders.post(BASE_PATH)
                .contentType(MediaType.APPLICATION_JSON)
                .content(payload))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    public void shouldThrowsErrorOnInsert() throws Exception {
        Mockito.when(bookService.insert(ArgumentMatchers.any()))
                .thenThrow(new InvalidArgumentException(BookMessages.USED_SBN));

        String payload = objectMapper.writeValueAsString(getMockInsertBookRequest());

        this.mockMvc.perform(MockMvcRequestBuilders.post(BASE_PATH)
                .contentType(MediaType.APPLICATION_JSON)
                .content(payload))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isUnprocessableEntity())
                .andExpect(MockMvcResultMatchers.jsonPath("$.error", Matchers.equalTo(BookMessages.USED_SBN)));
    }

    @Test
    public void shouldUpdate() throws Exception {
        final long id = 10L;

        Mockito.when(bookService.update(ArgumentMatchers.any(), ArgumentMatchers.any()))
                .thenReturn(getMockBook(id));

        var requestData = getMockUpdateBookRequest();
        String payload = objectMapper.writeValueAsString(requestData);

        this.mockMvc.perform(MockMvcRequestBuilders.patch(BASE_PATH.concat("/").concat(Long.toString(id)))
                .contentType(MediaType.APPLICATION_JSON)
                .content(payload))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.not(Matchers.emptyString())));
    }

    @Test
    public void shouldThrowsErrorOnUpdate() throws Exception {
        final long id = 10L;

        Mockito.when(bookService.update(ArgumentMatchers.any(), ArgumentMatchers.any()))
                .thenThrow(new ResourceNotFound(BookMessages.BOOK_NOT_FOUND));

        String payload = objectMapper.writeValueAsString(getMockUpdateBookRequest());

        this.mockMvc.perform(MockMvcRequestBuilders.patch(BASE_PATH.concat("/").concat(Long.toString(id)))
                .contentType(MediaType.APPLICATION_JSON)
                .content(payload))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(MockMvcResultMatchers.jsonPath("$.error", Matchers.equalTo(BookMessages.BOOK_NOT_FOUND)));
    }

    @Test
    public void shouldFindById() throws Exception {
        final long id = 10;

        Mockito.when(bookService.findByIdOrThrows(id))
                .thenReturn(getMockBook(id));

        this.mockMvc.perform(MockMvcRequestBuilders.get(BASE_PATH.concat("/{id}"), id)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.description", Matchers.not(Matchers.emptyString())));
    }

    @Test
    public void shouldThrowsErrorOnFindById() throws Exception {
        final long id = 10L;

        Mockito.when(bookService.findByIdOrThrows(ArgumentMatchers.any()))
                .thenThrow(new ResourceNotFound(BookMessages.BOOK_NOT_FOUND));

        this.mockMvc.perform(MockMvcRequestBuilders.get(BASE_PATH.concat("/{id}"), id)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(MockMvcResultMatchers.jsonPath("$.error", Matchers.equalTo(BookMessages.BOOK_NOT_FOUND)));
    }

    @Test
    public void shouldFindAllInStock() throws Exception {
        final long id = 10L;

        Mockito.when(bookService.findAllInStock(ArgumentMatchers.any(), ArgumentMatchers.any()))
                .thenReturn(getMockPageBook(id));

        this.mockMvc.perform(MockMvcRequestBuilders.get(BASE_PATH)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.content", Matchers.not(Matchers.emptyArray())));
    }

    @Test
    public void shouldDeleteById() throws Exception {
        final long id = 10L;

        Mockito.doNothing()
                .when(bookService).deleteById(id);

        this.mockMvc.perform(MockMvcRequestBuilders.delete(BASE_PATH.concat("/{id}"), id)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    @Test
    public void shouldThrowsErrorOnDeleteById() throws Exception {
        final long id = 10L;

        Mockito.doThrow(new ResourceNotFound(BookMessages.BOOK_NOT_FOUND))
                .when(bookService).deleteById(ArgumentMatchers.any());

        this.mockMvc.perform(MockMvcRequestBuilders.delete(BASE_PATH.concat("/{id}"), id)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(MockMvcResultMatchers.jsonPath("$.error", Matchers.equalTo(BookMessages.BOOK_NOT_FOUND)));
    }

    private Page<Book> getMockPageBook(Long id) {
        return new PageImpl<>(Collections.singletonList(getMockBook(id)));
    }

    private Book getMockBook(Long id) {
        return Book.builder()
                .id(id)
                .sbn("sbn")
                .name("Nome")
                .description("Descrição")
                .author("Autor")
                .availableQuantity(10)
                .build();
    }

    private InsertBookRequest getMockInsertBookRequest() {
        return new InsertBookRequest(
                "sbn",
                "nome do livro",
                "descrição",
                "autor",
                10);
    }

    private UpdateBookRequest getMockUpdateBookRequest() {
        return new UpdateBookRequest(
                "nome do livro",
                "descrição de atualizacao",
                "autor",
                10);
    }

}
