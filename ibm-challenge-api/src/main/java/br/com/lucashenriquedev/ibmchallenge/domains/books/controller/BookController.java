package br.com.lucashenriquedev.ibmchallenge.domains.books.controller;

import br.com.lucashenriquedev.ibmchallenge.domains.books.adapter.BookAdapter;
import br.com.lucashenriquedev.ibmchallenge.domains.books.controller.request.InsertBookRequest;
import br.com.lucashenriquedev.ibmchallenge.domains.books.controller.request.UpdateBookRequest;
import br.com.lucashenriquedev.ibmchallenge.domains.books.controller.response.BookDetailsResponse;
import br.com.lucashenriquedev.ibmchallenge.domains.books.controller.response.BookNameResponse;
import br.com.lucashenriquedev.ibmchallenge.domains.books.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService service;

    @Autowired
    public BookController(BookService bookService) {
        this.service = bookService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BookDetailsResponse insert(@RequestBody @Valid InsertBookRequest request) {
        return BookAdapter.toBookDetailsResponse(
                service.insert(BookAdapter.from(request)));
    }

    @PatchMapping("/{id}")
    public BookDetailsResponse update(@PathVariable Long id, @RequestBody @Valid UpdateBookRequest request) {
        return BookAdapter.toBookDetailsResponse(
                service.update(id, BookAdapter.from(request)));
    }

    @GetMapping("/{id}")
    public BookDetailsResponse findById(@PathVariable Long id) {
        return BookAdapter.toBookDetailsResponse(service.findByIdOrThrows(id));
    }

    @GetMapping
    public Page<BookNameResponse> findAllInStock(
            @RequestParam(defaultValue = "0", required = false) Integer page,
            @RequestParam(defaultValue = "20", required = false) Integer pageSize) {
        return BookAdapter.toBookNameResponsePage(service.findAllInStock(page, pageSize));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) {
        service.deleteById(id);
    }

}
