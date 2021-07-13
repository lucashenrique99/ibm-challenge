package br.com.lucashenriquedev.ibmchallenge.domains.books.service;

import br.com.lucashenriquedev.ibmchallenge.commons.exceptions.InvalidArgumentException;
import br.com.lucashenriquedev.ibmchallenge.commons.exceptions.ResourceNotFound;
import br.com.lucashenriquedev.ibmchallenge.domains.books.messages.BookMessages;
import br.com.lucashenriquedev.ibmchallenge.domains.books.model.Book;
import br.com.lucashenriquedev.ibmchallenge.domains.books.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    private final BookRepository repository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.repository = bookRepository;
    }

    public Book insert(Book book) {
        if (repository.existsBySbn(book.getSbn())) {
            throw new InvalidArgumentException(BookMessages.USED_SBN);
        }

        return repository.save(book);
    }

    public Book findByIdOrThrows(Long id) {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFound(BookMessages.BOOK_NOT_FOUND));
    }

    public Page<Book> findAllInStock(Integer page, Integer pageSize) {
        return repository.findAllInStock(PageRequest.of(page, pageSize, Sort.by("name")));
    }

    public Book update(Long id, Book book) {
        var saved = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFound(BookMessages.BOOK_NOT_FOUND));

        saved.setName(book.getName());
        saved.setAuthor(book.getAuthor());
        saved.setDescription(book.getDescription());
        saved.setAvailableQuantity(book.getAvailableQuantity());

        return repository.save(saved);
    }

    public void deleteById(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFound(BookMessages.BOOK_NOT_FOUND);
        }
        repository.deleteById(id);
    }

}
