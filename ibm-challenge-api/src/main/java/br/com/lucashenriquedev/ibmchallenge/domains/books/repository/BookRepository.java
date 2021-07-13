package br.com.lucashenriquedev.ibmchallenge.domains.books.repository;

import br.com.lucashenriquedev.ibmchallenge.domains.books.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    @Query("SELECT b FROM Book b WHERE b.availableQuantity > 0")
    Page<Book> findAllInStock(Pageable pageable);

    Optional<Book> findBySbn(String sbn);

    boolean existsBySbn(String sbn);

}
