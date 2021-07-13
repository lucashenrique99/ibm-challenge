package br.com.lucashenriquedev.ibmchallenge.domains.books.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "books", uniqueConstraints = {
        @UniqueConstraint(name = "uk_books_sbn", columnNames = "sbn")
})
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @EqualsAndHashCode.Include
    private Long id;

    @NotEmpty
    private String sbn;

    @NotEmpty
    private String name;

    private String description;

    @NotEmpty
    private String author;

    @PositiveOrZero
    @NotNull
    private Integer availableQuantity;

}
