package br.com.lucashenriquedev.ibmchallenge.domains.books.controller.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InsertBookRequest {

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
