package br.com.lucashenriquedev.ibmchallenge.domains.books.messages;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BookMessages {

    public static final String USED_SBN = "Este código sbn já está em uso";
    public static final String BOOK_NOT_FOUND = "Livro não encontrado";

}
