package br.com.lucashenriquedev.ibmchallenge.domains.books.controller.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class BookDetailsResponse {

    private Long id;
    private String sbn;
    private String name;
    private String description;
    private String author;
    private Integer availableQuantity;

}
