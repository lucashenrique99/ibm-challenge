package br.com.lucashenriquedev.ibmchallenge.domains.books.adapter;

import br.com.lucashenriquedev.ibmchallenge.domains.books.controller.request.InsertBookRequest;
import br.com.lucashenriquedev.ibmchallenge.domains.books.controller.request.UpdateBookRequest;
import br.com.lucashenriquedev.ibmchallenge.domains.books.controller.response.BookDetailsResponse;
import br.com.lucashenriquedev.ibmchallenge.domains.books.controller.response.BookNameResponse;
import br.com.lucashenriquedev.ibmchallenge.domains.books.model.Book;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BookAdapter {

    public static Book from(InsertBookRequest request) {
        return Book.builder()
                .name(request.getName())
                .author(request.getAuthor())
                .availableQuantity(request.getAvailableQuantity())
                .description(request.getDescription())
                .sbn(request.getSbn())
                .build();
    }

    public static Book from(UpdateBookRequest request) {
        return Book.builder()
                .name(request.getName())
                .author(request.getAuthor())
                .availableQuantity(request.getAvailableQuantity())
                .description(request.getDescription())
                .build();
    }

    public static Page<BookNameResponse> toBookNameResponsePage(Page<Book> page) {
        return page.map(BookAdapter::toBookNameResponse);
    }

    public static BookNameResponse toBookNameResponse(Book book) {
        return BookNameResponse.builder()
                .name(book.getName())
                .build();
    }

    public static BookDetailsResponse toBookDetailsResponse(Book book) {
        return BookDetailsResponse.builder()
                .id(book.getId())
                .name(book.getName())
                .author(book.getAuthor())
                .availableQuantity(book.getAvailableQuantity())
                .description(book.getDescription())
                .sbn(book.getSbn())
                .build();
    }

}
