package br.com.lucashenriquedev.ibmchallenge.domains.books.adapter;

import br.com.lucashenriquedev.ibmchallenge.domains.books.controller.request.InsertBookRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BookAdapterTest {

    @Test
    public void shouldTransformToBook() {
        var request = getMockInsertBookRequest();
        var book = BookAdapter.from(request);

        Assertions.assertEquals(book.getSbn(), request.getSbn());
        Assertions.assertEquals(book.getName(), request.getName());
        Assertions.assertEquals(book.getAuthor(), request.getAuthor());
        Assertions.assertEquals(book.getDescription(), request.getDescription());
        Assertions.assertEquals(book.getAvailableQuantity(), request.getAvailableQuantity());

    }

    private InsertBookRequest getMockInsertBookRequest() {
        return new InsertBookRequest(
                "sbn",
                "nome do livro",
                "descrição",
                "autor",
                10);
    }

}
